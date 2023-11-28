package com.ace.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.ace.constant.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.Files;
import com.ace.models.entity.Roles;
import com.ace.models.entity.Users;
import com.ace.service.*;
import com.util.NullUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Classname: UserController
 * @Date: 11/11/2021 4:38 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace/users")
public class UserController extends CommonController {
    private static final Logger log = LogManager.getLogger(UserController.class.getName());

    private final UsersService usersService;
    private final UserRolesService userRolesService;
    private final RolesService rolesService;
    private final FilesService filesService;
    private final ImagesService imagesService;
    private final String usersPath;


    @Autowired
    public UserController(UserRolesService userRolesService, UsersService usersService, RolesService rolesService, FilesService filesService, ImagesService imagesService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.userRolesService = userRolesService;
        this.filesService = filesService;
        this.imagesService = imagesService;
        this.usersPath = AceEnvironment.getUsers();
    }

    @RequestMapping(value = "/user.html", method = RequestMethod.GET)
    public ModelAndView getUserList() {
        List<Users> userList = usersService.findUsersOrderByLoginDateTime(15);
        ModelAndView modelAndView = super.page("ace/modules/users/users");
        modelAndView.addObject("users", userList);
        return modelAndView;
    }


    @RequestMapping(value = "/enable.html", method = RequestMethod.GET)
    public ModelAndView setEnable(@RequestParam(value = "userId") Long userId) {
        getPermission();

        log.info("userId: {}", userId);
        ModelAndView modelAndView = super.page("ace/pb-pages/ajax-result");

        Users user = usersService.findUsersById(userId);
        if (user.isEnabled()) {
            user.setEnabled(false);
            modelAndView.addObject("ajaxResult", "<strong class=\"red\">Disable</strong>");
        } else {
            user.setEnabled(true);
            modelAndView.addObject("ajaxResult", "<strong class=\"green\">Enable</strong>");
        }
        usersService.save(user);
        kickout(user.getUserId());
        return modelAndView;
    }

    @RequestMapping(value = "/profile.html/{userId}", method = RequestMethod.GET)
    public ModelAndView getProfile(@PathVariable Long userId) {
        log.info("access profile.html/{}", userId);
        Users user = usersService.findUsersById(userId);
        List<Roles> rolesList = rolesService.getRolesByUserId(userId);
        List<Roles> allRoles = rolesService.findAll();

        ModelAndView modelAndView = super.page("ace/modules/users/profile");
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", rolesList);
        modelAndView.addObject("allRoles", allRoles);
        return modelAndView;
    }

    @RequestMapping(value = "/search.html", method = RequestMethod.GET)
    public ModelAndView getUserById(String username) {
        log.info("username: {}", username);
        List<Users> userList;
        if (NullUtil.isNull(username) || "".equals(username)) {
            userList = usersService.findUsersOrderByLoginDateTime(30);
        } else {
            userList = usersService.findUsersByUsernameLikeIgnoreCaseOrderByLoginDateTime(username);
        }
        ModelAndView modelAndView = super.page("ace/modules/users/users");
        modelAndView.addObject("users", userList);
        return modelAndView;
    }

    @RequestMapping(value = "/delete.html/{userId}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public boolean deleteUser(@PathVariable Long userId) {
        log.info("access users/delete.html/{}", userId);
        usersService.delete(userId);
        userRolesService.deleteUserRolesByUserId(userId);
        return true;
    }

    private void getPermission() {
        // 判断：当前账号是否含有指定权限, 返回true或false
        boolean hasPermission = StpUtil.hasPermission("user-update");
        log.info("hasPermission: {}", hasPermission);

        // 校验：当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
        StpUtil.checkPermission("user-update");

        // 校验：当前账号是否含有指定权限 [指定多个，必须全部验证通过]
        StpUtil.checkPermissionAnd("user-update", "user-delete");

        // 校验：当前账号是否含有指定权限 [指定多个，只要其一验证通过即可]
        StpUtil.checkPermissionOr("user-update", "user-delete");
    }

    /**
     * 缩略图显示请求
     * 响应输出图片文件
     *
     * @param userId
     */
    /**
     * 缩略图显示请求
     * 响应输出图片文件
     *
     * @param fileName
     */
    @RequestMapping(value = "/avatar/get/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public void get(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        log.info("access avatar/get/{}", fileName);
        imagesService.get(usersPath, fileName, response);
    }

    @RequestMapping(value = "/icon/get/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List getAvatar(@PathVariable(value = "userId") String userId) throws IOException {
        log.info("access icon/get/{}", userId);
        List ls = imagesService.getImagesByFileName(userId);
        return ls;
    }

    @RequestMapping(value = "/avatar/rotate/{direction}/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public Files rotate(@PathVariable String direction, @PathVariable String uuid) throws Exception {
        log.info("access image/rotate => rotate {} {}", direction, uuid);
        Files f = imagesService.rotate(direction, usersPath, uuid);
        return f;
    }
}
