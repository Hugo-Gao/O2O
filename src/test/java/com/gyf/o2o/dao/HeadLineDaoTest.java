package com.gyf.o2o.dao;

import com.gyf.o2o.BaseTest;
import com.gyf.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gaoyunfan on 2017/12/24
 **/
public class HeadLineDaoTest extends BaseTest
{
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void queryHeadLine()
    {
        List<HeadLine> headLines = headLineDao.queryHeadLine(new HeadLine());
        assertEquals(headLines.size(), 2);

    }
}