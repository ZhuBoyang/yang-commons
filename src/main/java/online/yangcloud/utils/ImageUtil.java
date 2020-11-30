package online.yangcloud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 图像相关工具
 *
 * @author zhuby
 * @since 2020/11/27 3:37 下午
 */

public class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 改变图片的尺寸
     *
     * @param newWidth     新的宽度
     * @param newHeight    新的高度
     * @param filePath     文件源路径
     * @param outputPath   文件输出路径
     * @param imageExtType 输出文件类型
     * @return 是否修改成功
     */
    public static boolean changeSize(int newWidth, int newHeight, String filePath, String outputPath,
                                     String imageExtType) throws IOException {
        BufferedInputStream bis;
        try {
            bis = new BufferedInputStream(new FileInputStream(filePath));
            // 字节流转图片对象
            Image bi = ImageIO.read(bis);
            // 构建图片流
            BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            // 绘制改变尺寸后的图
            tag.getGraphics().drawImage(bi, 0, 0, newWidth, newHeight, null);
            // 输出流
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outputPath));
            ImageIO.write(tag, imageExtType, out);
            bis.close();
            out.close();
            return true;
        } catch (IOException e) {
            logger.info("image error : {}", e.getMessage());
            throw e;
        }
    }

}
