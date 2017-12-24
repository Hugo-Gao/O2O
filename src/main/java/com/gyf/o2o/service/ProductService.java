package com.gyf.o2o.service;

import com.gyf.o2o.dto.ImageHolder;
import com.gyf.o2o.dto.ProductExecution;
import com.gyf.o2o.entity.Product;
import com.gyf.o2o.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/21
 **/
public interface ProductService
{

    /**
     * 查询商品列表并分页
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    /**
     * 添加商品信息以及图片处理
     * @param product 商品
     * @param thumbnail 缩略图
     * @param imageHolderList 详情图列表
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail,
                                List<ImageHolder> imageHolderList)
            throws ProductOperationException;

    /**
     * 根据id获取对应的product
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 修改商品信息以及图片处理
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException;
}
