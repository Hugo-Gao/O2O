package com.gyf.o2o.service.impl;

import com.gyf.o2o.dao.ProductCategoryDao;
import com.gyf.o2o.entity.ProductCategory;
import com.gyf.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/18
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService
{
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategoryList(Long shopId)
    {
        return productCategoryDao.queryProductCategoryByShopId(shopId);
    }
}
