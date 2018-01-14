package com.gyf.o2o.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

import java.util.Set;

/**
 * Created by gaoyunfan on 2018/1/12
 **/
public class JedisUtil
{
    public Keys KEYS;
    //对存储结构Strings的操作
    public Strings STRINGS;
    private JedisPool jedisPool;

    public JedisPool getJedisPool()
    {
        return jedisPool;
    }

    public void setJedisPool(JedisPoolWriper jedisPoolWriper)
    {
        this.jedisPool = jedisPoolWriper.getJedisPool();
    }

    public Jedis getJedis()
    {
        return jedisPool.getResource();
    }

    //***************************Keys***********************
    public class Keys
    {


        public String flushAll()
        {
            Jedis jedis = getJedis();
            String stata=jedis.flushAll();
            jedis.close();
            return stata;
        }

        public long del(String...keys)
        {
            Jedis jedis = getJedis();
            long count = jedis.del(keys);
            jedis.close();
            return count;
        }

        public boolean exists(String key)
        {
            Jedis jedis = getJedis();
            boolean exits = jedis.exists(key);
            jedis.close();
            return exits;
        }

        public Set<String> keys(String pattern)
        {
            Jedis jedis = getJedis();
            Set<String> set = jedis.keys(pattern);
            jedis.close();
            return set;
        }
    }

    public class Strings
    {

        public String get(String key)
        {
            Jedis jedis = getJedis();
            String value = jedis.get(key);
            jedis.close();
            return value;
        }

        public String set(String key, String value)
        {
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        public String set(byte[] key, byte[] value)
        {
            Jedis jedis=getJedis();
            String status = jedis.set(key, value);
            jedis.close();
            return status;
        }
    }
}
