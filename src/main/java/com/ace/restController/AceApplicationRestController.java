package com.ace.restController;

import com.ace.AceApplication;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Users;
import com.ace.service.UsersService;
import com.ace.util.BeanUtil;
import com.ace.util.PropertiesUtil;
import com.util.NullUtil;
import com.util.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname: AceApplicationRestController
 * @Date: 2022/11/13 下午 11:40
 * @Author: kalam_au
 * @Description:
 */

@RestController
@RequestMapping("/rest/AceApplication")
@Tag(name = "AceApplication")
public class AceApplicationRestController {
    private static final Logger log = LogManager.getLogger(AceApplicationRestController.class.getName());

    private final UserRolePermissionRestController userRolePermissionRestController;
    private final UsersService usersService;

    @Autowired
    public AceApplicationRestController(UsersService usersService, UserRolePermissionRestController userRolePermissionRestController) {
        this.userRolePermissionRestController = userRolePermissionRestController;
        this.usersService = usersService;
    }


    @Operation(summary = "Init Default User")
    @RequestMapping(method = RequestMethod.GET, value = "/initDefaultUser")
    public AjaxResponse GenerateDefaultUser() {
        userRolePermissionRestController.defaultUser();

        List<Users> users = usersService.findAll();
        List<String> result = new ArrayList<>();
        for (Users user : users) {
            result.add(user.getUserAccount());
            result.add(user.getStatus());
        }
        return AjaxResponse.success(result);
    }

    @Operation(summary = "Init Roles Permissions")
    @RequestMapping(method = RequestMethod.GET, value = "/initRolesPermissions")
    public AjaxResponse initRolesPermissions() {
        log.info("init roles and permissions relation !!!");
        return userRolePermissionRestController.mapRolesAndPermissions();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getBean")
    public AjaxResponse getBean(@RequestParam(required = false) String key) {
        if (NullUtil.isNull(key)) {
            log.info("print all bean name !!!");
            //print all loaded BeanName and properties value
            String[] beanNames = BeanUtil.getBeanNames(AceApplication.applicationContext);

            //根据字符串长度由短到长排列
            StringUtil.sortByLength(beanNames);
            int beanSize = beanNames.length;
            List<String> result = new ArrayList<>();
            for (int i = 0; i < beanSize; i++) {
                result.add(i + ". " + beanNames[i]);
            }
            return AjaxResponse.success(result);
        } else {
            boolean result = BeanUtil.beanExist(key);
            String message;
            Map<String, Object> map = new HashMap<>();
            if (result) {
                message = key + " exist";
                map.put("Result", true);
                map.put("BeanName", message);
            } else {
                message = key + " not found";
                map.put("Result", false);
                map.put("BeanName", message);
            }
            return AjaxResponse.success(map);
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getProperties")
    public AjaxResponse getProperties(@RequestParam(required = false) String key) {
        Map<String, String> properties = PropertiesUtil.getLoadedProperties();
        if (NullUtil.isNull(key)) {
            return AjaxResponse.success(properties);
        } else {
            return AjaxResponse.success(key + ": " + properties.get(key));
        }
    }
}

