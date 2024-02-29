package com.ace.restController;

import com.ace.models.common.AjaxResponse;
import com.ace.util.AnnotationMapperUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname: UrlMapperUtilRestController
 * @Date: 2023/1/13 上午 09:51
 * @Author: kalam_au
 * @Description:
 */

@RestController
@RequestMapping("/rest/url")
//@Api(tags = "Url Mapping")
@Tag(name = "AnnotationMapperUtil")
@EnableConfigurationProperties
public class UrlMapperUtilRestController {
    private static final Logger log = LogManager.getLogger(UrlMapperUtilRestController.class.getName());


    private final ApplicationContext applicationContext;

    @Autowired
    public UrlMapperUtilRestController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public AjaxResponse getUrlMapping() {
        AnnotationMapperUtil annotationMapperUtil = new AnnotationMapperUtil();
        List<String> url = annotationMapperUtil.getUrl();
        return AjaxResponse.success(url);
    }

}

