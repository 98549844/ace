package com.ace.dao;

import com.ace.models.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionsDao extends JpaRepository<Permissions, Long>, JpaSpecificationExecutor<Permissions> {


	Permissions findPermissionsByPermissionCode(String permissionCode);

	Permissions findPermissionsByAction(String action);

//	@Query(value = "select p.permissionsId, p.action, p.permissionCode from Roles r, RolePermissions rp, Permissions p where r.roleId = rp.roleId and rp.permissionsId = p.permissionsId and r.roleCode = :#{#roleCode}")
//	List<Permissions> findPermissionsByRoleCode(@Param("roleCode") String roleCode);
}
