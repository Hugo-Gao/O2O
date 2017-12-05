package com.gyf.o2o.dao;

import com.gyf.o2o.entity.Area;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/5
 **/
public interface AreaDao
{
    /**
     * 列出所有区域列表
     * @return
     */
    List<Area> queryArea();
}
