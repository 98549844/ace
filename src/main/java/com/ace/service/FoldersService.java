package com.ace.service;

import com.ace.dao.FoldersDao;
import com.ace.models.entity.Folders;
import com.util.FileUtil;
import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

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

    public FoldersService(FoldersDao foldersDao) {
        this.foldersDao = foldersDao;
    }

    public Folders save(Folders folders) {
        return foldersDao.save(folders);
    }

    public Folders findByPath(String path) {
        return foldersDao.findByPath(path);
    }

    public boolean createCurrentUserFolder(String currentUserPath, String owner) {
        //创建当前用户文件夹
        Folders folder = findByPath(currentUserPath);
        if (NullUtil.isNull(folder) || NullUtil.isNull(folder.getPath())) {
            Folders f = new Folders();
            f.setParentId(0);
            f.setPath(currentUserPath);
            f.setOwner(owner);
            f.setFolderName(owner);
            save(f);
            FileUtil.mkDirs(currentUserPath);
            return true;
        }
        return false;
    }
}

