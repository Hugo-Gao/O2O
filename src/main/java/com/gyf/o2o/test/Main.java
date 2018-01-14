package com.gyf.o2o.test;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by gaoyunfan on 2017/12/18
 **/
public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        Thread one =new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("one start");
            }
        });

        Thread two=new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("two start");
            }
        });

        one.start();
        two.start();
        System.out.println("this is main");
//        one.join();
//        two.join();
        System.out.println("main finish");
    }
}
