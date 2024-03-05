package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.generator.insertPermissions;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Permissions;
import com.ace.models.entity.Users;
import com.ace.service.PermissionsService;
import com.ace.service.RolesService;
import com.ace.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @Classname: PermissionRestController
 * @Date: 12/12/2021 5:54 AM
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/permission")
//@Api(tags = "permission")
@Tag(name = "Permission")
public class PermissionRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(PermissionRestController.class.getName());

    private final PermissionsService permissionsService;
    private final UsersService usersService;

    @Autowired
    public PermissionRestController(UsersService usersService, PermissionsService permissionsService) {
        this.permissionsService = permissionsService;
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAllPermission")
    public AjaxResponse deleteAllPermission() {
        permissionsService.deleteAll();
        return AjaxResponse.success("All permission deleted");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPermission")
    public AjaxResponse getPermission() {
        List<Permissions> ls = permissionsService.findAll();
        Map map =new LinkedHashMap();
        for (Permissions permissions : ls) {
            map.put(permissions.getAction(),"PermissionCode: "+"[" + permissions.getPermissionCode() + "]");
        }
        return AjaxResponse.success(map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/insertPermission")
    public AjaxResponse insertPermission() {
        permissionsService.deleteAll();
        log.info("All permissions DELETED !");

        Users user = usersService.findByUserAccount("garlam");
        //generate roles data
        insertPermissions insertPermissions = new insertPermissions();
        List<Permissions> ls = insertPermissions.insertPermissions(user);

        permissionsService.saveAll(ls);
        log.info("Default permissions has been CREATED !");
        Map map =new LinkedHashMap();
        for (Permissions permissions : ls) {
            map.put(permissions.getAction(),"PermissionCode: "+"[" + permissions.getPermissionCode() + "]");
        }
        return AjaxResponse.success(map);
    }
}

