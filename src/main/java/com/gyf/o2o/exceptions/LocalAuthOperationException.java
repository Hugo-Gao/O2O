package com.gyf.o2o.exceptions;

/**
 * Created by gaoyunfan on 2017/12/19
 **/
public class LocalAuthOperationException extends RuntimeException
{
    private static final long serialVersionUID = 4461547400826082807L;

    public LocalAuthOperationException(String msg)
    {
        super(msg);
    }
}
