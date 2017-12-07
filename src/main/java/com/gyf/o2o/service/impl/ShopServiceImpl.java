package com.gyf.o2o.service.impl;

import com.gyf.o2o.dao.ShopDao;
import com.gyf.o2o.dto.ShopExecution;
import com.gyf.o2o.entity.Shop;
import com.gyf.o2o.enums.ShopStateEnum;
import com.gyf.o2o.exceptions.ShopOperationException;
import com.gyf.o2o.service.ShopService;
import com.gyf.o2o.util.ImageUtil;
import com.gyf.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

/**
 * Created by gaoyunfan on 2017/12/6
 **/
@Service
public class ShopServiceImpl implements ShopService
{
    @Autowired
    private ShopDao shopDao;
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg)
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
            }else{
                if (shopImg != null)
                {
                    //存储图片
                    try
                    {
                        addShopImg(shop, shopImg);//获取到图片存储地址
                    } catch (Exception e)
                    {
                        throw new ShopOperationException("addShopImg error:"+e.getMessage());
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

    private void addShopImg(Shop shop, File shopImg)
    {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgAddr);
    }

}