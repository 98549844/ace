package com.restController;

import com.api.Response;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Classname: ApiRestController
 * @Date: 9/5/2021 10:04 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/api")
@Api(tags = "api")
public class ApiRestController {
    private static Logger log = LogManager.getLogger(ApiRestController.class.getName());


    /**
     * feign get 请求<单个参数请求>
     *
     * @param params
     * @return
     * @author songfayuan
     * @date 2019/03/22 18:29
     */
    @GetMapping("/feign/getRequest1")
    public Response getRequest1(@RequestParam("params") String params) {
        log.info("请求参数params = {}", params);
        return Response.success(params);
    }

    /**
     * feign get 请求<多参数请求>
     *
     * @param params1
     * @param params2
     * @return
     * @author songfayuan
     * @date 2019/03/22 18:29
     */
    @GetMapping("/feign/getRequest")
    public Response getRequest(@RequestParam("params1") String params1, @RequestParam("params2") String params2) {
        log.info("请求参数params1 = {}, params2 = {}", params1, params2);
        return Response.success(params1 + "&" + params2);
    }

    /**
     * feign get 请求<单个参数请求>
     *
     * @return
     * @author songfayuan
     * @date 2019/03/25 11:19
     */
    @GetMapping("/feign/getRequest3/{params}")
    public Response getRequest3(@PathVariable("params") String params) {
        log.info("请求参数params = {}", params);
        return Response.success(params);
    }

    /**
     * feign post 请求<单参、多参数请求>
     *
     * @param params
     * @return
     * @author songfayuan
     * @date 2019/03/22 18:29
     */
    @PostMapping("/feign/postRequest")
    public Response feignPostRequest(@RequestBody Map<String, Object> params) {
        log.info("请求参数params = {}", params);
        return Response.success(params);
    }

    /**
     * feign post 请求<单参数请求>
     *
     * @param params
     * @return
     * @author songfayuan
     * @date 2019/03/25 11:00
     */
    @PostMapping("/feign/postRequest2")
    public Response feignPostRequest2(@RequestParam("params") String params) {
        log.info("请求参数params = {}", params);
        return Response.success(params);
    }



 /*   @RequestMapping(method = RequestMethod.GET, value = "/get")
    public static void get() throws Exception {
        StringBuilder sb = new StringBuilder("http://localhost:8088/api");
        Map<String, String> params = new HashMap<String, String>();
        params.put("bxm_id", "前端传过来的bxm_de的值");
        params.put("status", "1");//固定值
        params.put("modeltype", "7");//固定值
        String result = GetPostUrl(sb.toString(), params, "GET", null, 0, 0);
        System.out.println(result);

    }

    public static String GetPostUrl(String sendUrl, Map<String, String> params, String sendType, String charset, int repeat_request_count, int repeat_request_max_count) {
        URL url = null;
        HttpURLConnection httpurlconnection = null;

        try {
            // 构建请求参数
            StringBuffer paramSb = new StringBuffer();
            if (params != null) {
                for (Map.Entry<String, String> e : params.entrySet()) {
                    paramSb.append(e.getKey());
                    paramSb.append("=");
                    // 将参数值urlEncode编码,防止传递中乱码
                    paramSb.append(URLEncoder.encode(e.getValue(), "UTF-8"));
                    paramSb.append("&");
                }
                paramSb.substring(0, paramSb.length() - 1);
            }
            url = new URL(sendUrl + "?" + paramSb.toString());
            httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setRequestMethod("GET");
            httpurlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(true);

            // 设置http请求超时时间30000毫秒（30秒）
            httpurlconnection.setConnectTimeout(30000);
            httpurlconnection.setReadTimeout(30000);
            httpurlconnection.setUseCaches(true);


            int code = httpurlconnection.getResponseCode();
            if (code == 200) {
                DataInputStream in = new DataInputStream(httpurlconnection.getInputStream());
                int len = in.available();
                byte[] by = new byte[len];
                in.readFully(by);
                String rev = new String(by, "UTF-8");

                in.close();

                return rev;
            } else {
                // http 请求返回非 200状态时处理
                return "<?xml version=\"1.0\" encoding=\"utf-8\" ?><error>发送第三方请求失败</error>";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpurlconnection != null) {
                httpurlconnection.disconnect();
            }
        }
        return null;
    }
*/
}

