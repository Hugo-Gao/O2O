package com.gyf.o2o.dao;

import com.gyf.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/10
 **/
public interface ShopCategoryDao
{
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);
}
