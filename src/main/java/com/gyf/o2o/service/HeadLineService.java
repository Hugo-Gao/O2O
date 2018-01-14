package com.gyf.o2o.service;

import com.gyf.o2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/24
 **/
public interface HeadLineService
{

    public static final String HLLISTKEY = "headlinelist";

    /**
     * 根据条件查询HeadLine
     * @param headLineCondition
     * @return
     * @throws IOException
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) ;
}
