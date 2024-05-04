package com.ace.service;

import cn.dev33.satoken.stp.StpUtil;
import com.ace.dao.FilesDao;
import com.ace.models.entity.Files;
import com.ace.models.entity.Users;
import com.ace.utilities.FileUtil;
import com.ace.utilities.NullUtil;
import com.ace.utilities.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.ace.utilities.UUID;

/**
 * @Classname: FileService
 * @Date: 2022/9/27 上午 11:42
 * @Author: kalam_au
 * @Description:
 */


@Service
public class FilesService {
    private static final Logger log = LogManager.getLogger(FilesService.class.getName());

    private final FilesDao filesDao;
    private final int pageSize;

    @Autowired
    public FilesService(FilesDao filesDao) {
        this.filesDao = filesDao;
        this.pageSize = 30;
    }


    /**
     * 保存列表
     *
     * @return
     */
    public List<Files> saveAll(List<Files> files) {
        for (Files file : files) {
            if (NullUtil.isNonNull(file.getId())) {
                //file id not null , update location
                file.setLocation(file.getPath() + file.getFileName() + file.getExt());
            } else {
                Users users = (Users) StpUtil.getSession().get("user");
                file.setOwner(users.getUserId().toString());
            }
        }
        return filesDao.saveAll(files);
    }

    /**
     * 保存
     *
     * @param file
     * @return
     */
    public Files save(Files file) {
        if (NullUtil.isNonNull(file.getId())) {
            //更新location
            file.setLocation(file.getPath() + file.getFileName() + file.getExt());
        } else {
            Users users = (Users) StpUtil.getSession().get("user");
            file.setOwner(users.getUserId().toString());
        }
        return filesDao.save(file);
    }

    public Files saveAndFlush(Files file) {
        if (NullUtil.isNonNull(file.getId())) {
            file.setLocation(file.getPath() + file.getFileName() + file.getExt());
        } else {
            Users users = (Users) StpUtil.getSession().get("user");
            file.setOwner(users.getUserId().toString());
        }
        return filesDao.saveAndFlush(file);
    }

