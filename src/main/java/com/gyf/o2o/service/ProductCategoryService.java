package com.gyf.o2o.service;

import com.gyf.o2o.dto.ProductCategoryExecution;
import com.gyf.o2o.entity.ProductCategory;
import com.gyf.o2o.exceptions.ProductCategoryOperationException;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/18
 **/
public interface ProductCategoryService
{
    List<ProductCategory> getProductCategoryList(Long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
     * 将此类别下的商品类别id置为空,在删除该商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)throws ProductCategoryOperationException;
}
