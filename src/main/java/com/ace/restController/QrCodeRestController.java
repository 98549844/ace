package com.ace.restController;

import com.ace.service.QRCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;


/**
 * @Classname: QRRestController
 * @Date: 13/7/24 PM10:48
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/qrcode")
@Tag(name = "QR Code")
public class QrCodeRestController {
    private static final Logger log = LogManager.getLogger(QrCodeRestController.class.getName());

    private QRCodeService qrCodeService;

    public QrCodeRestController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    /**
     * 嵌入  <img src="data:image/png;base64,*base64编码字符串*">
     *      <img src="data:image/jpeg;base64,*base64编码字符串*">.
     *
     * 生成产品信息的二维码
     *
     * @param data 产品信息（如：生产日期、地点等）
     * @return 包含二维码图片的 Base64 编码字符串
     */
    @Operation(summary = "生成orCode base64编码字符串")
    @GetMapping(value = "/generateQR.html")
    public String generateQR(@RequestParam String data) {
        String qrCodeImage = qrCodeService.generateQRCodeImage(data);
        return qrCodeImage;
    }
}
