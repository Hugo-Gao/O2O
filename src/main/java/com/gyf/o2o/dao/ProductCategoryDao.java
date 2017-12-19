package com.gyf.o2o.dao;

import com.gyf.o2o.entity.ProductCategory;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/18
 **/
public interface ProductCategoryDao
{
    List<ProductCategory> queryProductCategoryByShopId(Long shopId);
}
