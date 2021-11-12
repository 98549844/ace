insert into menus (menuId, menu, parentId, parentMenu, createdBy, createdDate, lastUpdateDate,
                   lastUpdatedBy, version)
values (1, '用户管理', 0, 'ROOT', 1, sysdate(), sysdate(), 1, 1);

insert into menus (menuId, menu, parentId, parentMenu, createdBy, createdDate, lastUpdateDate,
                   lastUpdatedBy, version)
values (2, '用户列表', 1, '用户管理', 1, sysdate(), sysdate(), 1, 1);

insert into menus (menuId, menu, parentId, parentMenu, createdBy, createdDate, lastUpdateDate,
                   lastUpdatedBy, version)
values (3, '角色列表', 1, '用户管理', 1, sysdate(), sysdate(), 1, 1);

insert into menus (menuId, menu, parentId, parentMenu, createdBy, createdDate, lastUpdateDate,
                   lastUpdatedBy, version)
values (4, '权限列表', 1, '用户管理', 1, sysdate(), sysdate(), 1, 1);



insert into menus (menuId, menu, parentId, parentMenu, createdBy, createdDate, lastUpdateDate,
                   lastUpdatedBy, version)
values (5, '菜单管理', 0, 'ROOT', 1, sysdate(), sysdate(), 1, 1);
insert into menus (menuId, menu, parentId, parentMenu, createdBy, createdDate, lastUpdateDate,
                   lastUpdatedBy, version)
values (6, '编辑菜单', 5, '菜单管理', 1, sysdate(), sysdate(), 1, 1);


select menuId,
       menu,
       parentId,
       parentMenu,
       version
from menus;
commit;

drop table menus;