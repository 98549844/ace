delete
from users
where userId is not null;
delete
from roles
where roleId is not null;
delete
from user_roles
where userRolesId is not null;
delete
from permissions
where permissionsId is not null;
delete
from role_permissions
where rolePermissionsId is not null;



insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName, version)
values (1001, sysdate(), 1, sysdate(), 'Administrator', null, 'garlam_au@qq.com', null, 'M', null, null, 1, sysdate(),
        '95186540',
        '$2a$08$yN1CpSMd1axnrqDVQXWYxulE1dFt5L3KnoRr9PzbUWj0tQ4Uihtm2', null, 'A', 'garlam', 'Garlam Au', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName, version)
values (1002, sysdate(), 2, sysdate(), 'Information', null, 'peter_wong@qq.com', null, 'M', null, null, 2, sysdate(),
        '12314564',
        '$2a$08$yN1CpSMd1axnrqDVQXWYxulE1dFt5L3KnoRr9PzbUWj0tQ4Uihtm2', null, 'A', 'peter', 'Peter Wong', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName, version)
values (1003, sysdate(), 3, sysdate(), 'Editor', null, 'mary_leeg@qq.com', null, 'F', null, null, 3, sysdate(),
        '44557878',
        '$2a$08$yN1CpSMd1axnrqDVQXWYxulE1dFt5L3KnoRr9PzbUWj0tQ4Uihtm2', null, 'A', 'mary', 'Mary Lee', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName, version)
values (1004, sysdate(), 1, sysdate(), 'Disable', null, 'kalam@qq.com', null, 'M', null, null, 1, sysdate(), '95186540',
        '$2a$08$yN1CpSMd1axnrqDVQXWYxulE1dFt5L3KnoRr9PzbUWj0tQ4Uihtm2', null, 'A', 'kalam', 'Ka Lam', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName, version)
values (1005, sysdate(), 2, sysdate(), 'Logger', null, 'may_tang@qq.com', null, 'M', null, null, 2, sysdate(),
        '12314564',
        '$2a$08$yN1CpSMd1axnrqDVQXWYxulE1dFt5L3KnoRr9PzbUWj0tQ4Uihtm2', null, 'A', 'may', 'May Tang', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName, version)
values (1006, sysdate(), 2, sysdate(), 'Information', null, 'frank_chow@qq.com', null, 'M', null, null, 2, sysdate(),
        '12314564',
        '$2a$08$yN1CpSMd1axnrqDVQXWYxulE1dFt5L3KnoRr9PzbUWj0tQ4Uihtm2', null, 'A', 'frank', 'Frank Chow', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName, version)
values (1007, sysdate(), 2, sysdate(), 'Disable', null, 'eric_luk@qq.com', null, 'M', null, null, 2, sysdate(),
        '12314564',
        '$2a$08$yN1CpSMd1axnrqDVQXWYxulE1dFt5L3KnoRr9PzbUWj0tQ4Uihtm2', null, 'D', 'eric', 'Eric Luk', 1);
commit;



select userId,
       birthday,
       description,
       email,
       expireDate,
       gender,
       ip,
       mobile,
       password,
       status,
       userAccount,
       userName
from users
order by userId;


#------------------------------------------------

insert into roles (roleId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate, roleCode, version)
values (1001, 1, sysdate(), 'Administrator', 1, sysdate(), 'ADMIN', 1);
insert into roles (roleId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate, roleCode, version)
values (1002, 1, sysdate(), 'Disable', 1, sysdate(), 'DISABLE', 1);

insert into roles (roleId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate, roleCode, version)
values (1003, 1, sysdate(), 'Information', 1, sysdate(), 'INFORMATION', 1);
insert into roles (roleId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate, roleCode, version)
values (1004, 1, sysdate(), 'Editor', 1, sysdate(), 'EDITOR', 1);
insert into roles (roleId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate, roleCode, version)
values (1005, 1, sysdate(), 'Logger', 1, sysdate(), 'LOGGER', 1);
commit;

