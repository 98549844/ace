show schemas ;
show tables;
select version();

create schema ace default character set utf8mb4 collate utf8mb4_unicode_ci;#创建数据库
grant select,insert,update,delete,create on ace.* to root;#用户授权数据库
flush privileges;#立即启用配置

create user 'garlam'@'%' identified by '';
GRANT ALL PRIVILEGES ON ace.* TO 'garlam'@'%';
# grant select,insert,update,delete,create on ace.* to garlam;#用户授权数据库
commit;
drop schema ace;#删除数据库


