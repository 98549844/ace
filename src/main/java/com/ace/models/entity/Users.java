package com.ace.models.entity;

import com.ace.models.entity.base.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "constraint_userAccount", columnNames = {"userAccount"}), @UniqueConstraint(name = "constraint_email", columnNames = {"email"})})
@Entity
public class Users extends BaseEntity implements Serializable {

    //users_description
    public static final String ADMIN = "ADMIN";
    // public static final String Editor = "Editor"; // part of read update insert
    public static final String DISABLE = "DISABLE";
    public static final String USER = "USER"; // read update
    public static final String VIEWER = "VIEWER"; // read only

    public static final String ACTIVE = "ACTIVE";
    public static final String INACTIVE = "INACTIVE";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;
    @Column
    private String password;


    //personal info
    @Column
    private String userAccount;
    @Column

    private String username;
    @Column
    private String roleGroup; //用户角色组
    @Column
    private String email;
    @Column
    private String mobile;
    @Column
    private String gender;
    @Column
    private LocalDateTime dateOfBirth;
    private Long age = 0L;
    @Column
    private LocalDateTime loginDateTime;
    @Column
    private String status = "ACTIVE"; // ACTIVE || INACTIVE //决定模组功能是否开启

    @Column
    private String region;
    @Column
    private String domain;
    @Column
    private String ip;
    @Column
    private String hostName;
    @Column
    private String remark;

    @Column
    private LocalDateTime expireDate;

    @Column
    private Boolean enabled = true; // user account

    @Column
    private Boolean record = true; // 控制rrweb开启记录

    @Transient
    private String currentUserPath;

    @Transient
    private String icon = "";

    @Transient
    private List<Roles> roles ;

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(String roleGroup) {
        this.roleGroup = roleGroup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(LocalDateTime loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentUserPath() {
        return currentUserPath;
    }

    public void setCurrentUserPath(String currentUserPath) {
        this.currentUserPath = currentUserPath;
    }

    public String getIcon() {
        return icon;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public boolean isRecord() {
        return record;
    }

    public void setRecord(boolean record) {
        this.record = record;
    }
}
