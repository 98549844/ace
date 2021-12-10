package com.configDataGenerator;

import com.constant.Constant;
import com.models.entity.dao.Permissions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.entity.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: insertPermissions
 * @Date: 10/12/2021 6:48 AM
 * @Author: garlam
 * @Description:
 */


public class insertPermissions {
    private static final Logger log = LogManager.getLogger(insertPermissions.class.getName());

    public List<Permissions> insertPermissions(Users users) {
        List<Permissions> ls = new ArrayList<>();

        Permissions p1 = new Permissions();
        Permissions p2 = new Permissions();
        Permissions p3 = new Permissions();
        Permissions p4 = new Permissions();
        Permissions p5 = new Permissions();
        Permissions p6 = new Permissions();
        Permissions p7 = new Permissions();

        p1.setCreatedBy(users.getUserId());
        p2.setCreatedBy(users.getUserId());
        p3.setCreatedBy(users.getUserId());
        p4.setCreatedBy(users.getUserId());
        p5.setCreatedBy(users.getUserId());
        p6.setCreatedBy(users.getUserId());
        p7.setCreatedBy(users.getUserId());


        p1.setCreatedDate(LocalDateTime.now());
        p2.setCreatedDate(LocalDateTime.now());
        p3.setCreatedDate(LocalDateTime.now());
        p4.setCreatedDate(LocalDateTime.now());
        p5.setCreatedDate(LocalDateTime.now());
        p6.setCreatedDate(LocalDateTime.now());
        p7.setCreatedDate(LocalDateTime.now());


        p1.setDescription("Allow all operation");
        p2.setDescription("Insert only");
        p3.setDescription("Update only");
        p4.setDescription("Delete only");
        p5.setDescription("Read only");
        p6.setDescription("Allow select update insert");
        p7.setDescription("Deny any operation");


        p1.setLastUpdatedBy(users.getUserId());
        p2.setLastUpdatedBy(users.getUserId());
        p3.setLastUpdatedBy(users.getUserId());
        p4.setLastUpdatedBy(users.getUserId());
        p5.setLastUpdatedBy(users.getUserId());
        p6.setLastUpdatedBy(users.getUserId());
        p7.setLastUpdatedBy(users.getUserId());


        p1.setLastUpdateDate(LocalDateTime.now());
        p2.setLastUpdateDate(LocalDateTime.now());
        p3.setLastUpdateDate(LocalDateTime.now());
        p4.setLastUpdateDate(LocalDateTime.now());
        p5.setLastUpdateDate(LocalDateTime.now());
        p6.setLastUpdateDate(LocalDateTime.now());
        p7.setLastUpdateDate(LocalDateTime.now());

        p1.setPermissionCode(Constant.ALL);
        p2.setPermissionCode(Constant.INSERT);
        p3.setPermissionCode(Constant.UPDATE);
        p4.setPermissionCode(Constant.DELETE);
        p5.setPermissionCode(Constant.SELECT);
        p6.setPermissionCode(Constant.SELECT_UPDATE_INSERT);
        p7.setPermissionCode(Constant.DENY);

        p1.setAction("ALL");
        p2.setAction("INSERT");
        p3.setAction("UPDATE");
        p4.setAction("DELETE");
        p5.setAction("SELECT");
        p6.setAction("SELECT UPDATE INSERT");
        p7.setAction("DENY");

        p1.setEnabled(true);
        p2.setEnabled(true);
        p3.setEnabled(true);
        p4.setEnabled(true);
        p5.setEnabled(true);
        p6.setEnabled(true);
        p7.setEnabled(true);

        return ls;
    }


}

