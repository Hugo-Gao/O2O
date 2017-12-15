package com.gyf.o2o.service.impl;

import com.gyf.o2o.dao.ShopDao;
import com.gyf.o2o.dto.ShopExecution;
import com.gyf.o2o.entity.Shop;
import com.gyf.o2o.enums.ShopStateEnum;
import com.gyf.o2o.exceptions.ShopOperationException;
import com.gyf.o2o.service.ShopService;
import com.gyf.o2o.util.ImageUtil;
import com.gyf.o2o.util.PageCalculator;
import com.gyf.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/6
 **/
@Service
public class ShopServiceImpl implements ShopService
{
    @Autowired
    private ShopDao shopDao;

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize)
    {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null)
        {
            se.setShopList(shopList);
            se.setCount(count);
        }else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName)
    {
        if (shop == null)
        {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try
        {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0)
            {
                //必须为ShopOperationException，事务才能回滚！！！！
                throw new ShopOperationException("店铺创建失败");
            } else
            {
                if (shopImgInputStream != null)
                {
                    //存储图片
                    try
                    {
                        addShopImg(shop, shopImgInputStream, fileName);//获取到图片存储地址
                    } catch (Exception e)
                    {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    effectedNum = shopDao.updateShop(shop);//更新数据库中图片地址信息
                    if (effectedNum <= 0)
                    {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e)
        {
            throw new ShopOperationException("addShop Error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    @Override
    public Shop getByShopId(long shopId)
    {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopInputStream, String fileName) throws ShopOperationException
    {
        if (shop == null || shop.getShopId() == null)
        {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else
        {
            try
            {
                if (shopInputStream != null && fileName != null && !"".equals(fileName))
                {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null)
                    {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopInputStream, fileName);
                }
                //1.判断是否需要处理图片
                //2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0)
                {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else
                {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e)
            {
                throw new ShopOperationException("modifyShopError:" + e.getMessage());
            }
        }
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName)
    {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        shop.setShopImg(shopImgAddr);
    }

}
