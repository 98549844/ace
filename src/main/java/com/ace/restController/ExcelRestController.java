package com.ace.restController;

import com.ace.models.common.RespResult;
import com.ace.models.entity.Columns;
import com.ace.models.entity.Users;
import com.ace.service.DataBaseService;
import com.ace.service.UsersService;
import com.ace.utilities.EasyExcelUtil;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: ExcelRestController
 * @Date: 6/12/2021 3:18 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/easyExcel")
@Tag(name = "EasyExcel")
public class ExcelRestController {
    private static final Logger log = LogManager.getLogger(ExcelRestController.class.getName());

    //final static String mac_path = "/Users/garlam/IdeaProjects/ace/src/main/resources/files/output/";
    //final static String mac_fileName = mac_path + "excel.xls";

    final static String windows_path = "C:\\Users\\Garlam.Au\\IdeaProjects\\ace\\src\\main\\resources\\files\\output\\";
    final static String fileName = windows_path + "excel.xls";


    private final DataBaseService dataBaseService;
    private final UsersService usersService;


    @Autowired
    public ExcelRestController(DataBaseService dataBaseService, UsersService usersService) {
        this.dataBaseService = dataBaseService;
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/generate/{table}")
    public RespResult generateTableToExcel(@PathVariable String table) throws ClassNotFoundException {

        List<Columns> list = dataBaseService.getColumnName(table);
        List<String> columns = new ArrayList<>();
        for (Columns c : list) {
            columns.add(c.getColumnName());
        }
        List<Users> users = usersService.findAll();

        EasyExcelUtil easyExcelUtil = new EasyExcelUtil();
        easyExcelUtil.write(fileName, users, new Users());
        return RespResult.success(list);
    }


    final static String xlsFile = "C:\\Users\\Garlam.Au\\IdeaProjects\\ace\\src\\main\\resources\\files\\output\\excel.xls";
    final static String xlsSheetName = "template";

    final static String xlsxFile = "C:\\Users\\Garlam.Au\\IdeaProjects\\ace\\src\\main\\resources\\files\\output\\ORDER-TDF-FW24-MFO.xlsx";
    final static String xlsxSheetName = "PO Plan";

    //會throw Could not initialize class com.alibaba.excel.analysis.v07.XlsxSaxAnalyser
    //Exception java.lang.NoClassDefFoundError: org/apache/poi/util/POILogFactory
    @RequestMapping(method = RequestMethod.GET, value = "/read/{order}")
    public RespResult read(@PathVariable int order) throws ClassNotFoundException {
        String p = xlsFile;
        String sheetName = xlsSheetName;

        if (order == 1) {
            p = xlsxFile;
            sheetName = xlsxSheetName;
        }

        EasyExcelUtil easyExcelUtil = new EasyExcelUtil();
        easyExcelUtil.read(p);
        ExcelReaderSheetBuilder excelReaderSheetBuilder = easyExcelUtil.getSheet(p, sheetName);

        ReadSheet readSheet = excelReaderSheetBuilder.build();
        System.out.println(readSheet);
        System.out.println("1." + readSheet.getCustomConverterList());
        System.out.println("2." + readSheet.getCustomReadListenerList());
        System.out.println("3." + readSheet.getHead());
        System.out.println("4." + readSheet.getHeadRowNumber());

        return RespResult.success(excelReaderSheetBuilder.build());
    }

}

