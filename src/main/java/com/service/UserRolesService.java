package com.service;


import com.dao.UserRolesDao;
import com.models.entity.dao.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesService {

    private UserRolesDao userRolesDao;

    @Autowired
    public UserRolesService(UserRolesDao userRolesDao) {
        this.userRolesDao = userRolesDao;
    }


    public UserRoles saveAndFlush(UserRoles userRoles) {
        return userRolesDao.saveAndFlush(userRoles);
    }

    public UserRoles save(UserRoles userRoles) {
        return userRolesDao.save(userRoles);
    }

    public List<UserRoles> saveAll(List<UserRoles> userRolesList) {
        return userRolesDao.saveAll(userRolesList);
    }

    public void deleteAll() {
        userRolesDao.deleteAll();
    }

    public void delete(UserRoles userRoles) {
        userRolesDao.delete(userRoles);
    }

    public List<UserRoles> findAllByUserId(Long userId) {
        return userRolesDao.findAllByUserId(userId);
    }


}
