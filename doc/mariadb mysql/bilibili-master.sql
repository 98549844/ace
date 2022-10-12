# bilibili
create schema video default character set utf8 collate utf8_general_ci;#创建数据库
grant select, insert, update, delete, create on video.* to root;#用户授权数据库 for mariadb
grant select, insert, update, delete, create on video.* to garlam;#用户授权数据库 for mariadb
flush privileges;#立即启用配置
commit;
drop schema video;#删除数据库


CREATE TABLE bilibili.forum
(
    forumID       VARCHAR(100),
    forumBT       VARCHAR(100),
    forummessage  VARCHAR(100),
    forumuserName VARCHAR(100),
    forumTime     VARCHAR(100),
    forumliebie   VARCHAR(100),
    forumAmount   VARCHAR(100),
    firumhand     VARCHAR(100)
);
commit;

CREATE TABLE bilibili.forumreply
(
    replyid      VARCHAR(100),
    replyneirong VARCHAR(100),
    replytime    VARCHAR(100),
    replyhand    VARCHAR(100),
    replytieziid VARCHAR(100),
    replyname    int
);
commit;



CREATE TABLE bilibili.grids
(
    gridsID    VARCHAR(100),
    girdsName  VARCHAR(100),
    girdsjiage VARCHAR(100),
    girdsimg   VARCHAR(100),
    girdskucun VARCHAR(100)
);
commit;



CREATE TABLE bilibili.message
(
    messageID       VARCHAR(100),
    messagevideoID  VARCHAR(100),
    messageuserID   VARCHAR(100),
    messageuserName VARCHAR(100),
    message         VARCHAR(100),
    messageTime     VARCHAR(100),
    messageHand     VARCHAR(100)
);
commit;


CREATE TABLE bilibili.ordertable
(
    orderID         VARCHAR(100),
    orderuserName   VARCHAR(100),
    orderIgridsName VARCHAR(100),
    ordergridsImg   VARCHAR(100),
    orderzongRMB    VARCHAR(100),
    orderStat       VARCHAR(100),
    orderTime       VARCHAR(100),
    orderAddr       VARCHAR(100)
);
commit;


CREATE TABLE bilibili.ShoppingCart
(
    cartID        VARCHAR(100),
    userName      VARCHAR(100),
    shoopingID    VARCHAR(100),
    shoopingName  VARCHAR(100),
    shoopingImg   VARCHAR(100),
    shoopingjiage VARCHAR(100)
);
commit;


CREATE TABLE bilibili.user
(
    userID          VARCHAR(100),
    userMingzi      VARCHAR(100),
    userName        VARCHAR(100),
    passWord        VARCHAR(100),
    useryinghangka  VARCHAR(100),
    usersex         VARCHAR(100),
    userHand        VARCHAR(100),
    userAddress     VARCHAR(100),
    userPhone       VARCHAR(100),
    userQQ          VARCHAR(100),
    userEmial       VARCHAR(100),
    userCollection  VARCHAR(100),
    userState       VARCHAR(100),
    userLoginTime   VARCHAR(100),
    userIP          VARCHAR(100),
    userPaypassword VARCHAR(100),
    userRMB         VARCHAR(100)
);
commit;

CREATE TABLE bilibili.video
(
    videoID         VARCHAR(100),
    videoName       VARCHAR(100),
    videoImage      VARCHAR(100),
    videoAddress    VARCHAR(100),
    videolookTime   VARCHAR(100),
    videoCollection VARCHAR(100),
    videoLeaving    VARCHAR(100),
    videoTime       VARCHAR(100),
    videoState      VARCHAR(100),
    videocAtegory   VARCHAR(100)
);
commit;

CREATE TABLE bilibili.videoTop
(
    Filename VARCHAR(100),
    FileSize BIGINT,
    FileSY   BIGINT,
    tag      int default 0,
    baifenbi int default 0
);
commit;