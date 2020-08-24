package com.best.yige.io;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import sun.awt.image.PNGImageDecoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author: beyondyanyi@gmail.com
 * @Description:图片工具类
 * @Date: 10:43 2020/8/24
 * @Modified By:
 */
public class ImageUtil {

    // 水印透明度
    private static float alpha = 0.5f;
    // 水印横向位置
    private static int positionWidth = 150;
    // 水印纵向位置
    private static int positionHeight = 300;
    // 水印文字字体
    private static Font font = new Font("宋体", Font.BOLD, 72);
    // 水印文字颜色
    private static Color color = Color.red;


    //将图片按比例缩放
    /**
     * @param file 文件
     * @param desSuffix 后缀名
     * @param destPath  生成的目标文件路径
     * @param smallRate 缩放比率
     */
    public static void getImageSmall(File file,String desSuffix,String destPath,double smallRate){
        try {
            //读取图片流
            BufferedImage bufferedImage = ImageIO.read(file);
            //按比例缩放
            int height=bufferedImage.getHeight();
            int width=bufferedImage.getWidth();
            height= (int) (height*smallRate);
            width= (int) (width*smallRate);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = image.getGraphics();
            graphics.drawImage(bufferedImage, 0, 0, width, height, Color.LIGHT_GRAY, null);
            graphics.dispose();
            ImageIO.write(image, desSuffix, new File(destPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //生成图片水印
    /**
     *
     * @param iconPath 水印图片路径
     * @param srcPath  源图片路径
     * @param destPath 目标图片路径
     * @param degree   水印图片旋转角度
     */
    public static void getImageWatermark(String iconPath, String srcPath,
                                         String destPath, Integer degree){
        OutputStream os = null;
        try {

            Image srcImg = ImageIO.read(new File(srcPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 1、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 2、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(
                    srcImg.getScaledInstance(srcImg.getWidth(null),
                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
                    null);
            // 3、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2,
                        (double) buffImg.getHeight() / 2);
            }

            // 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);
            // 5、得到Image对象。
            Image img = imgIcon.getImage();
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // 6、水印图片的位置
            g.drawImage(img, positionWidth, positionHeight, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            // 7、释放资源
            g.dispose();
            // 8、生成图片
            os = new FileOutputStream(destPath);
            ImageIO.write(buffImg, "JPG", os);
            System.out.println("图片完成添加水印图片");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     *
     * @param logoText
     * @param srcPath
     * @param destPath
     * @param degree
     */
    public static void markImageByText(String logoText, String srcPath,
                                       String destPath, Integer degree) {
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、源图片
            Image srcImg = ImageIO.read(new File(srcPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(
                    srcImg.getScaledInstance(srcImg.getWidth(null),
                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
                    null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2,
                        (double) buffImg.getHeight() / 2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            g.drawString(logoText, positionWidth, positionHeight);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(destPath);
            ImageIO.write(buffImg, "JPG", os);

            System.out.println("图片完成添加水印文字");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        //图片缩放
//        File file=new File("C:\\Users\\Administrator\\Desktop\\uploadDir\\1c485c3b-2a1b-473e-abd7-358512e1ed26.jpg");
//        if (!file.exists()) {
//            System.out.println("文件不存在");
//        }
//        getImageSmall(file,"png","C:\\Users\\Administrator\\Desktop\\small.png",0.2);
        //图片水印
        //getImageWatermark("C:\\Users\\Administrator\\Desktop\\small.png","C:\\Users\\Administrator\\Desktop\\11.jpg","C:\\Users\\Administrator\\Desktop\\22.jpg",0);
        //文字水印
        //markImageByText("logo","C:\\Users\\Administrator\\Desktop\\11.jpg","C:\\Users\\Administrator\\Desktop\\22.jpg",0);
    }
}
