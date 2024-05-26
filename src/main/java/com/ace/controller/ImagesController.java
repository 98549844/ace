package com.ace.controller;

import com.ace.constant.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.Files;
import com.ace.models.entity.Users;
import com.ace.service.FilesService;
import com.ace.service.ImagesService;
import com.ace.utilities.FileUtil;
import com.ace.utilities.UUID;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


/**
 * @Classname: ImagesController
 * @Date: 28/11/2023 1:04 am
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class ImagesController extends CommonController {
    private static final Logger log = LogManager.getLogger(ImagesController.class.getName());

    private final ImagesService imagesService;
    private final FilesService filesService;
    private final String imagePath;
    private final String imagesThumbnail;

    public ImagesController(AceEnvironment aceEnvironment, FilesService filesService, ImagesService imagesService) {
        this.imagesService = imagesService;
        this.filesService = filesService;
        this.imagePath = aceEnvironment.getImagesPath();
        this.imagesThumbnail = aceEnvironment.getImagesThumbnail();
    }


    /**
     * get all images
     *
     * @return
     */
    @RequestMapping(value = "/getImages.html", method = RequestMethod.GET)
    @ResponseBody
    public List getCurrentUserImages() {
        log.info("access getImages.html");
        List ls = imagesService.getImages(getCurrentUser());
        return ls;
    }


    /**
     * get images limitation
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getImagesByLimit.html/{paging}", method = RequestMethod.GET)
    @ResponseBody
    public List getImagesByLimit(@PathVariable(value = "paging") int paging) throws IOException {
        log.info("access getImagesByLimit.html paging: {}", paging);
        List ls = imagesService.getImagesByLimit(getCurrentUser(), paging);
        return ls;
    }

    /**
     * 响应输出图片文件
     *
     * @param fileName
     */
    @RequestMapping(value = "/image/get/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public void get(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        log.info("access image/get/{}", fileName);
        imagesService.get(fileName, response);
    }

    /**
     * 图片旋转
     *
     * @param direction
     * @param uuid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/thumbnail/rotate/{direction}/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public Files rotate(@PathVariable String direction, @PathVariable String uuid) throws Exception {
        log.info("access image/rotate => rotate {} {}", direction, uuid);
        Files f = imagesService.rotateDesc(direction, imagesThumbnail, uuid, UUID.get());
        return f;
    }


    /**
     * get images limitation
     *
     * @return
     * @throws IOException
     */
   /* @RequestMapping(value = "/getImagesByLimit.html/{paging}/{owner}", method = RequestMethod.GET)
    @ResponseBody
    public List<Files> getImagesByOwnerLimit(@PathVariable(value = "paging") int paging, @PathVariable(value = "owner") String owner) throws IOException {
        log.info("access getImagesByOwnerLimit.html owner paging: {} {}", owner, paging);
        List<Files> ls = imagesService.getImagesByLimit(getCurrentUser(), paging);
        List<Files> ownerLs = new LinkedList<>();
        for (Files f : ls) {
            if (f.getOwner().contains(owner)) {
                ownerLs.add(f);
            }
        }
        return ownerLs;
    }*/

    /**
     * 图片上传
     */
    @RequestMapping(value = "/image/uploads.html", method = RequestMethod.POST)
    @ResponseBody
    public List<String> uploads(@RequestParam(value = "files") MultipartFile[] files, MultipartHttpServletRequest request) {
        String uuid = request.getParameter("uuid");
        log.info("access image/uploads.html => dropzone uuid: {}", uuid);
        List<String> list = filesService.uploads(files, uuid, imagePath);
        return list;
    }

    /**
     * 移除图片
     *
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/image/remove/{uuid}", method = RequestMethod.GET)
    public ModelAndView remove(@PathVariable String uuid) {
        ModelAndView modelAndView = super.page("ace/tool-pages/gallery");
        log.info("access image/remove => delete {}", uuid);
        modelAndView.addObject("delete", filesService.delete(uuid));
        return modelAndView;
    }

    /**
     * 图片删除
     *
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/image/delete/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(@PathVariable String uuid) {
        //  ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");
        log.info("access image/delete => delete {}", uuid);
        boolean rs = filesService.delete(uuid);
        // String result = JsonUtil.ObjectToJson(rs);
        //  modelAndView.addObject("ajaxResult", result);
        return rs;

    }

    /**
     * 删除用户所有图片
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/image/delete/all", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteAll() {
        log.info("access image/delete/all account => {}", getCurrentUser().getUserAccount());
        //只delete 数据库, 没有delete文件
        Users user = getCurrentUser();
        List<Files> ls = filesService.findFilesByOwner(user.getUserId().toString());
        for (Files l : ls) {
            FileUtil.delete(l.getLocation());
            FileUtil.delete(imagesThumbnail + l.getFileName() + l.getExt());
        }
        return filesService.deleteByUserId(user.getUserId());
    }

    /**
     * 下载图片
     *
     * @param uuid
     * @param response
     */
    @RequestMapping(value = "/image/download/{uuid}", method = RequestMethod.GET)
    public void download(@PathVariable String uuid, HttpServletResponse response) {
        log.info("access image/download => download {}", uuid);
        boolean result = filesService.download(uuid, response);
        if (result) {
            log.info("download {} {}", uuid, "success");
        } else {
            log.error("download {} {}", uuid, "fail");
        }
    }

    /**
     * 图片压缩
     *
     * @param uuid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/image/compress/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public boolean compress(@PathVariable String uuid) throws Exception {
        log.info("access image/compress => uuid: {}", uuid);
        imagesService.compressImage(filesService.findFilesByFileName(uuid));
        return true;
    }

}

