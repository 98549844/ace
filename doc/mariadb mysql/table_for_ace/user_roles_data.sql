

insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName,version)
values (1, sysdate(), 1, sysdate(), 'Administrator', null, 'garlam_au@qq.com', null, 'M', null, null, 1, sysdate(), '95186540',
        '909394', null, 'A', 'A1', 'Garlam Au', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName,version)
values (2, sysdate(), 2, sysdate(), 'Information', null, 'peter_wong@qq.com', null, 'M', null, null, 2, sysdate(), '12314564',
        '909394', null, 'A', 'B1', 'Peter Wong', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName,version)
values (3, sysdate(), 3, sysdate(), 'Editor', null, 'mary_leeg@qq.com', null, 'F', null, null, 3, sysdate(), '44557878',
        '909394', null, 'A', 'C2', 'Mary Lee', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName,version)
values (4, sysdate(), 1, sysdate(), 'Disable', null, 'kalam@qq.com', null, 'M', null, null, 1, sysdate(), '95186540',
        '909394', null, 'A', 'A2', 'Ka Lam', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName,version)
values (5, sysdate(), 2, sysdate(), 'Logger', null, 'may_tang@qq.com', null, 'M', null, null, 2, sysdate(), '12314564',
        '909394', null, 'A', 'B2', 'May Tang', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName,version)
values (6, sysdate(), 2, sysdate(), 'Information', null, 'frank_chow@qq.com', null, 'M', null, null, 2, sysdate(), '12314564',
        '909394', null, 'A', 'B3', 'Frank Chow', 1);
insert into users (userId, birthday, createdBy, createdDate, description, domain, email, expireDate, gender, hostName,
                   ip, lastUpdatedBy, lastUpdateDate, mobile, password, remark, status, userAccount, userName,version)
values (7, sysdate(), 2, sysdate(), 'Disable', null, 'eric_luk@qq.com', null, 'M', null, null, 2, sysdate(), '12314564',
        '909394', null, 'D', 'D1', 'Eric Luk', 1);
commit;

select userId, birthday,  description,  email, expireDate, gender,
       ip, mobile, password,  status, userAccount, userName
from users
order by userId;



#------------------------------------------------

insert into roles (roleId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate, roleCode)
values (1, 1, sysdate(), 'Administrator', 1, sysdate(), 'ADMIN');
insert into roles (roleId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate, roleCode)
values (2, 1, sysdate(), 'Disable', 1, sysdate(), 'DISABLE');

insert into roles (roleId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate, roleCode)
values (3, 1, sysdate(), 'Information', 1, sysdate(), 'INFORMATION');
insert into roles (roleId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate, roleCode)
values (4, 1, sysdate(), 'Editor', 1, sysdate(), 'EDITOR');
insert into roles (roleId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate, roleCode)
values (5, 1, sysdate(), 'Logger', 1, sysdate(), 'LOGGER');
commit;

select *
from roles;


insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId)
values (1, 1, sysdate(), 1, sysdate(), 1, 1);
insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId)
values (2, 1, sysdate(), 1, sysdate(), 2, 4);

insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId)
values (3, 1, sysdate(), 1, sysdate(), 3, 2);
insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId)
values (4, 1, sysdate(), 1, sysdate(), 4, 3);
insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId)
values (5, 1, sysdate(), 1, sysdate(), 5, 5);
insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId)
values (6, 1, sysdate(), 1, sysdate(), 3, 6);
insert into user_roles (userRolesId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, userId)
values (7, 1, sysdate(), 1, sysdate(), 2 , 7);
commit;


select r.roleId, r.roleCode, u.description, u.userId, u.userName
from users u,
     user_roles ur,
     roles r
where u.userId = ur.userId
  and r.roleId = ur.roleId order by u.userId;



#------------------------------------------------

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status)
VALUES (1, 1, sysdate(), 'Allow all operation', 1, sysdate(), 0, 'ALL', 'A');

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status)
VALUES (2, 1, sysdate(), 'Insert only', 1, sysdate(), 1, 'INSERT', 'A');

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status)
VALUES (3, 1, sysdate(), 'Update only', 1, sysdate(), 2, 'UPDATE', 'A');

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status)
VALUES (4, 1, sysdate(), 'Delete only', 1, sysdate(), 3, 'DELETE', 'A');

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status)
VALUES (5, 1, sysdate(), 'Read only', 1, sysdate(), 4, 'SELECT', 'A');

#select update
insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status)
VALUES (6, 1, sysdate(), 'Allow select update', 1, sysdate(), 6, 'SELECT UPDATE', 'A');
#select insert
insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status)
VALUES (7, 1, sysdate(), 'Allow select insert', 1, sysdate(), 7, 'SELECT INSERT', 'A');
#select update insert
insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status)
VALUES (8, 1, sysdate(), 'Allow select update insert', 1, sysdate(), 8, 'SELECT UPDATE INSERT', 'A');

insert into permissions (permissionsId, createdBy, createdDate, description, lastUpdatedBy, lastUpdateDate,
                         permissionCode, action, status)
VALUES (9, 1, sysdate(), 'Deny any operation', 1, sysdate(), 10, 'DENY', 'A');

commit;


select permissionsId,  description, permissionCode, action, status
from permissions;
#------------------------------------------------

#admin role: allow all operation
insert into role_permissions (rolePermissionsId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, permissionsId)
VALUES (1, 1, sysdate(),1, sysdate(), 1,1 );

#information
insert into role_permissions (rolePermissionsId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, permissionsId)
VALUES (2, 1, sysdate(),1, sysdate(), 3,5 );
#logger
insert into role_permissions (rolePermissionsId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, permissionsId)
VALUES (3, 1, sysdate(),1, sysdate(), 5,7 );

#editor
insert into role_permissions (rolePermissionsId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, permissionsId)
VALUES (4, 1, sysdate(),1, sysdate(), 4,8 );


#disable role: deny all operation
insert into role_permissions (rolePermissionsId, createdBy, createdDate, lastUpdatedBy, lastUpdateDate, roleId, permissionsId)
VALUES (5, 1, sysdate(),1, sysdate(), 2,9 );

commit;


select roleId,roleCode,description from roles ;

/*
select p.permissionsId, p.permissionCode, p.permissionName, r.roleId, r.roleCode, u.userId, u.userName
from role_permissions rp,
     permissions p,
     roles r,
     user_roles ur,
     users u
where rp.permissionsId = p.permissionsId
  and rp.roleId = r.roleId
  and ur.roleId = r.roleId
  and ur.userId = u.userId;*/


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

