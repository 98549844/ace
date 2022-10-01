package com.service;

import cn.dev33.satoken.stp.StpUtil;
import com.constant.AceEnvironment;
import com.dao.FilesDao;
import com.models.entity.dao.Files;
import com.models.entity.dao.Users;
import com.util.FileUtil;
import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Classname: FileService
 * @Date: 2022/9/27 上午 11:42
 * @Author: kalam_au
 * @Description:
 */


@Service
public class FilesService {
    private static final Logger log = LogManager.getLogger(FilesService.class.getName());

    private FilesDao filesDao;

    @Autowired
    public FilesService(FilesDao filesDao) {
        this.filesDao = filesDao;
    }

    public List<Files> saveAll(List<Files> files) {
        return filesDao.saveAll(files);
    }

    public List<Files> findFilesByFileNameNotInOrderByLastUpdateDateDesc(List<String> fileList) {
        if (fileList.size() == 0) {
            //避免list == 0时query null data 情况
            log.info("folder is empty");
            fileList.add("");
        }
        List<Files> fs = new ArrayList<>();
        try {
            fs = filesDao.findFilesByFileNameNotInOrderByLastUpdateDateDesc(fileList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fs;
    }

    public void deleteAll(List<Files> fs) {
        filesDao.deleteAll(fs);
    }

    public void delete(Files fs) {
        filesDao.delete(fs);
    }

    /**
     * 处理图片显示请求
     * 响应输出图片文件
     *
     * @param fileName
     * @param response
     * @return
     */
    public String getFile(String fileName, HttpServletResponse response) {
        //设置文件路径
        File file = new File(AceEnvironment.getFilePath() + fileName);
        if (!NullUtil.isNull(file) && file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally { // 做关闭操作
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "下载失败";
    }

    /**
     * 处理图片显示请求
     * 响应输出图片文件
     *
     * @param fileName
     * @param response
     */
    public void get(String fileName, HttpServletResponse response) {
        File imgFile = new File(fileName);
        try {
            InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();
            byte[] buffer = new byte[1024]; // 图片文件流缓存池
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
            os.flush();
            os.close();
            is.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String upload(String storageLocation, MultipartFile file) {    //注意参数
        try {
            if (file.isEmpty()) {
                return "file is empty";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);//写日志
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);//写日志
            // 设置文件存储路径
            String path = storageLocation + fileName;
            File dest = new File(new File(path).getAbsolutePath());// dist为文件，有多级目录的文件
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {//因此这里使用.getParentFile()，目的就是取文件前面目录的路径
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return "upload success";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload fail";
    }

    public List uploads(MultipartFile[] files, String uuid) {
        // 存储上传成功的文件名，响应给客户端
        List<String> list = new ArrayList<>();
        // 判断文件数组长度
        if (files.length <= 0) {
            list.add("请选择文件");
            return list;
        }
        Users users = (Users) StpUtil.getSession().get("user");
        List<Files> fs = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            // 源文件名
            String originalFilename = multipartFile.getOriginalFilename();
            // 文件格式
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 新文件名，避免文件名重复，造成文件替换问题
            if (NullUtil.isNull(uuid)) {
                log.warn("uuid provide empty, generate by UUID.randomUUID()!!!");
                uuid = UUID.randomUUID().toString();
            }
            String fileName = uuid + suffix;
            // 文件存储全路径
            String path = AceEnvironment.getImagesPath();
            File targetFile = new File(path + fileName);

            // 判断文件存储目录是否存在，不存在则新建目录
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdir();
            }
            try {
                // 将图片保存
                multipartFile.transferTo(targetFile);
                list.add(fileName);
            } catch (IOException e) {
                log.error("文件上传异常: " + e);
            }

            Files f = new Files();
            f.setOriginationName(originalFilename);
            f.setExt(suffix);
            f.setFileName(fileName);
            f.setLocation(path + fileName);
            f.setOwner(users.getUserId().toString());
            f.setSize((multipartFile.getSize() / 1024));
            fs.add(f);
        }
        saveAll(fs);
        return list;
    }

    public boolean delete(String fileName) {
        Files fs = filesDao.findFilesByFileName(fileName);
        filesDao.delete(fs);
        String fName = AceEnvironment.getImagesPath() + fs.getFileName();
        if (!FileUtil.delete(fName)) {
            log.error("delete file fail => {}", fName);
            return false;
        }
        return true;
    }
}



