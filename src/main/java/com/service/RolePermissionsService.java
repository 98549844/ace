package com.service;

import com.dao.RolePermissionsDao;
import com.models.entity.dao.RolePermissions;
import org.springframework.beans.factory.annotation.Autowired;

public class RolePermissionsService {

	private RolePermissionsDao rolePermissionsDao;

	@Autowired
	public RolePermissionsService(RolePermissionsDao rolePermissionsDao) {
		this.rolePermissionsDao = rolePermissionsDao;
	}

	public RolePermissions save (RolePermissions rolePermissions){
		return rolePermissionsDao.saveAndFlush(rolePermissions);
	}

}
