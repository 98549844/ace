package com.dao;

import com.models.entity.dao.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolesDao extends JpaRepository<Roles, Long>, JpaSpecificationExecutor<Roles> {


	Roles findByRoleCode(String roleCode);
}
