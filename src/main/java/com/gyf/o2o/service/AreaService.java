package com.gyf.o2o.service;

import com.gyf.o2o.entity.Area;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/5
 **/
public interface AreaService
{
    public final static String AREALISTKEY = "arealist";

    List<Area> getAreaList();
}
