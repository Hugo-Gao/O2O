package com.gyf.o2o.exceptions;

/**
 * Created by gaoyunfan on 2017/12/19
 **/
public class ProductCategoryOperationException extends RuntimeException
{


    private static final long serialVersionUID = -9113764044123348997L;

    public ProductCategoryOperationException(String msg)
    {
        super(msg);
    }
}
