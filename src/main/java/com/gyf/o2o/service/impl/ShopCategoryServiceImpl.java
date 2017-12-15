package com.gyf.o2o.service.impl;

import com.gyf.o2o.dao.ShopCategoryDao;
import com.gyf.o2o.entity.ShopCategory;
import com.gyf.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/10
 **/
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService
{
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition)
    {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
