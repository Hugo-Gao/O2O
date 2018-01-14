package com.gyf.o2o.service.impl;

import com.gyf.o2o.BaseTest;
import com.gyf.o2o.entity.Area;
import com.gyf.o2o.service.AreaService;
import com.gyf.o2o.service.CacheService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gaoyunfan on 2017/12/5
 **/
public class AreaServiceImplTest extends BaseTest
{
    @Autowired
    private AreaService areaService;
    @Autowired
    private CacheService cacheService;
    @Test
    public void testGetAreaList()
    {
        List<Area> areaList = areaService.getAreaList();
        assertEquals("西苑", areaList.get(0).getAreaName());
        cacheService.removeFromCache(areaService.AREALISTKEY);
        areaList = areaService.getAreaList();
    }
}