    public List<Files> findFilesByFileNameNotInOrderByLastUpdateDateDesc(List<String> fileList) {
        if (NullUtil.isNull(fileList)) {
            //避免list == 0时query null data 情况
            log.warn("folder is empty !");
            fileList = new ArrayList<>();
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

    public List<Files> findFilesByPathAndFileNameNotIn(String path, List<String> fileNames) {
        if (NullUtil.isNull(fileNames)) {
            //避免list == 0时query null data 情况
            log.warn("folder is empty !!!");
            fileNames = new ArrayList<>();
            fileNames.add("");
        }
        List<Files> fs = new ArrayList<>();
        try {
            fs = filesDao.findFilesByPathAndFileNameNotIn(path, fileNames);
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
    public boolean download(String fileName, HttpServletResponse response) {
        Files f = findFilesByFileName(fileName);
        File file = new File(f.getLocation());
        if (!NullUtil.isNull(fileName) && file.exists()) {
            try {
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=" + f.getOriginationName());
                outputStream(file, response);
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 处理文件流请求, read from jar
     * 响应输出文件
     *
     * @param response
     */
    public void getAsStream(String path, HttpServletResponse response) {
        try {
            outputStream(path, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void outputStream(String propertiesPath, HttpServletResponse response) throws IOException {
        InputStream is = FilesService.class.getResourceAsStream(FileUtil.separator + propertiesPath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024]; // 图片文件流缓存池
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }

        byte[] data = baos.toByteArray();
        OutputStream os = response.getOutputStream();
        os.write(data);

        os.flush();
        os.close();
        is.close();
    }

    /**
     * 处理文件流请求, read from file
     * 响应输出文件
     *
     * @param response
     */
    public void get(String path, HttpServletResponse response) {
        File file = new File(path);
        try {
            outputStream(file, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void outputStream(File file, HttpServletResponse response) throws IOException {
        InputStream is = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[1024]; // 图片文件流缓存池
        while (is.read(buffer) != -1) {
            os.write(buffer);
        }
        os.flush();
        os.close();
        is.close();
    }

    public String upload(String storageLocation, MultipartFile file) { //注意参数
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

    public List uploads(MultipartFile[] files, String uuid, String path) {
        // 存储上传成功的文件名，响应给客户端
        List<String> list = new ArrayList<>();
        // 判断文件数组长度
        if (files.length == 0) {
            list.add("请选择文件");
            return list;
        }
        List<Files> fs = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            Files f = new Files();
            // 源文件名
            String originalFilename = multipartFile.getOriginalFilename();
            // 文件格式
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 新文件名，避免文件名重复，造成文件替换问题
            if (NullUtil.isNull(uuid)) {
                uuid = UUID.get();
                log.info("Ace Application UUID: {}", uuid);
                f.setRemark("ACE Application UUID: " + uuid);
            } else {
                f.setRemark("UUID: " + uuid);
            }

            String fileName = uuid;
            // 文件存储全路径
            File targetFile = new File(path + fileName + suffix);
            // 判断文件存储目录是否存在，不存在则新建目录
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdir();
            }
            try {
                // 将文件保存
                multipartFile.transferTo(targetFile);
                list.add(fileName);
            } catch (IOException e) {
                log.error("文件上传异常: " + e);
            }

            f.setOriginationName(originalFilename);
            f.setExt(suffix.toLowerCase());
            f.setFileName(fileName);
            f.setLocation(path + fileName + suffix);
            f.setPath(path);
            f.setSize((multipartFile.getSize()));
            // f.setStatus(Files.UPLOADED);
            if (FileUtil.isImage(f.getLocation())) {
                f.setType(Files.IMAGE);
            } else if (FileUtil.isVideo(f.getLocation())) {
                f.setType(Files.VIDEO);
            } else {
                log.warn("File type undefine !!!");
            }
            fs.add(f);
        }
        saveAll(fs);
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByUserId(Users user) {
        try {
            filesDao.deleteFilesByOwner(String.valueOf(user.getUserId()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List findFilesByOwner(Users user) {
        List<Files> ls = new ArrayList<>();
        try {
            ls = filesDao.findFilesByOwner(String.valueOf(user.getUserId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }


    public boolean delete(String fileName) {
        Files fs = findFilesByFileName(fileName);
        if (NullUtil.isNull(fs) || NullUtil.isNull(fs.getLocation())) {
            log.info("file record not found, location is: {}", fileName);
            delFile(fileName);
        } else {
            delFile(fs.getLocation()); // 删除文件
            delete(fs); // 删除数据
        }
        return true;
    }

    public boolean delFile(String location) {
        if (FileUtil.delete(location)) {
            return true;
        }
        log.error("delete file fail => {}", location);
        return false;
    }

    public boolean deleteDirectories(String directory) {
        return FileUtil.deleteDirectories(directory);
    }

    public Files findFilesByFileName(String fileName) {
        return filesDao.findFilesByFileName(fileName);
    }

    public List<Files> findFilesByFileNameLike(String fileName) {
        return filesDao.findFilesByFileNameLike(fileName);
    }

    public List<Files> findFilesInFileName(List<String> fileNames) {
        return filesDao.findFilesByFileNameIn(fileNames);
    }

    public List<Files> findFilesByFileNameInAndVersionGreaterThan(List<String> fileNames, int version) {
        return filesDao.findFilesByFileNameInAndVersionGreaterThan(fileNames, version);
    }

    public List<Files> findFilesByFileNameInAndStatus(List<String> fileNames, String status) {
        return filesDao.findFilesByFileNameInAndStatus(fileNames, status);
    }

    public List<Files> findFilesByFileNameInAndStatusOrderByCreatedDateDesc(List<String> fileNames, String status) {
        return filesDao.findFilesByFileNameInAndStatusOrderByCreatedDateDesc(fileNames, status);
    }

    public List<Files> findFilesByFileNameInAndStatusInOrderByCreatedDateDesc(List<String> fileNames, List<String> status) {
        return filesDao.findFilesByFileNameInAndStatusInOrderByCreatedDateDesc(fileNames, status);
    }

    public List<Files> findFilesByFileNameInAndStatusAndOwnerOrderByCreatedDateDesc(List<String> fileNames, String status, String ownerId) {
        return filesDao.findFilesByFileNameInAndStatusAndOwnerOrderByCreatedDateDesc(fileNames, status, ownerId);
    }

    public List<Files> findFilesByFileNameInAndStatusInAndOwnerOrderByCreatedDateDesc(List<String> fileNames, List<String> status, String ownerId) {
        return filesDao.findFilesByFileNameInAndStatusInAndOwnerOrderByCreatedDateDesc(fileNames, status, ownerId);
    }

    public List<Files> findFilesByFileNameInAndStatusAndOwnerOrderByCreatedDateDesc(List<String> fileNames, String status, String ownerId, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return filesDao.findFilesByFileNameInAndStatusAndOwnerOrderByCreatedDateDesc(fileNames, status, ownerId, pageable);
    }

    public List<Files> findFilesByFileNameInAndStatusOrderByCreatedDateDesc(List<String> fileNames, String status, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return filesDao.findFilesByFileNameInAndStatusOrderByCreatedDateDesc(fileNames, status, pageable);
    }

    public List<Files> findFilesByStatusOrderByCreatedDateDesc(String status, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return filesDao.findFilesByStatusOrderByCreatedDateDesc(status, pageable);
    }

    public List<Files> findFilesByStatusAndOwnerOrderByCreatedDateDesc(String status, String ownerId, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return filesDao.findFilesByStatusAndOwnerOrderByCreatedDateDesc(status, ownerId, pageable);
    }
}



