CREATE TABLE ace.test
(
    id               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id          VARCHAR(100)    NOT NULL COMMENT 'user id',
    user_name        VARCHAR(100)    NOT NULL COMMENT 'user name',
    email            VARCHAR(100) COMMENT 'user email',
    created_date     date                     default sysdate() comment 'create date',
    last_update_date date                     default sysdate() comment 'last update date',
    created_by       varchar(100)    not null  comment 'created user id',
    last_update_by   varchar(100)    not null  comment 'last update user id',
    version          int                      default 1,
    PRIMARY KEY (id)
) COMMENT ='test information';
commit;


drop table ace.test;
commit;
