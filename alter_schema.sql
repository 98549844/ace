show schemas;
show tables;
select version();

create schema ace default character set utf8mb4 collate utf8mb4_unicode_ci;#创建数据库
grant select, insert, update, delete, create on ace.* to root;#用户授权数据库
flush privileges;#立即启用配置


grant select, insert, update, delete, create on ace.* to garlam;#用户授权数据库
commit;
drop schema ace;
#删除数据库


-- auto-generated definition
create table ace.users
(
    userId         bigint       not null
        primary key,
    createdBy      bigint       null,
    createdDate    datetime(6)  null,
    lastUpdateDate datetime(6)  null,
    lastUpdatedBy  bigint       null,
    version        int          null,
    age            bigint       not null,
    dateOfBirth    datetime(6)  null,
    description    varchar(255) null,
    domain         varchar(255) null,
    email          varchar(255) null,
    enabled        bit          null,
    expireDate     datetime(6)  null,
    gender         varchar(255) null,
    hostName       varchar(255) null,
    ip             varchar(255) null,
    loginDateTime  datetime(6)  null,
    mobile         varchar(255) null,
    password       varchar(255) null,
    region         varchar(255) null,
    remark         varchar(255) null,
    status         varchar(255) null,
    userAccount    varchar(255) null,
    username       varchar(255) null,
    constraint constraint_email
        unique (email),
    constraint constraint_userAccount
        unique (userAccount)
);

INSERT INTO ace.users (userId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, age, dateOfBirth,
                       description, domain, email, enabled, expireDate, gender, hostName, ip, loginDateTime, mobile,
                       password, region, remark, status, userAccount, username)
VALUES (1, 0, '2024-03-27 07:37:24.933661', '2024-03-27 07:37:24.933661', 0, 1, 0, '2024-03-27 07:37:24.920719',
        'administrator', 'ace.com', 'admin@ace.com', true, '2124-03-27 07:37:24.920736', null, null, '127.0.0.1',
        '2024-03-27 07:37:24.920726', '0000 0000', '$2a$11$eXmG/HZLumI4/Jkw2FYpUuO03WJh2PErDO4YG/j3AyFOoe831vGLK', null,
        'ACE APPLICATION', 'ACTIVE', 'admin', 'administrator');
INSERT INTO ace.users (userId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, age, dateOfBirth,
                       description, domain, email, enabled, expireDate, gender, hostName, ip, loginDateTime, mobile,
                       password, region, remark, status, userAccount, username)
VALUES (2, 0, '2024-03-27 07:37:25.108351', '2024-03-31 08:52:03.806891', 0, 50, 0, '2024-03-27 07:37:25.102532',
        'administrator', 'ace.com', 'garlam@ace.com', true, '2124-03-27 07:37:25.102545', 'M', '192.168.1.100',
        '192.168.1.100', '2024-03-31 08:52:03.740240', '9518 6540',
        '$2a$11$4tXwKrv6VR1fPPpNpUzbDOcuQpwXFuDTNkoAvLD4/.K.xv0FvCf8O', '内网IP', 'ACE APPLICATION', 'ACTIVE', 'garlam',
        'garlam');
INSERT INTO ace.users (userId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, age, dateOfBirth,
                       description, domain, email, enabled, expireDate, gender, hostName, ip, loginDateTime, mobile,
                       password, region, remark, status, userAccount, username)
VALUES (3, 0, '2024-03-27 07:37:32.242752', '2024-03-27 07:37:32.242752', 0, 1, 0, '2024-03-27 07:37:31.043711',
        'administrator', null, 'timothy_au@qq.com', true, '2027-03-27 07:37:31.043730', 'M', null, null, null,
        '55550000', '$2a$11$JrRohMySIOJAEeaUK8DVZeEV2VDXKscSrsKVdCDISkJxgv4vn1TNG', null, null, 'ACTIVE', 'timothy',
        'Timothy Au');
INSERT INTO ace.users (userId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, age, dateOfBirth,
                       description, domain, email, enabled, expireDate, gender, hostName, ip, loginDateTime, mobile,
                       password, region, remark, status, userAccount, username)
VALUES (4, 0, '2024-03-27 07:37:32.243649', '2024-03-27 07:37:32.243649', 0, 1, 0, '2024-03-27 07:37:31.043725', 'user',
        null, 'peter_wong@qq.com', true, '2027-03-27 07:37:31.043737', 'M', null, null, null, '55550000',
        '$2a$11$FT3REHiRaMr9ro54w1iuHOhSTVMHsxhNAH.Zegr6ElEcy2zAN.iXK', null, null, 'ACTIVE', 'peter', 'Peter Wong');
