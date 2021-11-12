package com.models.entity.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

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
    private Long menuId;

    @Column
    private String menu;
    @Column
    private Long menuCode;

    @Column
    private Long parentId;
    @Column
    private String parentMenu;
    @Column
    private Long parentMenuCode;

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        Menus.log = log;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Long getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(Long menuCode) {
        this.menuCode = menuCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Long getParentMenuCode() {
        return parentMenuCode;
    }

    public void setParentMenuCode(Long parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }
}

