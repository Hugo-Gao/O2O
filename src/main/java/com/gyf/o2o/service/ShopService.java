package com.gyf.o2o.service;

import com.gyf.o2o.dto.ShopExecution;
import com.gyf.o2o.entity.Shop;

import java.io.File;

/**
 * Created by gaoyunfan on 2017/12/6
 **/
public interface ShopService
{
    ShopExecution addShop(Shop shop, File shopImg);

}
