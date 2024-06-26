package com.ace.service;

import com.ace.dao.UsersDao;
import com.ace.exception.PasswordNotMatchException;
import com.ace.exception.UserNotFoundException;
import com.ace.mapper.UsersMapper;
import com.ace.models.entity.*;
import com.ace.models.info.UsersInfo;
import com.ace.utilities.*;
import com.ace.utils.BeanUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname: usersService
 * @Date: 1/7/2021 10:00 下午
 * @Author: garlam
 * @Description:
 */

@Service
public class UsersService {
    private static final Logger log = LogManager.getLogger(UsersService.class.getName());
    private final UsersDao usersDao;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRolesService userRolesService;
    private final RolesService rolesService;
    private final PermissionsService permissionsService;
    private final FilesService filesService;


    @Autowired
    public UsersService(UsersMapper usersMapper, UsersDao usersDao, PasswordEncoder passwordEncoder, UserRolesService userRolesService, RolesService rolesService, PermissionsService permissionsService, FilesService filesService) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
        this.userRolesService = userRolesService;
        this.rolesService = rolesService;
        this.permissionsService = permissionsService;
        this.usersMapper = usersMapper;
        this.filesService = filesService;
    }

    public void compressAvatar(Files f) {
        if (NullUtil.isNull(f)) {
            return;
        }
        try {
            ImageUtil imageUtil = new ImageUtil();
            imageUtil.square(f.getLocation(), null);

            String[] fileName = f.getFileName().split("-");
            String avatar = fileName[0] + "-avatar-" + fileName[1];
            String icon = fileName[0] + "-icon-" + fileName[1];
            ImageUtil.compressPicForScale(f.getLocation(), f.getPath() + avatar + f.getExt(), 80, 2, 400, 400);
            ImageUtil.compressPicForScale(f.getLocation(), f.getPath() + icon + f.getExt(), 10, 2, 60, 60);

            f.setStatus(Files.COMPRESSED);

            Files fAvatar = new Files();
            Files fIcon = new Files();


            fAvatar.setRemark("ACE Application avatar: " + avatar);
            fAvatar.setOriginationName(f.getOriginationName());
            fAvatar.setExt(f.getExt());
            fAvatar.setFileName(avatar);
            fAvatar.setLocation(f.getPath() + avatar + f.getExt());
            fAvatar.setPath(f.getPath());
            fAvatar.setSize((FileUtil.getFileSize(f.getPath() + avatar + f.getExt())));
            fAvatar.setType(Files.IMAGE);
            fAvatar.setStatus("avatar");

            fIcon.setRemark("ACE Application icon: " + icon);
            fIcon.setOriginationName(f.getOriginationName());
            fIcon.setExt(f.getExt());
            fIcon.setFileName(icon);
            fIcon.setLocation(f.getPath() + icon + f.getExt());
            fIcon.setPath(f.getPath());
            fIcon.setSize((FileUtil.getFileSize(f.getPath() + icon + f.getExt())));
            fIcon.setType(Files.IMAGE);
            fIcon.setStatus("icon");

            List<Files> fs = new ArrayList<>();
            fs.add(f);
            fs.add(fAvatar);
            fs.add(fIcon);

            filesService.saveAll(fs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Users> findUsersByUsernameLikeIgnoreCaseOrderByLoginDateTime(String username) {
        List<Users> usersList = usersDao.findUsersByUsernameLikeIgnoreCaseOrderByLoginDateTime(SqlUtil.like(username));
        calcAge(usersList);
        return usersList;
    }

    public List<Users> findAll() {
        List<Users> usersList = usersDao.findAll();
        calcAge(usersList);
        return usersList;
    }


    public List<Users> findAllByMybatis() {
        List<Users> usersList = usersMapper.findAll();
        calcAge(usersList);
        return usersList;
    }

    public Users findUserByMybatis(String acc) {
        Users users = usersMapper.selectByAccount(acc);
        calcAge(users);
        return users;
    }

    public List<Users> findUsersLikeNameByMybatis(String userName) {
        List<Users> usersList = usersMapper.findUsersLikeNameByMybatis(SqlUtil.like(userName));
        calcAge(usersList);
        return usersList;
    }


    public List<Users> findUsersOrderByLoginDateTime(Integer limit) {
        List<Users> usersList = usersDao.findUsersOrderByLoginDateTime(limit == null ? 1000 : limit);
        calcAge(usersList);
        return usersList;
    }

    public List<Users> getUsersOrderByLoginDateTimeLimit(Integer paging, Integer limit) {
        paging = paging * limit;
        List<Users> usersList = usersDao.getUsersOrderByLoginDateTimeLimit(paging, limit);
        calcAge(usersList);
        return usersList;
    }


    public Users findByUserAccount(Users ur) throws UserNotFoundException, PasswordNotMatchException {
        Users user = usersDao.findByUserAccount(ur.getUserAccount());
        if (NullUtil.isNull(user) || NullUtil.isNull(user.getUserId())) {
            //抛出异常，会根据配置跳到登录失败页面
            throw new UserNotFoundException(ur.getUserAccount());
        }
        boolean matches = passwordEncoder.matches(ur.getPassword(), user.getPassword());
        log.info("Match result: {}", matches);
        if (!matches) {
            throw new PasswordNotMatchException();
        }
        calcAge(user);
        return user;
    }

    public Users findByUserAccount(String userAccount) {
        Users user = usersDao.findByUserAccount(userAccount.toLowerCase());
        calcAge(user);
        return user;
    }

    public List<Users> findByUserAccountNotIn(List<String> userAccounts) {
        List<Users> user = usersDao.findByUserAccountNotIn(userAccounts);
        calcAge(user);
        return user;
    }


    public List<Map> findUserRolePermission() {
        return usersDao.findUserRolePermission();
    }

    public List<Map> findUserRolePermissionByUserAccount(String userAccount) {
        return usersDao.findUserRolePermissionByUserAccount(userAccount);
    }

    public Users findUsersByEmail(String email) {
        return usersDao.findUsersByEmail(email);
    }

    public List<Map> findUserRolePermissionDetail() {
        return usersDao.findUserRolePermissionDetail();
    }


    /**
     * using mybatis
     *
     * @param userId
     * @return
     */
    public List<Map> getUserRolePermissionById(Long userId) {
        return usersMapper.getUserRolePermissionById(userId);
    }

    /**
     * using Hibernate
     *
     * @param userId
     * @return
     */
    public List<Map> findUserRolePermissionDetailById(Long userId) {
        return usersDao.findUserRolePermissionDetailById(userId);
    }

    public List<Map> findAllUserRolePermissionByMybatis() {
        return usersMapper.findAllUserRolePermissionByMybatis();
    }

    /**
     * accountRegistration
     *
     * @param users
     * @return
     */
    @Transactional
    public Users accountRegistration(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRoleGroup(Users.VIEWER);
        users.setUsername("ace" + RandomUtil.getRangeInt(1, 999) + "_" + users.getUserAccount());
        users.setExpireDate(LocalDateTime.now().plusYears(3));
        Users u = usersDao.saveAndFlush(users);

        //default role is user
        Roles role = rolesService.findByRoleCode(Roles.USER);

        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(u.getUserId());
        userRoles.setRoleId(role.getRoleId());
        userRolesService.save(userRoles);

        //default permission is select
        Permissions permissions = permissionsService.findPermissionsByPermissionCode(Permissions.SELECT);

        RolePermissions rolePermissions = new RolePermissions();
        rolePermissions.setPermissionsId(permissions.getPermissionsId());
        rolePermissions.setRoleId(role.getRoleId());
        return users;
    }

    @Transactional
    public Users save(Users users) {
        return usersDao.save(users);
    }

    @Transactional
    public Users saveAndFlush(Users users) {
        return usersDao.saveAndFlush(users);
    }

    @Transactional
    public void flush() {
        usersDao.flush();
    }


    public boolean validate(Users users) {
        boolean validate = true;
        if (NullUtil.isNonNull(users)) {
            if (NullUtil.isNull(users.getEmail().trim()) || NullUtil.isNull(users.getUserAccount().trim()) || NullUtil.isNull(users.getPassword())) {
                validate = false;
            }
        }
        return validate;
    }


    public Integer countByUserAccountOrEmail(Users users) {
        return usersDao.countByUserAccountOrEmail(users.getUserAccount(), users.getEmail());
    }

    public int countByUserAccount(String userAccount) {
        return usersDao.countByUserAccount(userAccount);
    }

    public int countByUserId(Long userId) {
        return usersDao.countByUserId(userId);
    }


    public Users findUsersById(long id) {
        Users user = usersDao.findByUserId(id);
        calcAge(user);
        return user;
    }

    /**
     * @param id
     * @return
     */
    public UsersInfo findUsersInfoById(long id) {
        Users user = usersDao.findByUserId(id);
        calcAge(user);
        BeanUtil beanUtil = new BeanUtil();
        UsersInfo usersInfo = beanUtil.copy(user, UsersInfo.class);
        usersInfo.setRoles(rolesService.getRolesByUserId(id));
        return usersInfo;
    }

    /**
     * @param ids
     * @return
     */
    public List<UsersInfo> findUsersInfoById(List<Long> ids) {
        List<Users> users = usersDao.findByUserIdIn(ids);
        BeanUtil beanUtil = new BeanUtil();
        List<UsersInfo> usersInfoList = beanUtil.copyList(users, UsersInfo.class);
        calcAge(users);

        for (UsersInfo usersInfo : usersInfoList) {
            long userId = usersInfo.getUserId();
            usersInfo.setRoles(rolesService.getRolesByUserId(userId));
        }
        return usersInfoList;
    }


    @Transactional
    public boolean saveAll(List<Users> usersIterable) {
        List<Users> usersList = new ArrayList<>();
        for (Users u : usersIterable) {
            String encode = passwordEncoder.encode(u.getPassword());
            u.setPassword(encode);
            usersList.add(u);
        }
        try {
            usersDao.saveAll(usersList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUsers(Users users) {
        try {
            usersDao.updateUsers(users);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateByMybatis(Users users) {
        try {
            int i = usersMapper.updateByAccount(users);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteAll(List<Users> users) {
        try {
            usersDao.deleteAll(users);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(Users users) {
        try {
            usersDao.delete(users);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Long id) {
        try {
            usersDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void deleteAll() {
        usersDao.deleteAll();
    }


    public void calcAge(List<Users> userList) {
        if (NullUtil.isNonNull(userList)) {
            for (Users users : userList) {
                if (NullUtil.isNonNull(users.getDateOfBirth())) {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime dateOfBirth = users.getDateOfBirth();
                    long age = DateTimeUtil.differenceYearsByLocalDateTime(dateOfBirth, now);
                    users.setAge(age);
                }
            }
        } else {
            log.warn("User list is null");
        }
    }

    public void calcAge(Users user) {
        if (NullUtil.isNonNull(user) && NullUtil.isNonNull(user.getDateOfBirth())) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime birthDate = user.getDateOfBirth();
            long age = DateTimeUtil.differenceYearsByLocalDateTime(birthDate, now);
            user.setAge(age);
        } else {
            log.warn("User not exist !");
        }
    }

    public List<Users> findAllByRecord() {
        return usersDao.findAllByRecord(true);
    }

}

