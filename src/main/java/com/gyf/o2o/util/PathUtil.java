package com.gyf.o2o.util;

/**
 * Created by gaoyunfan on 2017/12/6
 * 根据环境不同提供不同的根路径
 **/
public class PathUtil
{
    private static String separator = System.getProperty("file.separator");

    public static String getImgBasePath()
    {
        String os = System.getProperty("os.name");
        String basePath;
        if (os.toLowerCase().startsWith("win"))
        {
            basePath = "E:/projectdev/image/";
        } else
        {
            basePath = "/home/image/";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    public static String getShopImagePath(long shopId)
    {
        String imagePath = "upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }
}
