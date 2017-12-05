package com.gyf.o2o.dao;

import com.gyf.o2o.entity.Shop;

/**
 * Created by gaoyunfan on 2017/12/5
 **/
public interface ShopDao
{
    /**
     * 新增店铺
     * @param shop
     * @return -1失败
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
