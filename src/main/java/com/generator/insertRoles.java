package com.generator;

import com.constant.Constant;
import com.models.entity.dao.Roles;
import com.models.entity.dao.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.util.NullUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: insertRoles
 * @Date: 10/12/2021 6:48 AM
 * @Author: garlam
 * @Description:
 */


public class insertRoles {
    private static final Logger log = LogManager.getLogger(insertRoles.class.getName());

    public List<Roles> insertRoles(Users users) {
        List<Roles> ls = new ArrayList<>();

        Roles r1 = new Roles();
        Roles r2 = new Roles();
        Roles r3 = new Roles();
        Roles r4 = new Roles();

        if (NullUtil.isNull(users) || NullUtil.isNull(users.getUserId())) {
            log.warn("User is null, default id = 1");
            r1.setCreatedBy(1l);
            r2.setCreatedBy(1l);
            r3.setCreatedBy(1l);
            r4.setCreatedBy(1l);

            r1.setLastUpdatedBy(1l);
            r2.setLastUpdatedBy(1l);
            r3.setLastUpdatedBy(1l);
            r4.setLastUpdatedBy(1l);
        } else {
            r1.setCreatedBy(users.getUserId());
            r2.setCreatedBy(users.getUserId());
            r3.setCreatedBy(users.getUserId());
            r4.setCreatedBy(users.getUserId());

            r1.setLastUpdatedBy(users.getUserId());
            r2.setLastUpdatedBy(users.getUserId());
            r3.setLastUpdatedBy(users.getUserId());
            r4.setLastUpdatedBy(users.getUserId());
        }

        r1.setCreatedDate(LocalDateTime.now());
        r2.setCreatedDate(LocalDateTime.now());
        r3.setCreatedDate(LocalDateTime.now());
        r4.setCreatedDate(LocalDateTime.now());

//        r1.setDescription(Constant.Administrator);
//        r2.setDescription(Constant.Disable);
//        r3.setDescription(Constant.User);
//        r4.setDescription(Constant.Viewer);


        r1.setLastUpdateDate(LocalDateTime.now());
        r2.setLastUpdateDate(LocalDateTime.now());
        r3.setLastUpdateDate(LocalDateTime.now());
        r4.setLastUpdateDate(LocalDateTime.now());

        r1.setRoleCode(Constant.ROLECODE_ADMIN);
        r2.setRoleCode(Constant.ROLECODE_DISABLE);
        r3.setRoleCode(Constant.ROLECODE_USER);
        r4.setRoleCode(Constant.ROLECODE_VIEWER);

        r1.setRoleName(Constant.administrator);
        r2.setRoleName(Constant.disable);
        r3.setRoleName(Constant.user);
        r4.setRoleName(Constant.Viewer);

        ls.add(r1);
        ls.add(r2);
        ls.add(r3);
        ls.add(r4);

        return ls;
    }

}
