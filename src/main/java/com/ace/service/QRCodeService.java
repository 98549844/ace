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
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;


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


    /**
     * 生成包含指定内容的二维码图片，并返回该二维码的 Base64 编码表示.
     *
     * @param text 二维码内容
     * @return 二维码图片的 Base64 编码
     */
    public String generateQRCodeImage(String text) {
        try {
            int width = 250;
            int height = 250;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Hashtable<EncodeHintType, Object> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // 生成二维码图片
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);

            //BitMatrix 转换为 BufferedImage
            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);

            // 将图片转换为 Base64 编码
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            return Base64.getEncoder().encodeToString(byteArray);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("生成二维码图片时出现错误：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        QRCodeService qrCodeService = new QRCodeService();
        String base64 = qrCodeService.generateQRCodeImage("https://www.baidu.com");
        System.out.println(base64);
    }



}

