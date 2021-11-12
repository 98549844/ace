package com.models.entity.dao;


import com.models.entity.dao.base.baseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "role_permissions")
@Entity
public class RolePermissions extends baseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(strategy = "identity", name = "id")
	@Column
	private Integer rolePermissionsId;
	@Column
	private Integer roleId;
	@Column
	private Integer permissionsId;
	@Column
	private String url;

	public Integer getRolePermissionsId() {
		return rolePermissionsId;
	}

	public void setRolePermissionsId(Integer rolePermissionsId) {
		this.rolePermissionsId = rolePermissionsId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(Integer permissionsId) {
		this.permissionsId = permissionsId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
