package com.ace.restController;

import com.ace.constant.AceEnvironment;
import com.ace.models.common.RespResult;
import com.ace.models.entity.Files;
import com.ace.service.FilesService;
import com.ace.service.UsersService;
import com.ace.utilities.FileUtil;
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
    private final AceEnvironment aceEnvironment;


    public FilesRestController(UsersService usersService, FilesService filesService, AceEnvironment aceEnvironment) {
        this.usersService = usersService;
        this.filesService = filesService;
        this.aceEnvironment = aceEnvironment;
    }

    @Operation(summary = "清理用户|拥有者不存在的文件", description = "清理用户|拥有者不存在的文件,但依然存在本地文件夹的文件")
    @RequestMapping(method = RequestMethod.GET, value = "/deleteFilesWithOutOwner.html")
    public RespResult deleteFilesWithOutOwner() {
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
        return RespResult.success(result);
    }


    @Operation(summary = "清理图片库中无记录的图片", description = "清理数据库没有图片记录,但存在在本地文件夹的图片")
    @RequestMapping(method = RequestMethod.GET, value = "/deleteFilesWithOutRecord.html")
    public RespResult deleteFilesWithOutRecord() {
        String imagePath = aceEnvironment.getImagesPath();
        List<String> localFiles = FileUtil.getCurrentFolderAbsoluteFilesPath(imagePath);
        List<String> result = new LinkedList<>();
        int size = 0;
        for (String fileLocation : localFiles) {
            int count = filesService.countByLocation(fileLocation);
            if (count == 0) {
                //文件存在, 但是数据库没有记录, 删除文件
                FileUtil.delete(fileLocation);
                result.add(fileLocation);
            }
            ++size;
        }
        result.add("Total deleted files: " + size);
        return RespResult.success(result);
    }

    @Operation(summary = "清空thumbnail缩略图文件夹")
    @RequestMapping(method = RequestMethod.GET, value = "/clearThumbnail.html")
    public RespResult clearThumbnail() {
        String thumbnail = aceEnvironment.getImagesThumbnail();
        //删除ImagesThumbnail文件夹,包括ImagesThumbnail自已下的所有子文件夹和子文件
        FileUtil.deleteDirectories(thumbnail);
        //创建ImagesThumbnail文件夹
        FileUtil.mkDirs(thumbnail);
        return RespResult.success("SUCCESS");
    }

}

