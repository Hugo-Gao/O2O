package com.gyf.o2o.util;

import com.gyf.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by gaoyunfan on 2017/12/6
 **/
public class ImageUtil
{
    //获取resources的路径
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将CommonsMutipartFile转换为File
     * @param cFile
     * @return
     */
    public static File transferCommonsMutipartFileToFile(CommonsMultipartFile cFile)
    {
        File newFile = new File(cFile.getOriginalFilename());
        try
        {
            cFile.transferTo(newFile);
        } catch (IOException e)
        {
            logger.error(e.toString());
            e.printStackTrace();
        }

        return newFile;
    }
    /**
     * 处理缩略图,并返回新生成图片的相对值路径
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr)
    {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        //相对路径
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is "+relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete Addr is "+PathUtil.getImgBasePath() + relativeAddr);

        try{
            Thumbnails.of(thumbnail.getImage()).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e)
        {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径上所涉及到的路径
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr)
    {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists())
        {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取文件的扩展名
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName)
    {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名,不能重复，当前年月日小时分钟秒钟+五位随机数
     * @return
     */
    public static String getRandomFileName()
    {
        int rannum = r.nextInt(89999)+10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 判断是文件的路径还是目录的路径
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath)
    {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists())
        {
            if (fileOrPath.isDirectory())
            {
                File[] files = fileOrPath.listFiles();
                for (File file : files)
                {
                    file.delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static void main(String[] args) throws IOException
    {
        Thumbnails.of(new File("C:\\Users\\Lenovo\\Desktop\\targetimg.jpg"))
                .size(200, 200)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                .outputQuality(0.8f).toFile("C:\\Users\\Lenovo\\Desktop\\targetimgnew.jpg");

    }

    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr)
    {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        //相对路径
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is "+relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete Addr is "+PathUtil.getImgBasePath() + relativeAddr);

        try{
            Thumbnails.of(thumbnail.getImage()).size(337,640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e)
        {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }
}
