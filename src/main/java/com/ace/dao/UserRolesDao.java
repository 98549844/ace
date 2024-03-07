package com.ace.dao;

import com.ace.models.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRolesDao extends JpaRepository<UserRoles, Long>, JpaSpecificationExecutor<UserRoles> {

    //   @Query(nativeQuery = true, value = "select u.userId, r.roleId, p.permissionsId, p.permissionCode, u.username, p.action, r.roleCode, u.description, u.userAccount from role_permissions rp, permissions p, roles r, user_roles ur, users u where 1 = 1 and rp.permissionsId = p.permissionsId and rp.roleId = r.roleId and ur.roleId = r.roleId and ur.userId = u.userId order by userId")
    //   List<Map> findUserRolePermission();

    List<UserRoles> findAllByUserId(Long userId);
    UserRoles findUserRolesByUserIdAndRoleId(Long userId, Long roleId);

    List<UserRoles> findAllByUserIdAndRoleId(Long userId, Long roleId);


    List<UserRoles> findAllByUserIdIn(List<Long> usersId);

    List<UserRoles> findAllByUserRolesIdNotIn(List<Long> userRoles);

    @Modifying
    @Transactional
    void deleteUserRolesByUserId(Long userId);

    @Modifying
    @Transactional
    void deleteUserRolesByUserIdAndRoleId(Long userId, Long roleId);
}
