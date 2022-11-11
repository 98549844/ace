package com.service;

import com.constant.AceEnvironment;
import com.models.entity.Files;
import com.models.entity.Roles;
import com.models.entity.Users;
import com.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Classname: GalleryService
 * @Date: 2022/9/19 上午 09:40
 * @Author: kalam_au
 * @Description:
 */


@Service
public class GalleryService {
    private static final Logger log = LogManager.getLogger(GalleryService.class.getName());

    private FilesService filesService;
    private UserRolesService userRolesService;
    private RolesService rolesService;
    private final String imagePath;
    private final String imagesThumbnail;


    @Autowired
    public GalleryService(FilesService filesService, UserRolesService userRolesService, RolesService rolesService) {
        this.filesService = filesService;
        this.userRolesService = userRolesService;
        this.rolesService = rolesService;
        this.imagePath = AceEnvironment.getImagesPath();
        this.imagesThumbnail = AceEnvironment.getImagesThumbnail();
    }

    public List getImages(Users users) throws IOException {
        log.info("image location: {}", imagePath);

        List<String> ls = FileUtil.getFileNames(imagePath);
        List<String> tempLs = FileUtil.getFileNames(imagesThumbnail);

        try {
            Map mp = ListUtil.getDeduplicateElements(ls, tempLs);
            compressImages((List<String>) mp.get(ListUtil.LIST_1));
            tempLs = (List<String>) mp.get(ListUtil.LIST_2);
            if (NullUtil.isNotNull(tempLs)) {
                for (String s : tempLs) {
                    FileUtil.delete(imagesThumbnail + s);
                }
            }
        } catch (Exception e) {
            log.warn("Image still compressing, not ready to display ....");
            e.printStackTrace();
        } finally {
            //根据folder实际文件控制数据库, 删除folder不存文件数据
            List<String> fName = FileUtil.getNames(ls);
            List<Files> filesList = filesService.findFilesByPathAndFileNameNotIn(imagePath, fName);
            filesService.deleteAll(filesList);

            List<Roles> rolesList = rolesService.getRolesByUser(users.getUserId());

            //只处理单角色,多角色及后再新增处理
            if (Roles.ADMIN.equals(rolesList.get(0).getRoleCode())) {
                //根据数据库排序
                return filesService.findFilesByFileNameInAndStatusOrderByCreatedDateDesc(fName, Files.COMPRESSED);
            } else {
                //根据数据库排序
                return filesService.findFilesByFileNameInAndStatusAndOwnerOrderByCreatedDateDesc(fName, Files.COMPRESSED, users.getUserId().toString());
            }
            //根据文件排序
            // return FileUtil.getNamesOrderByLastModifiedDate(imagesThumbnail, true);
        }
    }

    private void compressImages(List<String> ls) {
        log.info("temp images expired, compressing image ...");
        if (NullUtil.isNull(ls)) {
            return;
        }
        try {
            ImageUtil imageUtil = new ImageUtil();
            for (String name : ls) {
                imageUtil.square(imagePath + name, true);
                ImageUtil.compress(imagesThumbnail + name);
                Files f = filesService.findFilesByFileName(FileUtil.getName(name));
                f.setStatus(Files.COMPRESSED);
                filesService.save(f);
            }
        } catch (Exception e) {
            log.error("Include non image files !!!");
            e.printStackTrace();
        }
        log.info("compressing image complete !!!");
    }

    public Files rotate(String direction, String uuid) throws Exception {
        int rotate;
        if ("left".equals(direction)) {
            rotate = -90;
        } else {
            rotate = 90;
        }
        String newUuid = UUID.get();
        Files f = filesService.findFilesByFileName(uuid);
        rename(f.getLocation(), imagePath + newUuid + f.getExt());

        String temp = imagesThumbnail + f.getFileName() + f.getExt();
        String newTemp = imagesThumbnail + newUuid + f.getExt();

        f.setFileName(newUuid);
        f.setRemark("Ace Application UUID: " + newUuid);
        ImageUtil.rotation(temp, newTemp, rotate);

        filesService.delFile(temp);
        return filesService.saveAndFlush(f);

    }

    private static void rename(String src, String desc) throws Exception {
        log.info("Start rename file");
        // 旧的文件或目录
        File oldName = new File(src);
        // 新的文件或目录1
        File newName = new File(desc);
        if (newName.exists()) {  //  确保新的文件名不存在
            throw new java.io.IOException("target file exists !!!");
        }
        if (oldName.renameTo(newName)) {
            log.info("File renamed success => {}", desc);
        } else {
            log.error("File rename fail !!!");
        }
    }
}

