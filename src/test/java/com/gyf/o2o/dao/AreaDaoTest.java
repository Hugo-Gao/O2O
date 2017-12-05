package com.gyf.o2o.dao;

import com.gyf.o2o.BaseTest;
import com.gyf.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by gaoyunfan on 2017/12/5
 **/
public class AreaDaoTest extends BaseTest
{
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea()
    {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2, areaList.size());
    }
}