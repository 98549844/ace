package com.ace.dao;

import com.ace.models.entity.RolePermissions;
import com.ace.models.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface RolePermissionsDao extends JpaRepository<RolePermissions, Long>, JpaSpecificationExecutor<RolePermissions> {

    List<RolePermissions> findRolePermissionsByRoleId(Long roleId);


    @Query(nativeQuery = true, value = "select p.* from roles r, role_permissions rp, permissions p where r.roleId = rp.roleId and rp.permissionsId = p.permissionsId and r.roleCode = :#{#roleCode}")
    List<Map> findPermissionsByRoleCode(@Param("roleCode") String roleCode);

    @Query(nativeQuery = true, value = "select p.* from roles r, role_permissions rp, permissions p where r.roleId = rp.roleId and rp.permissionsId = p.permissionsId and r.roleCode in (:#{#roleCode})")
    List<Map> findPermissionsInRoles(@Param("roleCode") List<String> roleCode);

}
