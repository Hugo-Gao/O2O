package com.gyf.o2o.service;

import com.gyf.o2o.entity.ProductCategory;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/18
 **/
public interface ProductCategoryService
{
    List<ProductCategory> getProductCategoryList(Long shopId);
}
