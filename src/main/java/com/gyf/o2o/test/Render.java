package com.gyf.o2o.test;

import com.gyf.o2o.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by gaoyunfan on 2017/12/20
 **/
public class Render
{
    private final ExecutorService executor;

    public Render()
    {
        this.executor = new ScheduledThreadPoolExecutor(100);
    }

    void renderPage()
    {
        long startTime = System.currentTimeMillis();
        List<ImageInfo> infoList = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            infoList.add(new ImageInfo((5+1-i)*1000));
        }
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executor);
        for (ImageInfo imageInfo : infoList)
        {
            completionService.submit(new Callable<ImageData>()
            {
                @Override
                public ImageData call() throws Exception
                {
                    return imageInfo.downloadImage();
                }
            });
        }
        try
        {
            executor.shutdownNow();
            for (int i = 0, n= infoList.size(); i < n; i++)
            {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                imageData.showResult();
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime+" Costs");
    }

    public static void main(String[] args)
    {
        Render render = new Render();
        render.renderPage();
    }

    class ImageInfo
    {
        int name;

        public ImageInfo(int name)
        {
            this.name = name;
        }

        ImageData downloadImage()
        {
            try
            {
                Thread.sleep(name);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return new ImageData(name);
        }
    }

    class ImageData
    {
        int name;

        public ImageData(int name)
        {
            this.name = name;
        }

        public void showResult()
        {
            System.out.println("Image"+name+" has done");
        }
    }
}
