package com.gyf.o2o.dao;

import com.gyf.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/20
 **/
public interface ProductDao
{
    /**
     * 查询商品列表并分页 ，可输入的条件有：商品名，商品状态，店铺id，商品类别
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 查询对应的商品总数
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 根据id查找product
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    /**
     * 根据实体类返回信息
     * @param product
     * @return
     */
    int updateProduct(Product product);

    int updateProductCategoryToNull(@Param("productCategoryId") long productCategoryId);
}
