package com.service;

import com.dao.MenuDao;
import com.models.entity.dao.MenuTree;
import com.models.entity.dao.Menus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: MenuService
 * @Date: 13/11/2021 12:51 上午
 * @Author: garlam
 * @Description:
 */

@Service
public class MenuService {
    private static Logger log = LogManager.getLogger(MenuService.class.getName());

    private MenuDao menuDao;

    @Autowired
    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public List getMenuList() {

        List<Menus> rootMenus = menuDao.getDistinctByParentId(0l);


        List<Menus> menusList = menuDao.findAll();




        return menuDao.findAll();

    }

}

