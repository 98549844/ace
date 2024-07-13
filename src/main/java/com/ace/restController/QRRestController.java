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
public class QRRestController {
    private static final Logger log = LogManager.getLogger(QRRestController.class.getName());

    private QRCodeService qrCodeService;

    public QRRestController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    /**
     * 嵌入  <img src="data:image/png;base64,*base64编码字符串*">
     * <img src="data:image/jpeg;base64,*base64编码字符串*">.
     * <p>
     * 生成产品信息的二维码
     *
     * @param data 产品信息（如：生产日期、地点等）
     * @return 包含二维码图片的 Base64 编码字符串
     */
    @Operation(summary = "生成orCode base64编码字符串")
    @GetMapping(value = "/generateQR.html")
    public String generateQR(@RequestParam String data) {
        String qrCodeImage = qrCodeService.generateQRCodeImage(data);
        System.out.println(qrCodeImage);
        return qrCodeImage;
    }
}

/*
简单qrcode html代码：
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>QR 码生成示例</title>
</head>
<body>
<h1>QR 码生成示例</h1>
    <form id="productForm">
        <label for="productInfo">产品信息：</label>
        <input type="text" id="productInfo" name="productInfo" required>
        <button type="submit">生成二维码</button>
    </form>
    <div id="qrCodeContainer"></div>

        <script>
        document.getElementById('productForm').addEventListener('submit', function (event) {
    event.preventDefault();
            const productInfo = document.getElementById('productInfo').value;
    fetch(`/api/v1/qr/generate?productInfo=${encodeURIComponent(productInfo)}`)
            .then(response => response.text())
            .then(data => {
                const img = document.createElement('img');
    img.src = `data:image/png;base64,${data}`;
                const container = document.getElementById('qrCodeContainer');
    container.innerHTML = ''; // 清空之前的图片
    container.appendChild(img);
            })
            .catch(error => console.error('生成二维码时发生错误:', error));
});
    </script>
</body>
</html>
*/
