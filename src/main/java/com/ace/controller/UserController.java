package com.ace.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.ace.constant.AceEnvironment;
import com.ace.controller.common.CommonController;
import com.ace.exception.ResponseException;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Files;
import com.ace.models.entity.Roles;
import com.ace.models.entity.UserRoles;
import com.ace.models.entity.Users;
import com.ace.service.*;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ace.utilities.*;
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
    public UserController(AceEnvironment aceEnvironment, UserRolesService userRolesService, UsersService usersService, RolesService rolesService, FilesService filesService, ImagesService imagesService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.userRolesService = userRolesService;
        this.filesService = filesService;
        this.imagesService = imagesService;
        this.usersPath = aceEnvironment.getUsers();
    }

    @RequestMapping(value = "/users.html", method = RequestMethod.GET)
    public ModelAndView getUserList() {
        List<Users> userList = usersService.findUsersOrderByLoginDateTime(15);
        List<Roles> roles = rolesService.findAll();
        for (Users users : userList) {
            users.setRoles(roles);
        }
        ModelAndView modelAndView = super.page("ace/modules/users/users");
        modelAndView.addObject("users", userList);
        return modelAndView;
    }

    @RequestMapping(value = "/expire/update.html", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateExpire(@RequestBody String newDateTime) throws Exception {
        JSONObject jsonObject = FastJson2Util.JsonToObject(newDateTime);
        String dateTime = (String) jsonObject.get("newDateTime");
        Long userId = Long.parseLong((String) jsonObject.get("userId"));
        log.info("dateTime: {}", dateTime);
        log.info("userId: {}", userId);
        Users user = usersService.findUsersById(userId);
        user.setExpireDate(DateTimeUtil.convertLocalDate(dateTime));
        usersService.save(user);
        return true;
    }

    @RequestMapping(value = "/updateRoles.html", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateRolesByUserId(@RequestBody String data) throws JsonProcessingException {
        //第一个为userId, 其他的是角色
        log.info("data: {}", data);
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> listData = objectMapper.readValue(data, new TypeReference<>() {
        });
        try {
            Long userId = Long.parseLong(listData.get(0));
            userRolesService.deleteUserRolesByUserId(userId);
            if (listData.size() == 1) {
                //没有选择弱了Code,默认选deny
                Roles role = rolesService.findByRoleCode(Roles.DISABLE);
                UserRoles ur = new UserRoles();
                ur.setUserId(userId);
                ur.setRoleId(role.getRoleId());
                userRolesService.save(ur);
            }
            for (int i = 1; i < listData.size(); i++) {
                Roles role = rolesService.findByRoleCode(listData.get(i));
                UserRoles ur = new UserRoles();
                ur.setUserId(userId);
                ur.setRoleId(role.getRoleId());
                userRolesService.save(ur);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
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
        String png = SystemUtil.getPath() + "/src/main/resources/static/assets/images/avatars/avatar5.png";

        BufferedImage result = ImageIO.read(new File(png));
        System.out.println(result.getWidth());

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

    @RequestMapping(value = "/update/{userAccount}/{username}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse updateUserName(@PathVariable String userAccount, @PathVariable String username) {
        log.info("update username: {}", username);
        Users user = usersService.findByUserAccount(userAccount);
        if (NullUtil.isNull(username)) {
            return AjaxResponse.error(new ResponseException("请输入新用户名称"));
        }
        user.setUsername(username);
        user = usersService.saveAndFlush(user);
        if (getCurrentUser().getUserAccount().equals(user.getUserAccount())) {
            setUsersSaSession(user);
        }
        return AjaxResponse.success(user);
    }

}
