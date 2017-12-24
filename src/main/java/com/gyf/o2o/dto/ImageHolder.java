package com.gyf.o2o.dto;

import java.io.InputStream;

/**
 * Created by gaoyunfan on 2017/12/21
 **/
public class ImageHolder
{
    private String imageName;
    private InputStream image;

    public String getImageName()
    {
        return imageName;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }

    public InputStream getImage()
    {
        return image;
    }

    public void setImage(InputStream image)
    {
        this.image = image;
    }

    public ImageHolder(String imageName, InputStream image)
    {

        this.imageName = imageName;
        this.image = image;
    }
}
