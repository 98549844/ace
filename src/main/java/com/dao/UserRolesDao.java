package com.dao;

import com.models.entity.dao.UserRoles;
import com.models.entity.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRolesDao extends JpaRepository<UserRoles, Long>, JpaSpecificationExecutor<UserRoles> {

	List<UserRoles> findByUserId(Long userId);

}
