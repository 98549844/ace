package com.models.entity.dao;

import com.models.entity.dao.base.baseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "permissions",  uniqueConstraints = {@UniqueConstraint(name = "constraint_permissionCode", columnNames = "permissionCode")})
@Entity
public class Permissions extends baseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(strategy = "identity", name = "id")
	@Column
	private Long permissionsId;
	@Column
	private String permissionCode;
	@Column
	private String action;
	@Column
	private String description;
	@Column
	private boolean enabled = true;


	public Long getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(Long permissionsId) {
		this.permissionsId = permissionsId;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
