package com.gyf.o2o.service;

import com.gyf.o2o.entity.ShopCategory;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/10
 **/
public interface ShopCategoryService
{
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
