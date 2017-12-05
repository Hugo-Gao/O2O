package com.gyf.o2o.entity;

import java.util.Date;

/**
 * Created by gaoyunfan on 2017/11/30
 * 第三方微信账号
 **/
public class WechatAuth
{
    private Long wechatAuthId;//微信账号id

    private String openId;

    private Date createTime;

    private PersonInfo personInfo;


    public Long getWechatAuthId()
    {
        return wechatAuthId;
    }

    public void setWechatAuthId(Long wechatAuthId)
    {
        this.wechatAuthId = wechatAuthId;
    }

    public String getOpenId()
    {
        return openId;
    }

    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public PersonInfo getPersonInfo()
    {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo)
    {
        this.personInfo = personInfo;
    }
}
