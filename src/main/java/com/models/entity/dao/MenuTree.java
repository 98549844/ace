package com.models.entity.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @Classname: MenuTree
 * @Date: 13/11/2021 2:49 上午
 * @Author: garlam
 * @Description:
 */


public class MenuTree extends Menus{

    List<Menus> menusList;

    public List<Menus> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<Menus> menusList) {
        this.menusList = menusList;
    }
}

