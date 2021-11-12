package com.models.entity.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Classname: Menus
 * @Date: 12/11/2021 11:48 下午
 * @Author: garlam
 * @Description:
 */

@EntityListeners(AuditingEntityListener.class)
@Table(name = "menus")
@Entity
public class Menus {
    private static Logger log = LogManager.getLogger(Menus.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(strategy = "identity", name = "id")
    @Column
    private BigDecimal menuId;

    @Column
    private String menu;
    @Column
    private BigDecimal menuCode;

    @Column
    private BigDecimal parentId;
    @Column
    private String parentMenu;
    @Column
    private BigDecimal parentMenuCode;

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        Menus.log = log;
    }

    public BigDecimal getMenuId() {
        return menuId;
    }

    public void setMenuId(BigDecimal menuId) {
        this.menuId = menuId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public BigDecimal getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(BigDecimal menuCode) {
        this.menuCode = menuCode;
    }

    public BigDecimal getParentId() {
        return parentId;
    }

    public void setParentId(BigDecimal parentId) {
        this.parentId = parentId;
    }

    public String getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu;
    }

    public BigDecimal getParentMenuCode() {
        return parentMenuCode;
    }

    public void setParentMenuCode(BigDecimal parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }
}

