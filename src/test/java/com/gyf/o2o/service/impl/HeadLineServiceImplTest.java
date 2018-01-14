package com.gyf.o2o.service.impl;

import com.gyf.o2o.BaseTest;
import com.gyf.o2o.dao.HeadLineDao;
import com.gyf.o2o.entity.HeadLine;
import com.gyf.o2o.service.HeadLineService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gaoyunfan on 2018/1/13
 **/
public class HeadLineServiceImplTest extends BaseTest
{
    @Autowired
    private HeadLineService headLineService;

    @Test
    public void getHeadLineList()
    {
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        List<HeadLine> headLineList = headLineService.getHeadLineList(headLineCondition);
        System.out.println(headLineList.size());
    }
}