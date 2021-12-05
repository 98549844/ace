package com.restController;

import com.Ace;
import com.config.SpringSecurityConfig;
import com.mapper.DataBaseMapper;
import com.models.common.AjaxResponse;
import com.service.DataBaseService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;

/**
 * @Classname: ExcelRestController
 * @Date: 6/12/2021 3:18 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/excel")
@Api(tags = "excel")
public class ExcelRestController {
    private static Logger log = LogManager.getLogger(ExcelRestController.class.getName());

    final static String path = "/Users/garlam/IdeaProjects/utilities/src/main/resources/file/output/";
    final static String fileName = "excel.xls";

    private DataBaseService dataBaseService;


    @Autowired
    public ExcelRestController(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{table}")
    public AjaxResponse generateDbToExcel(@PathVariable String table) throws ClassNotFoundException {

        List<String> list = dataBaseService.getAllTableName();
        return AjaxResponse.success(list);
    }

}

