package com.service;


import com.dao.RolesDao;
import com.models.entity.dao.Roles;
import com.models.entity.dao.UserRoles;
import com.models.entity.dao.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolesService {

    private RolesDao rolesDao;
    private UserRolesService userRolesService;

    @Autowired
    public RolesService(RolesDao rolesDao, UserRolesService userRolesService) {
        this.rolesDao = rolesDao;
        this.userRolesService = userRolesService;
    }

    public List<Roles> findAll() {
        return rolesDao.findAll();
    }

    public Roles findByRoleCode(String roleCode) {
        return rolesDao.findByRoleCode(roleCode);
    }

    public void deleteAll() {
        rolesDao.deleteAll();
    }

    public void saveAll(List<Roles> roles) {
        rolesDao.saveAll(roles);
    }

    public Roles findRolesByRoleId(Long rolesId) {
        return rolesDao.findRolesByRoleId(rolesId);
    }

    public List<Roles> findRolesByRoleIdIn(List<Long> rolesList) {
        return rolesDao.findRolesByRoleIdIn(rolesList);
    }

    public void saveAllAndFlush(List<Roles> roles) {
        rolesDao.saveAllAndFlush(roles);
    }

    public List<Roles> getRolesByUser(Users users) {
        List<UserRoles> userRolesList = userRolesService.findAllByUserId(users.getUserId());
        List<Long> roleIdList = new ArrayList<>();
        for (UserRoles userRoles : userRolesList) {
            roleIdList.add(userRoles.getRoleId());
        }
        List<Roles> rolesList = findRolesByRoleIdIn(roleIdList);
        return rolesList;
    }

}
