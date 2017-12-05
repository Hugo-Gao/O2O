package com.gyf.o2o.service.impl;

import com.gyf.o2o.dao.AreaDao;
import com.gyf.o2o.entity.Area;
import com.gyf.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/5
 **/
@Service
public class AreaServiceImpl implements AreaService
{
    @Autowired
    private AreaDao areaDao;
    @Override
    public List<Area> getAreaList()
    {
        return areaDao.queryArea();
    }

}
