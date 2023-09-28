package com.ace.service;

import com.ace.constant.AceEnvironment;
import com.ace.constant.constant;
import com.ace.dao.FoldersDao;
import com.ace.models.entity.Folders;
import com.ace.models.entity.Users;
import com.util.FileUtil;
import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Classname: CacheService
 * @Date: 2023/5/17 下午 04:36
 * @Author: kalam_au
 * @Description:
 */


@Service
public class FoldersService {
    private static final Logger log = LogManager.getLogger(FoldersService.class.getName());

    private final FoldersDao foldersDao;
    private final String currentUserPath;
    private final String separator;

    public FoldersService(FoldersDao foldersDao) {
        this.foldersDao = foldersDao;
        this.currentUserPath = AceEnvironment.getCurrentUserFolder();
        this.separator = AceEnvironment.getSeparator();
    }

    public Folders save(Folders folders) {
        return foldersDao.save(folders);
    }

    public Folders findByPath(String path) {
        return foldersDao.findByPath(path);
    }

    public void getFolderTreeByCurrentUser(Users currentUser) {
        List<Folders> folders = foldersDao.findByOwnerId(currentUser.getUserId());
        Map<Integer, List<String>> folderTree = new HashMap<>();
//        List<List<String>> folderTree = new ArrayList<>();

//        for (Folders child : folders) {
//            List<String> childList = new ArrayList<>();
//            for (Folders parent : folders) {
//                if (child.getParentId().equals(parent.getId())) {
//                    childList.add(child.getFolderName());
//                }
//            }
//            folderTree.put(parentFolder, childList);
//        }

        Integer level = 0;
        for (Folders parent : folders) {
            List<String> childList = new ArrayList<>();
            for (Folders child : folders) {
                if (child.getParentId().equals(parent.getId())) {
                    childList.add(child.getFolderName());
                }
            }
            folderTree.put(level, childList);
            ++level;
        }


        System.out.println("------");
    }


    /**
     * 用户登陆时, 新建默认文件夹
     *
     * @param currentUserPath
     * @param currentUser
     */
    public void createCurrentUserDefaultFolder(String currentUserPath, Users currentUser) {
        //创建当前用户文件夹
        Folders folder = findByPath(currentUserPath);
        if (NullUtil.isNull(folder) || NullUtil.isNull(folder.getPath())) {
            Folders f = new Folders();
            f.setParentId(0l);
            f.setPath(currentUserPath);
            f.setOwnerId(currentUser.getUserId());
            f.setFolderName(currentUser.getUserAccount());
            save(f);
            FileUtil.mkDirs(currentUserPath);
        }
    }

    /**
     * 新建文件夹
     *
     * @param currentPath
     * @param newFolderName
     * @param currentUser
     * @return
     */
    public Map<String, String> create(String currentPath, String newFolderName, Users currentUser) {
        Map<String, String> status = new HashMap();
        String newFolderPath = currentPath + newFolderName + separator;
        Folders folder = findByPath(newFolderPath);
        if (NullUtil.isNull(folder) || NullUtil.isNull(folder.getId())) {
            Folders parentFolder = findByPath(currentPath);
            //check owner
            if (isOwner(parentFolder, currentUser)) {
                Folders newFolder = new Folders();
                newFolder.setParentId(parentFolder.getId());
                newFolder.setFolderName(newFolderName);
                newFolder.setOwnerId(currentUser.getUserId());
                newFolder.setPath(newFolderPath);
                save(newFolder);
                FileUtil.mkDirs(newFolderPath);
                String msg = "Folder " + newFolderName + " create success !";
                status.put("status", constant.SUCCESS);
                status.put("msg", msg);
            } else {
                String msg = "Create " + newFolderName + " fail ! " + "access denied";
                status.put("status", constant.DENIED);
                status.put("msg", msg);
            }
        } else {
            String msg = "Folder " + newFolderName + " exist !";
            status.put("status", constant.EXIST);
            status.put("msg", msg);
        }
        return status;
    }

    private boolean isOwner(Folders folder, Users currentUser) {
        return folder.getOwnerId().equals(currentUser.getUserId());
    }

    public Map<String, String> delete() {
        Map<String, String> status = new HashMap();

        return status;
    }

    public Map<String, String> rename() {
        Map<String, String> status = new HashMap();

        return status;
    }

}

