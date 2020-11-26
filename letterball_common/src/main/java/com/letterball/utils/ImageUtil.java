package com.letterball.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 图片处理工具类
 */
@Service
public class ImageUtil {

    /**
     * 缩放后大小，默认90D
     */
    private static double base = 90D;

    public static void main(String[] args) throws IOException {
/*        String path = "C:\\Users\\12\\Pictures\\不约.jpg";
        String newPath = "D:\\a.jpg";
        ImageUtil.zoomImageScale(new File(path), newPath);*/

        //String path = "E:\\3200_2000.jpg"; //宽比较长按照宽压缩
        //String newPath = "E:\\test1.jpg";

        String path = "E:\\1080_1920.jpg"; //高比较长按照高压缩
        String newPath = "E:\\test2.jpg";

        ImageUtil.zoomImageScale(new File(path), newPath, 386, 254);

    }


    /**
     * 压缩图片到默认大小
     *
     * @param imageFile
     * @param newPath
     * @throws IOException
     */
    public void zoomImageScale(File imageFile, String newPath) throws IOException {

        if (!imageFile.canRead()) {
            return;
        }
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        if (null == bufferedImage) {
            return;
        }

        int originalWidth = bufferedImage.getWidth();
        int originalHeight = bufferedImage.getHeight();
        int baseLength = Math.max(originalWidth, originalHeight);
        double scale = (double) baseLength / base; // 缩放的比例

        int newWidth = originalWidth == baseLength ? (int) base : (int) (originalWidth / scale);
        int newHeight = originalHeight == baseLength ? (int) base : (int) (originalHeight / scale);

        zoomImageUtils(imageFile, newPath, bufferedImage, newWidth, newHeight);
    }

    /**
     * 压缩图片到指定宽高度
     *
     * @param imageFile
     * @param newPath
     * @param width
     * @param height
     * @throws IOException
     */
    public static void zoomImageScale(File imageFile, String newPath, double width, double height) throws IOException {
        if (!imageFile.canRead()) {
            return;
        }
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        if (null == bufferedImage) {
            return;
        }

        int originalWidth = bufferedImage.getWidth();
        int originalHeight = bufferedImage.getHeight();
        int baseLength = Math.max(originalWidth, originalHeight);
        double scale = (double) baseLength / width; // 缩放的比例

        int newWidth = originalWidth == baseLength ? (int) width : (int) (originalWidth / scale);
        int newHeight = originalHeight == baseLength ? (int) height : (int) (originalHeight / scale);

        if (newWidth == 0) {
            newWidth = 1;
        }
        if (newHeight == 0) {
            newHeight = 1;
        }

        zoomImageUtils(imageFile, newPath, bufferedImage, newWidth, newHeight);
    }


    private static void zoomImageUtils(File imageFile, String newPath, BufferedImage bufferedImage, int width,
                                       int height) throws IOException {

        String suffix = StringUtils.substringAfterLast(imageFile.getName(), ".");

        // 处理 png 背景变黑的问题
        if (suffix != null
                && (suffix.trim().toLowerCase().endsWith("png") || suffix.trim().toLowerCase().endsWith("gif"))) {
            BufferedImage to = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            g2d.dispose();

            g2d = to.createGraphics();
            Image from = bufferedImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();

            ImageIO.write(to, suffix, new File(newPath));
        } else {
            // 高质量压缩，其实对清晰度而言没有太多的帮助
            // BufferedImage tag = new BufferedImage(width, height,
            // BufferedImage.TYPE_INT_RGB);
            // tag.getGraphics().drawImage(bufferedImage, 0, 0, width, height,
            // null);
            //
            // FileOutputStream out = new FileOutputStream(newPath); // 将图片写入
            // newPath
            // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            // JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            // jep.setQuality(1f, true); //压缩质量, 1 是最高值
            // encoder.encode(tag, jep);
            // out.close();

            BufferedImage newImage = new BufferedImage(width, height, bufferedImage.getType());
            Graphics g = newImage.getGraphics();
            g.drawImage(bufferedImage, 0, 0, width, height, null);
            g.dispose();
            ImageIO.write(newImage, suffix, new File(newPath));
        }
    }

    /**
     * @Description : 将网络图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param url 图片的url路径，如http://.....xx.jpg
     * @Date : 2018/7/9 15:47
     * @Author : Licf
     * @Modified By : Licf
     */
    public static String encodeImgageToBase64(String url) {
        URL imageUrl = null;
        try {
            imageUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //判断文件格式
        int index = url.lastIndexOf(".");
        String formatName = url.substring(index+1, url.length());
        if("jpg".equalsIgnoreCase(formatName)) {
            formatName = "jpg";
        } else if ("png".equalsIgnoreCase(formatName)) {
            formatName = "png";
        } else if ("JPEG".equalsIgnoreCase(formatName)) {
            formatName = "JPEG";
        } else {
            return null;
        }

        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, formatName, outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }
}