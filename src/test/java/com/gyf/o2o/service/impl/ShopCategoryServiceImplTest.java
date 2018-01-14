package com.gyf.o2o.service.impl;

import com.gyf.o2o.BaseTest;
import com.gyf.o2o.entity.ShopCategory;
import com.gyf.o2o.service.ShopCategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gaoyunfan on 2018/1/13
 **/
public class ShopCategoryServiceImplTest extends BaseTest
{
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void getShopCategoryList()
    {
        List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(null);
        System.out.println(shopCategoryList.size());

    }
}