package com.gyf.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyf.o2o.cache.JedisUtil;
import com.gyf.o2o.dao.AreaDao;
import com.gyf.o2o.entity.Area;
import com.gyf.o2o.exceptions.AreaOperationException;
import com.gyf.o2o.service.AreaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/5
 **/
@Service
public class AreaServiceImpl implements AreaService
{
    @Autowired
    private AreaDao areaDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;


    private static org.slf4j.Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
    @Override
    @Transactional
    public List<Area> getAreaList()
    {
        String key = AREALISTKEY;
        List<Area> areaList = null;
        ObjectMapper mapper = new ObjectMapper();
        if (!jedisKeys.exists(key))//如果redis里不存在此键
        {
            areaList = areaDao.queryArea();
            String jsonString = null;
            try
            {
                jsonString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e)
            {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        }else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try
            {
                areaList = mapper.readValue(jsonString, javaType);
            } catch (IOException e)
            {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }

}
