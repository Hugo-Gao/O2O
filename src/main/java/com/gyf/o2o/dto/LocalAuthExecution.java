package com.gyf.o2o.dto;

import com.gyf.o2o.entity.LocalAuth;
import com.gyf.o2o.enums.LocalAuthStateEnum;

import java.util.List;

/**
 * Created by gaoyunfan on 2017/12/6
 **/
public class LocalAuthExecution
{
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;

    //店铺数量
    private int count;

    //操作的shop(增删改店铺时用到)
    private LocalAuth shop;

    //shop列表(查询列表的时候使用)
    private List<LocalAuth> shopList;

    public LocalAuthExecution()
    {
    }

    //店铺操作失败的构造器
    public LocalAuthExecution(LocalAuthStateEnum stateEnum)
    {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //增删改成功的构造器
    public LocalAuthExecution(LocalAuthStateEnum stateEnum, LocalAuth shop)
    {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    //查询成功的构造器
    public LocalAuthExecution(LocalAuthStateEnum stateEnum, List<LocalAuth> shopList)
    {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public String getStateInfo()
    {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo)
    {
        this.stateInfo = stateInfo;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public LocalAuth getLocalAuth()
    {
        return shop;
    }

    public void setLocalAuth(LocalAuth shop)
    {
        this.shop = shop;
    }

    public List<LocalAuth> getLocalAuthList()
    {
        return shopList;
    }

    public void setLocalAuthList(List<LocalAuth> shopList)
    {
        this.shopList = shopList;
    }
}
