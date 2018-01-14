package com.gyf.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyf.o2o.cache.JedisUtil;
import com.gyf.o2o.dao.ShopCategoryDao;
import com.gyf.o2o.entity.ShopCategory;
import com.gyf.o2o.entity.ShopCategory;
import com.gyf.o2o.exceptions.ShopCategoryOperationException;
import com.gyf.o2o.service.ShopCategoryService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/10
 **/
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService
{
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Autowired

    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);


    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition)
    {
        String key = SCLISTKEY;
        List<ShopCategory> headLineList = null;
        ObjectMapper mapper = new ObjectMapper();

        if (shopCategoryCondition == null)
        {
            key = key + "_allfirstlevel";
        } else if (shopCategoryCondition.getParent().getShopCategoryId() != null)
        {
            key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
        } else if (shopCategoryCondition != null)
        {
            key = key + "_allsecondlevel";
        }

        if (!jedisKeys.exists(key))//如果redis里不存在此键
        {
            headLineList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
            String jsonString = null;
            try
            {
                jsonString = mapper.writeValueAsString(headLineList);
            } catch (JsonProcessingException e)
            {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        }else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
            try
            {
                headLineList = mapper.readValue(jsonString, javaType);
            } catch (IOException e)
            {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
        }
        return headLineList;
    }
}
