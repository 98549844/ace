package com.dao;

import com.models.entity.dao.RolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolePermissionsDao extends JpaRepository<RolePermissions, Long>, JpaSpecificationExecutor<RolePermissions> {
}
