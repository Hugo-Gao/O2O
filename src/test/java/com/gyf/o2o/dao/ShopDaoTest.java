package com.gyf.o2o.dao;

import com.gyf.o2o.BaseTest;
import com.gyf.o2o.entity.Area;
import com.gyf.o2o.entity.PersonInfo;
import com.gyf.o2o.entity.Shop;
import com.gyf.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gaoyunfan on 2017/12/5
 **/
public class ShopDaoTest extends BaseTest
{

    @Autowired
    private ShopDao shopDao;

    @Test
    @Ignore
    public void testInsertShop()
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
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("12341231");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    @Ignore
    public void testUpdateShop()
    {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void queryByShopId()
    {
        long shopId = 1L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println(shop.getArea().getAreaId());
        System.out.println(shop.getArea().getAreaName());

    }
    @Test
    public void testQueryShopListAndCount()
    {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
        assertEquals(shopList.size(),5);
        int i = shopDao.queryShopCount(shopCondition);
        assertEquals(i,5);
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(1L);
        shopCondition.setShopCategory(sc);
        shopList = shopDao.queryShopList(shopCondition, 0, 2);
        assertEquals(shopList.size(),2);
        i = shopDao.queryShopCount(shopCondition);
        assertEquals(i,4);

    }
}