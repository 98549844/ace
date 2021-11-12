package com.models.entity.dao;


import com.models.entity.dao.base.baseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "role_permissions")
@Entity
public class RolePermissions extends baseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(strategy = "identity", name = "id")
	@Column
	private BigDecimal rolePermissionsId;
	@Column
	private BigDecimal roleId;
	@Column
	private BigDecimal permissionsId;
	@Column
	private String url;

	public BigDecimal getRolePermissionsId() {
		return rolePermissionsId;
	}

	public void setRolePermissionsId(BigDecimal rolePermissionsId) {
		this.rolePermissionsId = rolePermissionsId;
	}

	public BigDecimal getRoleId() {
		return roleId;
	}

	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}

	public BigDecimal getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(BigDecimal permissionsId) {
		this.permissionsId = permissionsId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
