package com.gyf.o2o.entity;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by gaoyunfan on 2017/11/28
 **/
public class Area
{
    private Integer areaId;

    private String areaName;

    private Integer priority;//不希望有默认值0,所以用装箱类型

    private Date createTime;

    private Date lastEditTime;

    public Integer getAreaId()
    {
        return areaId;
    }

    public void setAreaId(Integer areaId)
    {
        this.areaId = areaId;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getLastEditTime()
    {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime)
    {
        this.lastEditTime = lastEditTime;
    }

}
