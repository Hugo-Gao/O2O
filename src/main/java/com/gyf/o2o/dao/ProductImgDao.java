package com.gyf.o2o.dao;

import com.gyf.o2o.entity.ProductImg;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/20
 **/
public interface ProductImgDao
{
    /**
     * 批量添加商品缩略图
     *
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);


    /**
     * 删除指定商品下的所有详情图
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);

    /**
     * 获取指定的商品id的商品详情图
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(Long productId);
}
