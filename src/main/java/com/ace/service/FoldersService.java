package com.ace.service;

import com.ace.constant.AceEnvironment;
import com.ace.constant.constant;
import com.ace.dao.FoldersDao;
import com.ace.models.entity.Folders;
import com.ace.models.entity.Users;
import com.ace.utilities.FileUtil;
import com.ace.utilities.NullUtil;
import com.ace.utilities.OsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final String separator;
    public final String FOLDER_ID = "folderId";
    public final String FOLDER_NAME = "folderName";
    public final String SUB_FOLDERS = "subFolders";

    public FoldersService(FoldersDao foldersDao, AceEnvironment aceEnvironment) {
        this.foldersDao = foldersDao;
        this.separator = aceEnvironment.getSeparator();
    }

    public Folders save(Folders folders) {
        return foldersDao.save(folders);
    }

    public Folders findByPath(String path) {
        return foldersDao.findByPath(path);
    }

    public Folders getRootFolder(Users currentUser) {
        String osName = OsUtil.getOsName();
        String osType = OsUtil.UNKNOWN;
        if (osName.contains(OsUtil.WINDOWS)) {
            osType = OsUtil.WINDOWS;
        } else if (osName.contains(OsUtil.MAC)) {
            osType = OsUtil.MAC;
        } else if (osName.contains(OsUtil.LINUX)) {
        } else {
            osType = OsUtil.LINUX;
        }
        return foldersDao.findByFolderNameAndParentIdAndOsType(currentUser.getUserAccount(), 0l, osType);
    }

    public List<Folders> getFolders(Users currentUser, Long parentId) {
        return foldersDao.findByOwnerIdAndParentId(currentUser.getUserId(), parentId);
    }


    //  https://www.treejs.cn/v3/demo.php#_113
    //https://github.com/ParadeTo/vue-tree-list Vue Tree List Component
    //https://github.com/zdy1988/vue-jstree vue-jstree
    public Map<String, Object> buildFolderMapByCurrentUser(Users currentUser) {
        List<Folders> folders = foldersDao.findByOwnerId(currentUser.getUserId());
        Map<Long, Folders> folderTree = new HashMap<>();

        Folders root = null;
        // 构建节点映射表
        for (Folders folder : folders) {
            folderTree.put(folder.getId(), folder);
        }

        // 构建树状结构
        for (Folders folder : folders) {
            Long parentId = folder.getParentId();
            if (parentId == 0) {
                root = folder;
            } else {
                Folders parent = folderTree.get(parentId);
                if (parent != null) {
                    parent.addSubFolder(folder);
                }
            }
        }

        if (root == null) {
            return new HashMap<>();
        }
        Map<String, Object> aaa = buildTree(root);
        return buildTree(root);

    }

    private Map<String, Object> buildTree(Folders folder) {
        Map<String, Object> tree = new HashMap<>();
        tree.put(this.FOLDER_ID, folder.getId());
        tree.put(this.FOLDER_NAME, folder.getFolderName());

        List<Folders> children = folder.getSubFolders();
        if (!children.isEmpty()) {
            List<Map<String, Object>> childTrees = new ArrayList<>();
            for (Folders child : children) {
                Map<String, Object> childTree = buildTree(child);
                childTrees.add(childTree);
            }

            tree.put(this.SUB_FOLDERS, childTrees);
        }
        return tree;
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

    /**
     * 未完成
     *
     * @return
     */
    public Map<String, String> delete() {
        Map<String, String> status = new HashMap();

        return status;
    }

    /**
     * 未完成
     *
     * @return
     */
    public Map<String, String> rename() {
        Map<String, String> status = new HashMap();

        return status;
    }

}

