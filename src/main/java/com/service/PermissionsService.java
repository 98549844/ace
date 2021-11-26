package com.service;

import com.dao.PermissionsDao;
import com.models.entity.dao.Permissions;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermissionsService {

	private PermissionsDao permissionsDao;

	@Autowired
	public PermissionsService(PermissionsDao permissionsDao) {
		this.permissionsDao = permissionsDao;
	}

	public Permissions findPermissionsByPermissionCode(String permissionCode) {
		return permissionsDao.findPermissionsByPermissionCode(permissionCode);
	}


}
