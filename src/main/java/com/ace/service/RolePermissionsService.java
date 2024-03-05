package com.ace.service;

import com.ace.dao.RolePermissionsDao;
import com.ace.models.entity.Permissions;
import com.ace.models.entity.RolePermissions;
import com.util.EntityUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RolePermissionsService {
    private static final Logger log = LogManager.getLogger(RolePermissionsService.class.getName());

    private final RolePermissionsDao rolePermissionsDao;

    @Autowired
    public RolePermissionsService(RolePermissionsDao rolePermissionsDao) {
        this.rolePermissionsDao = rolePermissionsDao;
    }

    public RolePermissions save(RolePermissions rolePermissions) {
        return rolePermissionsDao.saveAndFlush(rolePermissions);
    }

    public List<RolePermissions> findRolePermissionsByRoleId(Long roleId) {
        return rolePermissionsDao.findRolePermissionsByRoleId(roleId);
    }

    public List<Permissions> findPermissionsByRoleCode(String roleCode) {
        List<Map> results = rolePermissionsDao.findPermissionsByRoleCode(roleCode);
        List<Permissions> permissionsList = EntityUtil.listMapToEntity(results, Permissions.class);
        return permissionsList;
    }

    public List<RolePermissions> saveAll(List<RolePermissions> permissionsArrayList) {
        return rolePermissionsDao.saveAll(permissionsArrayList);
    }

    public void deleteAll() {
        rolePermissionsDao.deleteAll();
    }

    public void delete(RolePermissions rolePermissions) {
        rolePermissionsDao.delete(rolePermissions);
    }

}
