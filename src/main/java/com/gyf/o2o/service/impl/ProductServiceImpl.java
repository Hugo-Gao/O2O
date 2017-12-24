package com.gyf.o2o.service.impl;

import com.gyf.o2o.dao.ProductDao;
import com.gyf.o2o.dao.ProductImgDao;
import com.gyf.o2o.dto.ImageHolder;
import com.gyf.o2o.dto.ProductExecution;
import com.gyf.o2o.entity.Product;
import com.gyf.o2o.entity.ProductImg;
import com.gyf.o2o.enums.ProductStateEnum;
import com.gyf.o2o.exceptions.ProductOperationException;
import com.gyf.o2o.service.ProductService;
import com.gyf.o2o.util.ImageUtil;
import com.gyf.o2o.util.PageCalculator;
import com.gyf.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/21
 **/
@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize)
    {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    //1.处理缩略图，获取缩略图相对路径并赋值给product
    //2.往tb_product写入商品信息，获取product_id
    //3.结合productId批量处理商品详情图
    //4.将商品详情图列表插入tb_product_img中
    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException
    {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null)
        {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);
            if (thumbnail != null)
            {
                addThumbnail(product, thumbnail);
            }
            try
            {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0)
                {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e)
            {
                throw new ProductOperationException("创建商品失败"+e.toString());
            }
            if(productImgList!=null&&productImgList.size()>0)
            {
                addProductImgList(product, productImgList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS);
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public Product getProductById(long productId)
    {
        return productDao.queryProductById(productId);
    }


    //1.若缩略图有值，则处理缩略图,若原先存在缩略图则先删除再添加新图
    //2.若商品详情图列表参数有值，与1一样的操作
    //3.更新tb_product和tb_product_img的信息
    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException
    {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null)
        {
            product.setLastEditTime(new Date());
            //若上传了新的缩略图则先删除原来的再添加新的
            if (thumbnail != null)
            {
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if (tempProduct.getImgAddr() != null)
                {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            if (productImgHolderList != null && productImgHolderList.size() > 0)
            {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productImgHolderList);
            }
            try
            {
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0)
                {
                    throw new ProductOperationException("更新商品失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e)
            {
                throw new ProductOperationException("更新商品失败"+e.toString());
            }
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    private void deleteProductImgList(Long productId)
    {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList)
        {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        //删除数据库里的详情图
        productImgDao.deleteProductImgByProductId(productId);
    }

    /**
     * 批量添加详情图
     * @param product
     * @param productImgHolderList
     */
    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList)
    {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        for (ImageHolder imageHolder : productImgHolderList)
        {
            String imgAddr = ImageUtil.generateNormalImg(imageHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        if (productImgList.size() > 0)
        {
            try
            {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0)
                {
                    throw new ProductOperationException("创建商品详情图失败");
                }
            } catch (Exception e)
            {
                throw new ProductOperationException("创建商品详情图失败"+e.toString());
            }
        }
    }

    /**
     * 添加缩略图
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, ImageHolder thumbnail)
    {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }
}