INSERT INTO ace.users (userId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, age, dateOfBirth,
                       description, domain, email, enabled, expireDate, gender, hostName, ip, loginDateTime, mobile,
                       password, region, remark, status, userAccount, username)
VALUES (5, 0, '2024-03-27 07:37:32.244684', '2024-03-27 07:37:32.244684', 0, 1, 0, '2024-03-27 07:37:31.043726',
        'viewer', null, 'mary_leeg@qq.com', true, '2027-03-27 07:37:31.043738', 'M', null, null, null, '55550000',
        '$2a$11$BCiPnB/EOq7ky5cwnJ9ON.MH7G6L9QRHNuj47.n9dawMKHyuo5yd6', null, null, 'ACTIVE', 'mary', 'Mary Lee');
INSERT INTO ace.users (userId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, age, dateOfBirth,
                       description, domain, email, enabled, expireDate, gender, hostName, ip, loginDateTime, mobile,
                       password, region, remark, status, userAccount, username)
VALUES (6, 0, '2024-03-27 07:37:32.245307', '2024-03-27 07:37:32.245307', 0, 1, 0, '2024-03-27 07:37:31.043727',
        'disable', null, 'kalam@qq.com', false, '2027-03-27 07:37:31.043739', 'M', null, null, null, '55550000',
        '$2a$11$kJNUoZ14/7mguKRve4o.GeanVU2pTEfGMmEs4RfG0Em15BG1ytj2e', null, null, 'INACTIVE', 'kalam', 'Ka Lam');
INSERT INTO ace.users (userId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, age, dateOfBirth,
                       description, domain, email, enabled, expireDate, gender, hostName, ip, loginDateTime, mobile,
                       password, region, remark, status, userAccount, username)
VALUES (7, 0, '2024-03-27 07:37:32.246027', '2024-03-27 07:37:32.246027', 0, 1, 0, '2024-03-27 07:37:31.043728',
        'viewer', null, 'may_tang@qq.com', true, '2027-03-27 07:37:31.043740', 'M', null, null, null, '55550000',
        '$2a$11$Zro.J4QzxHbQ3wJRSpvCgOTqcyJpEw2/8Jsc1lEt3XlLy2vkvDsvm', null, null, 'ACTIVE', 'may', 'May Tang');
INSERT INTO ace.users (userId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, age, dateOfBirth,
                       description, domain, email, enabled, expireDate, gender, hostName, ip, loginDateTime, mobile,
                       password, region, remark, status, userAccount, username)
VALUES (8, 0, '2024-03-27 07:37:32.246619', '2024-03-27 07:37:32.246619', 0, 1, 0, '2024-03-27 07:37:31.043728', 'user',
        null, 'frank_chow@qq.com', true, '2027-03-27 07:37:31.043740', 'M', null, null, null, '55550000',
        '$2a$11$1WxTAU0v0WjYT0.tCsHEvOeVd61XiHEzba.dEFIwFwAgmU6Sv4g12', null, null, 'ACTIVE', 'frank', 'Frank Chow');
INSERT INTO ace.users (userId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, age, dateOfBirth,
                       description, domain, email, enabled, expireDate, gender, hostName, ip, loginDateTime, mobile,
                       password, region, remark, status, userAccount, username)
VALUES (9, 0, '2024-03-27 07:37:32.247231', '2024-03-27 07:37:32.247231', 0, 1, 0, '2024-03-27 07:37:31.043729',
        'disable', null, 'eric_luk@qq.com', true, '2027-03-27 07:37:31.043741', 'M', null, null, null, '55550000',
        '$2a$11$rNst76MuwHs7Go5EmZm8/.SmgMB00aKYuGD6ZBbbvNHccEWwa5Z7u', null, null, 'ACTIVE', 'eric', 'Eric Luk');
INSERT INTO ace.users (userId, createdBy, createdDate, lastUpdateDate, lastUpdatedBy, version, age, dateOfBirth,
                       description, domain, email, enabled, expireDate, gender, hostName, ip, loginDateTime, mobile,
                       password, region, remark, status, userAccount, username)
VALUES (10, 0, '2024-03-27 07:37:32.248018', '2024-03-27 07:37:32.248018', 0, 1, 0, '2024-03-27 07:37:31.043729',
        'administrator', null, 'root@ace.com', true, '2027-03-27 07:37:31.043742', 'M', null, null, null, null,
        '$2a$11$uST2NT8YICPn8ZrPGysNZO1i6DS8PkfTP2sumBMtlqGZGiDmoAhlS', null, null, 'ACTIVE', 'root', 'Root');
commit;

# drop table users;
select *
from users;
CREATE USER 'haproxy'@'%' IDENTIFIED BY '';
flush privileges;

SELECT user
FROM mysql.user;

