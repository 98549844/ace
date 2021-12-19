package com.service;


import com.dao.RolesDao;
import com.models.entity.dao.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class RolesService {

    private RolesDao rolesDao;

    @Autowired
    public RolesService(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
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

    public Roles findRolesByRoleId (Long rolesId){
        return rolesDao.findRolesByRoleId(rolesId);
    }

    public void saveAllAndFlush(List<Roles> roles) {
        rolesDao.saveAllAndFlush(roles);
    }
}
