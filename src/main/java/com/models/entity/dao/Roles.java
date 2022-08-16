package com.models.entity.dao;


import com.models.entity.dao.base.baseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(name = "constraint_roleCode", columnNames = "roleCode")})
@Entity
public class Roles extends baseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id") //use for mariadb
//	@GeneratedValue(strategy = GenerationType.IDENTITY) // mysql5 需要table AUTO_INCREMENT配合
    @Column
    private Long roleId;

    @Column
    private String roleCode;

    @Column
    private String roleName;

    @Column
    private String status = "ACTIVE"; // ACTIVE || INACTIVE


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
