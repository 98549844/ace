package com.ace.service;

import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

import static com.ace.constants.constant.PNG;


/**
 * @Classname: QRCodeService
 * @Date: 13/7/24 PM10:23
 * @Author: garlam
 * @Description:
 */

//https://blog.csdn.net/qq_27292113/article/details/109101878
//https://mp.weixin.qq.com/s/fJadh77_t5PrpKGkVLhBpQ
@Service
public class QRCodeService {
    private static final Logger log = LogManager.getLogger(QRCodeService.class.getName());

    int width = 250;
    int height = 250;

    /**
     * 生成包含指定内容的二维码图片，并返回该二维码的 Base64 编码表示.
     *
     * @param text 二维码内容
     * @return 二维码图片的 Base64 编码
     */
    public String generateQRCodeImage(String text) {
        try {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Hashtable<EncodeHintType, Object> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // 生成二维码图片
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);

            //BitMatrix 转换为 BufferedImage
            BufferedImage bufferedImage = toBufferedImage(bitMatrix);

            // 将图片转换为 Base64 编码
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, PNG, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            return Base64.getEncoder().encodeToString(byteArray);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("生成二维码图片时出现错误：" + e.getMessage());
        }
    }

    /**
     * 给二维码添加logo
     *
     * @return java.awt.image.BufferedImage
     * @Date 2023/09/24 22:33
     * @Param [bufferedImage, logoFile]
     */
    private BufferedImage addQrCodeLogo(BufferedImage bufferedImage, File logoFile) throws IOException {
        Graphics2D graphics = bufferedImage.createGraphics();
        int matrixWidth = bufferedImage.getWidth();
        int matrixHigh = bufferedImage.getHeight();

        // 读取logo图片文件
        BufferedImage logo = ImageIO.read(logoFile);
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        //  计算logo放置位置
        int x = bufferedImage.getWidth() / 5 * 2;
        int y = bufferedImage.getHeight() / 5 * 2;
        int width = matrixWidth / 5;
        int height = matrixHigh / 5;

        // 开始绘制图片
        graphics.drawImage(logo, x, y, width, height, null);
        graphics.drawRoundRect(x, y, logoWidth, logoHeight, 15, 15);
        graphics.setStroke(new BasicStroke(5.0F, 1, 1));
        graphics.setColor(Color.white);
        graphics.drawRect(x, y, logoWidth, logoHeight);

        graphics.dispose();
        bufferedImage.flush();
        return bufferedImage;
    }


    /**
     * 转换为BufferedImage
     *
     * @return java.awt.image.BufferedImage
     * @Date 2023/09/24 22:32
     * @Param [bitMatrix]
     */
    private BufferedImage toBufferedImage(BitMatrix bitMatrix) throws IOException, WriterException {
        //MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(); //默认黑白色
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
        return bufferedImage;
    }


}

