package com.ace.restController;

import com.ace.models.entity.Menu;
import com.ace.service.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/menu")
@Tag(name = "Menu")
public class MenuRestController {
	private final static Logger log = LogManager.getLogger(MenuRestController.class.getName());

	private final MenuService menuService;

	@Autowired
	public MenuRestController(MenuService menuService) {
		this.menuService = menuService;
	}

	@RequestMapping(value = "/tree.html", method = RequestMethod.GET)
	public List<Menu> getMenuTree() {
		log.info("Generate Menu Tree");

		List<Menu> menuTree = menuService.getMenuTree();

		return menuTree;
	}


	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public List<Menu> getMenuList() {
		log.info("Generate Menu List");

		List<Menu> menuList = menuService.getMenuList();

		return menuList;
	}
}
