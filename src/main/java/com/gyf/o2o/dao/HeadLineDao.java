package com.gyf.o2o.dao;

import com.gyf.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/24
 **/
public interface HeadLineDao
{
    /**
     * 根据传入的查询条件查询HeadLine
     * @param headLineCondition
     * @return
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
