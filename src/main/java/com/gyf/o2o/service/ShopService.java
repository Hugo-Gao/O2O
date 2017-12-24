package com.gyf.o2o.service;

import com.gyf.o2o.dto.ImageHolder;
import com.gyf.o2o.dto.ShopExecution;
import com.gyf.o2o.entity.Shop;
import com.gyf.o2o.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

/**
 * Created by gaoyunfan on 2017/12/6
 **/
public interface ShopService
{
    /**
     * 获取含条件和分页的商店列表
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    ShopExecution addShop(Shop shop, ImageHolder thumbnail);

    /**
     * 通过店铺id获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 修改店铺信息
     * @param shop
     * @param thumbnail 图片流
     * @return
     */
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
