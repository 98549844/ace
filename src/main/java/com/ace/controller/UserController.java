package com.ace.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.ace.constant.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.models.entity.Files;
import com.ace.models.entity.Roles;
import com.ace.models.entity.Users;
import com.ace.service.*;
import com.util.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
     * @param fileName
     */
    @RequestMapping(value = "/avatar/get/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public void get(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        log.info("access avatar/get/{}", fileName);
        imagesService.get(usersPath, fileName, response);
    }

    public static void main(String[] args) throws IOException {
        String aaaa = SystemUtil.getPath() + "/src/main/resources/static/assets/images/avatars/avatar5.png";

        BufferedImage aaa = ImageIO.read(new File(aaaa));
        System.out.println(aaa.getWidth());

    }

    @RequestMapping(value = "/avatars/get/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List getAvatar(@PathVariable(value = "userId") String userId) throws IOException {
        log.info("access avatars/get/{}", userId);
        List ls = imagesService.getFilesByFileNameLike(SqlUtil.likeRight(userId));
        return ls;
    }

    /**
     * 图片上传
     */
    @RequestMapping(value = "/avatar/uploads.html", method = RequestMethod.POST)
    @ResponseBody
    public List uploadAvatar(@RequestParam(value = "files") MultipartFile[] files, MultipartHttpServletRequest request) {
        log.info("access avatar/uploads.html");
        Users users = getCurrentUser();
        String userAccount = users.getUserAccount();
        //删除旧头像
        List<Files> deleteAvatars = imagesService.getFilesByFileNameLike(SqlUtil.likeRight(userAccount));
        for (Files f : deleteAvatars) {
            filesService.delFile(f.getLocation());
        }
        filesService.deleteAll(deleteAvatars);

        //上传新头头像
        String uuid = userAccount + "-" + UUID.get();
        filesService.uploads(files, uuid, usersPath);//上传图片,并更新数据
        usersService.compressAvatar(filesService.findFilesByFileName(uuid)); //同时压缩生成avatar和icon,并更新数据
        List<Files> fs = imagesService.getFilesByFileNameLike(SqlUtil.likeRight(userAccount));
        for (Files f : fs) {
            if (f.getFileName().contains(userAccount + "-icon")) {
                //更新currentUser icon id
                users.setIcon(f.getFileName());
                setUsersSaSession(users);
            }
        }
        return fs;
    }

    /**
     * 头像旋转
     *
     * @param direction
     * @param uuid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/avatar/rotate/{direction}/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public List<Files> rotateAvatar(@PathVariable String direction, @PathVariable String uuid) throws Exception {
        log.info("access image/rotate => rotate {} {}", direction, uuid);
        String newUuid = UUID.get();
        Users users = getCurrentUser();
        String newFileName = users.getUserAccount() + "-" + newUuid;
        String newAvatarUuid = users.getUserAccount() + "-avatar-" + newUuid;
        String newIconUuid = users.getUserAccount() + "-icon-" + newUuid;

        String[] fUuid = uuid.split("-");
        Files f = filesService.findFilesByFileName(users.getUserAccount() + "-" + fUuid[2]);
        f.setFileName(newFileName);
        FileUtil.rename(f.getLocation(), f.getPath() + newFileName + f.getExt());

        filesService.save(f);
        Files fAvatar = imagesService.rotate(direction, uuid, newAvatarUuid);

        Files fIcon = imagesService.rotate(direction, users.getUserAccount() + "-icon-" + fUuid[2], newIconUuid);
        List<Files> fs = new ArrayList<>();
        fs.add(fAvatar);
        fs.add(fIcon);
        return fs;
    }


}
