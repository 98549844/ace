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
	private Long rolePermissionsId;
	@Column
	private Long roleId;
	@Column
	private Long permissionsId;
//	@Column
//	private String url;

	public Long getRolePermissionsId() {
		return rolePermissionsId;
	}

	public void setRolePermissionsId(Long rolePermissionsId) {
		this.rolePermissionsId = rolePermissionsId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(Long permissionsId) {
		this.permissionsId = permissionsId;
	}

/*	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}*/
}
