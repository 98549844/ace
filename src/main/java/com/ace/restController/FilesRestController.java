package com.ace.restController;

import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Files;
import com.ace.service.FilesService;
import com.ace.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;


/**
 * @Classname: FilesRestController
 * @Date: 15/5/24 PM2:25
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/files")
@Tag(name = "Files")
public class FilesRestController {
    private static final Logger log = LogManager.getLogger(FilesRestController.class.getName());

    private final UsersService usersService;
    private final FilesService filesService;

    public FilesRestController(UsersService usersService, FilesService filesService) {
        this.usersService = usersService;
        this.filesService = filesService;
    }

    @Operation(summary = "清理用户不存在的文件")
    @RequestMapping(method = RequestMethod.GET, value = "/delete.html")
    public AjaxResponse delete() {
        List<String> owners = filesService.getAllDistinctOwner();
        List<String> result = new LinkedList<>();
        for (String owner : owners) {
            int isExist = usersService.countByUserAccount(owner);
            result.add(owner);
            if (isExist == 0) {
                List<Files> ls = filesService.findFilesByOwner(owner);
                for (Files file : ls) {
                    result.add(file.getOriginationName());
                }
                //用户不存在,删除file数据
                filesService.deleteByOwner(owner);
                //然后删除文件
                filesService.delFileList(ls);
            }
        }
        return AjaxResponse.success(result);
    }
}