select *
from roles;


insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId, version)
values (1001, 1, sysdate(), 1, sysdate(), 1001, 1001, 1);
insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId, version)
values (1002, 1, sysdate(), 1, sysdate(), 1002, 1004, 1);

insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId, version)
values (1003, 1, sysdate(), 1, sysdate(), 1003, 1002, 1);
insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId, version)
values (1004, 1, sysdate(), 1, sysdate(), 1004, 1003, 1);
insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId, version)
values (1005, 1, sysdate(), 1, sysdate(), 1005, 1005, 1);
insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId, version)
values (1006, 1, sysdate(), 1, sysdate(), 1003, 1006, 1);
insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId, version)
values (1007, 1, sysdate(), 1, sysdate(), 1002, 1007, 1);

commit;

select r.roleId, r.roleCode, u.description, u.userId, u.userName
from users u,
     user_roles ur,
     roles r
where u.userId = ur.userId
  and r.roleId = ur.roleId
order by u.userId;

#------------------------------------------------

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status, version)
VALUES (1001, 1, sysdate(), 'Allow all operation', 1, sysdate(), 0, 'ALL', 'A', 1);

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status, version)
VALUES (1002, 1, sysdate(), 'Insert only', 1, sysdate(), 1, 'INSERT', 'A', 1);

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status, version)
VALUES (1003, 1, sysdate(), 'Update only', 1, sysdate(), 2, 'UPDATE', 'A', 1);

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status, version)
VALUES (1004, 1, sysdate(), 'Delete only', 1, sysdate(), 3, 'DELETE', 'A', 1);

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status, version)
VALUES (1005, 1, sysdate(), 'Read only', 1, sysdate(), 4, 'SELECT', 'A', 1);

#select update
insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status, version)
VALUES (1006, 1, sysdate(), 'Allow select update', 1, sysdate(), 6, 'SELECT UPDATE', 'A', 1);
#select insert
insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status, version)
VALUES (1007, 1, sysdate(), 'Allow select insert', 1, sysdate(), 7, 'SELECT INSERT', 'A', 1);
#select update insert
insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status, version)
VALUES (1008, 1, sysdate(), 'Allow select update insert', 1, sysdate(), 8, 'SELECT UPDATE INSERT', 'A', 1);

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status, version)
VALUES (1009, 1, sysdate(), 'Deny any operation', 1, sysdate(), 10, 'DENY', 'A', 1);

commit;


select permissionsId, description, permissionCode, action, status
from permissions;
#------------------------------------------------

#admin role: allow all operation
insert into role_permissions (rolePermissionsId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId,
                              permissionsId, version)
VALUES (1001, 1, sysdate(), 1, sysdate(), 1001, 1001, 1);

#information
insert into role_permissions (rolePermissionsId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId,
                              permissionsId, version)
VALUES (1002, 1, sysdate(), 1, sysdate(), 1003, 1005, 1);
#logger
insert into role_permissions (rolePermissionsId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId,
                              permissionsId, version)
VALUES (1003, 1, sysdate(), 1, sysdate(), 1005, 1007, 1);

#editor
insert into role_permissions (rolePermissionsId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId,
                              permissionsId, version)
VALUES (1004, 1, sysdate(), 1, sysdate(), 1004, 1008, 1);


#disable role: deny all operation
insert into role_permissions (rolePermissionsId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId,
                              permissionsId, version)
VALUES (1005, 1, sysdate(), 1, sysdate(), 1002, 1009, 1);

commit;

select roleId, roleCode, description
from roles;


select p.permissionsId,
       p.permissionCode,
       p.action,
       p.description,
       r.roleId,
       r.roleCode,
       u.userId,
       u.description,
       u.userName
from role_permissions rp,
     permissions p,
     roles r,
     user_roles ur,
     users u
where 1 = 1
  and rp.permissionsId = p.permissionsId
  and rp.roleId = r.roleId
  and ur.roleId = r.roleId
  and ur.userId = u.userId
order by userId
;

