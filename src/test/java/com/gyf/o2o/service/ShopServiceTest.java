package com.gyf.o2o.service;

import com.gyf.o2o.BaseTest;
import com.gyf.o2o.dto.ShopExecution;
import com.gyf.o2o.entity.Area;
import com.gyf.o2o.entity.PersonInfo;
import com.gyf.o2o.entity.Shop;
import com.gyf.o2o.entity.ShopCategory;
import com.gyf.o2o.enums.ShopStateEnum;
import com.gyf.o2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by gaoyunfan on 2017/12/7
 **/
public class ShopServiceTest extends BaseTest
{

    @Autowired
    private ShopService shopService;

    @Test
    public void testGetShopList()
    {
        Shop shopCondition = new Shop();
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(1L);
        shopCondition.setShopCategory(sc);
        ShopExecution se = shopService.getShopList(shopCondition, 1, 2);
        System.out.println("店铺列表数为" + se.getShopList().size());
        System.out.println("店铺总数为" + se.getCount());

    }



    @Test
    public void testAddShop() throws FileNotFoundException
    {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("12341231");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("C:\\Users\\Lenovo\\Desktop\\image\\watermark.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.addShop(shop, is,shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(),shopExecution.getState());
    }
    @Test
    public void testModifyShop() throws ShopOperationException,FileNotFoundException
    {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("修改后的店铺名称"+shop.getShopName());
        File shopImg = new File("C:\\Users\\Lenovo\\Desktop\\image\\dabai.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.modifyShop(shop, is, "dabai.jpg");
        System.out.println("新的图片地址"+shopExecution.getShop().getShopImg());
    }

}