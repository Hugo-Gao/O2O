package com.gyf.o2o.exceptions;

/**
 * Created by gaoyunfan on 2017/12/7
 * 商店操作异常类
 **/
public class ShopOperationException extends RuntimeException
{

    private static final long serialVersionUID = 6818710100331734000L;

    public ShopOperationException(String msg)
    {
        super(msg);
    }
}
