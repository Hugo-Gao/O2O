package com.gyf.o2o.exceptions;

/**
 * Created by gaoyunfan on 2017/12/19
 **/
public class ProductOperationException extends RuntimeException
{

    private static final long serialVersionUID = 142198563445002805L;

    public ProductOperationException(String msg)
    {
        super(msg);
    }
}
