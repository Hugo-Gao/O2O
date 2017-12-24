package com.gyf.o2o.test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by gaoyunfan on 2017/12/20
 **/
public class TaskExecutionWebServer
{
    private static final int NTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args)
    {
    }
}
