package com.gyf.o2o.util;

/**
 * Created by gaoyunfan on 2017/12/15
 **/
public class PageCalculator
{
    public static int calculateRowIndex(int pageIndex, int pageSize)
    {
        return (pageIndex>0)?(pageIndex-1)*pageSize:0;
    }
}
