package com.models.entity.dao;


import com.models.entity.dao.base.baseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_roles")
@Entity
public class UserRoles extends baseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(strategy = "identity", name = "id")
	@Column
	private Integer userRolesId;

	@Column
	private Integer roleId;
	@Column
	private Integer userId;


	public Integer getUserRolesId() {
		return userRolesId;
	}

	public void setUserRolesId(Integer userRolesId) {
		this.userRolesId = userRolesId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
