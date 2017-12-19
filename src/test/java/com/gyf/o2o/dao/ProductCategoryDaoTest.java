package com.gyf.o2o.dao;

import com.gyf.o2o.BaseTest;
import com.gyf.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gaoyunfan on 2017/12/18
 **/
public class ProductCategoryDaoTest extends BaseTest
{

    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Test
    public void queryProductCategoryByShopId()
    {
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryByShopId(1L);
        assertEquals(productCategoryList.size(), 3);
    }
}