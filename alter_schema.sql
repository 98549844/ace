

create schema ry default character set utf8 collate utf8_general_ci;#创建数据库
grant select,insert,update,delete,create on ry.* to root;#用户授权数据库
flush privileges;#立即启用配置


grant select,insert,update,delete,create on ace.* to garlam;#用户授权数据库
commit;
drop schema imagegallery;#删除数据库

