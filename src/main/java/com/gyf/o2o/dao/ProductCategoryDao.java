package com.gyf.o2o.dao;

import com.gyf.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/18
 **/
public interface ProductCategoryDao
{
    /**
     * 通过shopId查询店铺类别
     *
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryByShopId(Long shopId);

    /**
     * 批量新增商品类别
     *
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * 根据两个id删除category
     * @param productCategoryId
     * @param shopId
     * @return
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
