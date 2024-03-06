package com.ace.service;

import com.ace.dao.RolePermissionsDao;
import com.ace.models.entity.Permissions;
import com.ace.models.entity.RolePermissions;
import com.ace.models.entity.Roles;
import com.util.EntityUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public RolePermissions saveAndFlush(RolePermissions rolePermissions) {
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

    public List<Permissions> findPermissionsInRoleCode(List<Roles> roles) {
        List<String> roleCOdes = new ArrayList<>();
        for (Roles r : roles) {
            //没有解决到直接传对像, 然后在sql调用entity.field的问题
            roleCOdes.add(r.getRoleCode());
        }
        List<Map> results = rolePermissionsDao.findPermissionsInRoles(roleCOdes);
        List<Permissions> permissionsList = EntityUtil.listMapToEntity(results, Permissions.class);
        return permissionsList;
    }

    public void saveAll(List<RolePermissions> permissionsArrayList) {
        rolePermissionsDao.saveAll(permissionsArrayList);
    }

    public void save(RolePermissions rolePermissions) {
        rolePermissionsDao.save(rolePermissions);
    }

    public List<RolePermissions> saveAllAndFlush(List<RolePermissions> permissionsArrayList) {
        return rolePermissionsDao.saveAllAndFlush(permissionsArrayList);
    }

    public void deleteAll() {
        rolePermissionsDao.deleteAll();
    }

    public void deleteByRoleId(Long roleId) {
        rolePermissionsDao.deleteByRoleId(roleId);
    }


    public void delete(RolePermissions rolePermissions) {
        rolePermissionsDao.delete(rolePermissions);
    }

}
