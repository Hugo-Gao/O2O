package com.gyf.o2o.service;

import com.gyf.o2o.entity.ShopCategory;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/10
 **/
public interface ShopCategoryService
{
    /**
     * 根据查询条件查询
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
