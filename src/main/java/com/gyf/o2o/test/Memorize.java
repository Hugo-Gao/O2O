package com.gyf.o2o.test;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by gaoyunfan on 2017/12/18
 **/
public class Memorize<A,V> implements Compute<A, V>
{
    private Map<A, Future<V>> cache= new ConcurrentHashMap<>();
    private Compute<A, V> c;

    public Memorize(Compute<A, V> c)
    {
        this.c = c;
    }

    @Override
    public V compute(A arg)
    {
        while (true)
        {
            Future<V> result = cache.get(arg);
            if (result == null)
            {
                Callable<V> callable = new Callable<V>()
                {
                    @Override
                    public V call() throws InterruptedException
                    {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> f = new FutureTask<>(callable);
                result = cache.putIfAbsent(arg, f);
                if (result == null)
                {
                    result = f;
                    f.run();
                }
            }
            try
            {
                return result.get();
            } catch (CancellationException e)
            {
                cache.remove(arg, result);
            } catch (ExecutionException e)
            {
                e.printStackTrace();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
