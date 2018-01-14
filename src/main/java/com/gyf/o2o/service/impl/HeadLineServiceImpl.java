package com.gyf.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyf.o2o.cache.JedisUtil;
import com.gyf.o2o.dao.HeadLineDao;
import com.gyf.o2o.entity.HeadLine;
import com.gyf.o2o.exceptions.HeadLineOperationException;
import com.gyf.o2o.service.HeadLineService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/24
 **/
@Service
public class HeadLineServiceImpl implements HeadLineService
{

    @Autowired
    private HeadLineDao headLineDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(HeadLineServiceImpl.class);

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition)
    {
        String key = HLLISTKEY;
        List<HeadLine> headLineList = null;
        ObjectMapper mapper = new ObjectMapper();
        if (headLineCondition != null && headLineCondition.getEnableStatus() != null)
        {
            key = key + "_" + headLineCondition.getEnableStatus();
        }

        if (!jedisKeys.exists(key))//如果redis里不存在此键
        {
            headLineList = headLineDao.queryHeadLine(headLineCondition);
            String jsonString = null;
            try
            {
                jsonString = mapper.writeValueAsString(headLineList);
            } catch (JsonProcessingException e)
            {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        }else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
            try
            {
                headLineList = mapper.readValue(jsonString, javaType);
            } catch (IOException e)
            {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
        }
        return headLineList;
    }
}
