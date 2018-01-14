package com.gyf.o2o.service;

/**
 * Created by gaoyunfan on 2018/1/13
 **/
public interface CacheService
{

    /**
     * 根据Key的前缀删除匹配该模式下的所有键值对，输入shopcategory，则删除所有shopcategory_**的键值对
     * @param keyPrefix
     */
    void removeFromCache(String keyPrefix);
}
