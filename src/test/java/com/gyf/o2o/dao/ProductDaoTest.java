package com.gyf.o2o.dao;

import com.gyf.o2o.BaseTest;
import com.gyf.o2o.entity.Product;
import com.gyf.o2o.entity.ProductCategory;
import com.gyf.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gaoyunfan on 2017/12/20
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest
{

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testAInsertProduct()
    {
        Shop shop1 = new Shop();
        shop1.setShopId(1L);
        ProductCategory pc1 = new ProductCategory();
        pc1.setProductCategoryId(1L);

        Product product1 = new Product();
        product1.setProductName("测试1");
        product1.setProductDesc("测试DESC1");
        product1.setImgAddr("test1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(pc1);

        Product product2 = new Product();
        product2.setProductName("测试2");
        product2.setProductDesc("测试DESC2");
        product2.setImgAddr("test2");
        product2.setPriority(2);
        product2.setEnableStatus(0);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop1);
        product2.setProductCategory(pc1);

        Product product3 = new Product();
        product3.setProductName("test3");
        product3.setProductDesc("测试DESC3");
        product3.setImgAddr("test3");
        product3.setPriority(3);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop1);
        product3.setProductCategory(pc1);

        int effectedNum = productDao.insertProduct(product1);
        assertEquals(1, effectedNum);
        effectedNum = productDao.insertProduct(product2);
        assertEquals(1, effectedNum);
        effectedNum = productDao.insertProduct(product3);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testQueryProductById()
    {
        Product product = productDao.queryProductById(5L);
        assertEquals(2, product.getProductImgList().size());

    }
    @Test
    public void testUpdateProduct()
    {
        Product product = new Product();
        ProductCategory pc = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(1L);
        pc.setProductCategoryId(2L);
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductName("第一个产品");
        product.setProductCategory(pc);
        int effectedNum = productDao.updateProduct(product);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryProductList() throws Exception {
        Product productCondition = new Product();
        // 分页查询，预期返回三条结果
        List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
        assertEquals(3, productList.size());
        // 查询名称为测试的商品总数
        int count = productDao.queryProductCount(productCondition);
        assertEquals(8, count);
        // 使用商品名称模糊查询，预期返回两条结果
        productCondition.setProductName("测试");
        productList = productDao.queryProductList(productCondition, 0, 3);
        assertEquals(3, productList.size());
        count = productDao.queryProductCount(productCondition);
        assertEquals(6, count);

    }

    @Test
    public void updateProductCategoryToNull()
    {
        int i = productDao.updateProductCategoryToNull(7L);
        assertEquals(i, 1);
    }


}