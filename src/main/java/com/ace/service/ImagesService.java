package com.ace.service;

import com.ace.constant.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.Files;
import com.ace.models.entity.Roles;
import com.ace.models.entity.Users;
import com.ace.utilities.FileUtil;
import com.ace.utilities.ImageUtil;
import com.ace.utilities.ListUtil;
import com.ace.utilities.NullUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Classname: ImagesService
 * @Date: 28/11/2023 1:18 am
 * @Author: garlam
 * @Description:
 */

@Service
public class ImagesService extends CommonController {
    private static final Logger log = LogManager.getLogger(ImagesService.class.getName());


    private final FilesService filesService;
    private final RolesService rolesService;
    private final String imagePath;
    private final String imagesThumbnail;

    public ImagesService(AceEnvironment aceEnvironment, FilesService filesService, RolesService rolesService) {
        this.filesService = filesService;
        this.rolesService = rolesService;
        this.imagePath = aceEnvironment.getImagesPath();
        this.imagesThumbnail = aceEnvironment.getImagesThumbnail();
    }


    public List getImages(Users users) {
        log.info("image location: {}", imagePath);

        List<String> ls = FileUtil.getFileNamesWithExt(imagePath);
        List<String> thumbnailLs = FileUtil.getFileNamesWithExt(imagesThumbnail);

        try {
            deleteThumbnails(ls, thumbnailLs); //删除缩略图, 如果原图不存在
        } catch (Exception e) {
            log.warn("Image still compressing, not ready to display ....");
            e.printStackTrace();
        }
        //根据folder实际文件控制数据库, 删除folder不存文件数据
        List<String> fName = FileUtil.getNames(ls);
        List<Files> filesList = filesService.findFilesByPathAndFileNameNotIn(imagePath, fName);
        filesService.deleteAll(filesList);

        List<Roles> rolesList = rolesService.getRolesByUserId(users.getUserId());

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

    public List<Files> getImagesByLimit(Users user, int paging) {
        List<Files> filesLs;
        if (user.getRoleGroup().contains(Users.ADMIN)) {
            //Admin roles access all images
            filesLs = filesService.findFilesByStatusOrderByOwnerAscCreatedDateDesc(Files.COMPRESSED, paging);
        } else {
            filesLs = filesService.findFilesByStatusAndOwnerOrderByCreatedDateDesc(Files.COMPRESSED, user.getUserAccount(), paging);
        }
        List<Files> result = new ArrayList<>();
        for (Files f : filesLs) {
            File original = new File(f.getLocation()); //原图
            File thumbnail = new File(imagesThumbnail + f.getFileName() + f.getExt()); //缩略图
            if (original.exists() && !f.getFileName().contains(user.getUserAccount())) {
                //文件名包含userAccount,定义为头像
                result.add(f);
                if (!thumbnail.exists()) {
                    compressImage(f); //缩略图不存在, 生成缩略图
                }
            } else if (!original.exists() && thumbnail.exists()) {
                //删除缩略图, 当原图不存在
                if (thumbnail.delete()) {
                    //更新数据
                    f.setStatus(Files.LOST);
                    filesService.save(f);
                }
            }
        }
        return result;
    }

    public List<Files> getImagesByFileName(String fileName) {
        List<Files> result = new ArrayList<>();
        Files f = filesService.findFilesByFileName(fileName);
        result.add(f);
        return result;
    }

    public List<Files> getFilesByFileNameLike(String fileName) {
        return filesService.findFilesByFileNameLike(fileName);
    }


    /**
     * 根据文件名读取文件
     *
     * @param fileName
     * @param response
     * @throws IOException
     */
    public void get(String location, String fileName, HttpServletResponse response) throws IOException {
        String name;
        String ext;
        if (!fileName.contains(".")) {
            //不包含".", 查数据库获获
            Files f = filesService.findFilesByFileName(fileName);
            ext = f.getExt().split("\\.")[1];
            name = fileName + f.getExt();
        } else {
            name = fileName;
            ext = fileName.split("\\.")[1];
        }
        System.out.println("file location: " + location + name);
        ImageIO.write(ImageIO.read(new File(location + name)), ext, response.getOutputStream());
    }

    /**
     * 缩略图转圏
     */
    public Files rotateDesc(String direction, String descPath, String uuid, String newUuid) throws Exception {
        int rotate;
        if ("left".equals(direction)) {
            rotate = -90;
        } else {
            rotate = 90;
        }
        //  String newUuid = UUID.get();
        Files f = filesService.findFilesByFileName(uuid);
        rename(f.getLocation(), imagePath + newUuid + f.getExt());

        String image = descPath + f.getFileName() + f.getExt();
        String newImage = descPath + newUuid + f.getExt();

        f.setFileName(newUuid);
        f.setRemark("Ace Application UUID: " + newUuid);
        ImageUtil.rotation(image, newImage, rotate);

        filesService.delFile(image);
        return filesService.saveAndFlush(f);

    }

    /**
     * 原图自转圏
     */
    public Files rotate(String direction, String uuid, String newUuid) throws Exception {
        int rotate;
        if ("left".equals(direction)) {
            rotate = -90;
        } else {
            rotate = 90;
        }
        Files f = filesService.findFilesByFileName(uuid);
        String image = f.getPath() + f.getFileName() + f.getExt();
        String newImage = f.getPath() + newUuid + f.getExt();
        ImageUtil.rotation(f.getLocation(), newImage, rotate);

        f.setFileName(newUuid);
        f.setRemark("Ace Application UUID: " + newUuid);
        ImageUtil.rotation(image, newImage, rotate);

        filesService.delFile(image);
        return filesService.saveAndFlush(f);
    }

    public void compressImage(Files f) {
        log.info("Compressing thumbnail ...");
        if (NullUtil.isNull(f)) {
            log.warn("Image not exist !!!");
            return;
        }
        try {
            ImageUtil imageUtil = new ImageUtil();
            imageUtil.square(f.getLocation(), imagesThumbnail);
            ImageUtil.compress(f.getPath() + f.getFileName() + f.getExt());
            f.setStatus(Files.COMPRESSED);
            filesService.save(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("compressing thumbnail complete !!!");
    }

    private void compressImages(List<String> ls) {
        log.info("thumbnail expired, compressing image ...");
        if (NullUtil.isNull(ls)) {
            return;
        }
        try {
            ImageUtil imageUtil = new ImageUtil();
            for (String name : ls) {
                String location = imagePath + name;
                if (FileUtil.isImage(location)) {
                    imageUtil.square(location, imagesThumbnail);
                    ImageUtil.compress(imagesThumbnail + name);
                    Files f = filesService.findFilesByFileName(FileUtil.getName(name));
                    f.setStatus(Files.COMPRESSED);
                    filesService.save(f);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("compressing image complete !!!");
    }

    private void deleteThumbnails(List<String> ls, List<String> thumbnailList) {
        Map mp = ListUtil.getNonDeduplicateElements(ls, thumbnailList);
        compressImages((List<String>) mp.get(ListUtil.LIST_1)); //原图有, 但缩略图没有, 并压缩生成缩略图
        thumbnailList = (List<String>) mp.get(ListUtil.LIST_2); //原图没有, 但缩略图存在
        if (NullUtil.isNonNull(thumbnailList)) {
            //原图已删, 删除掉缩略图
            for (String s : thumbnailList) {
                FileUtil.delete(imagesThumbnail + s);
            }
        }
    }


    private static void rename(String src, String desc) throws IOException {
        // 旧的文件或目录
        File oldName = new File(src);
        // 新的文件或目录
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

