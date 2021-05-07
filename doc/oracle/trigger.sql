--1. 查all_triggers表得到trigger_name
SELECT trigger_name
FROM all_triggers
WHERE table_name = 'VEH_POUND_PARK_SPACE_REQ';

--2.根据trigger_name查询出触发器详细信息

SELECT DISTINCT (text)
FROM all_source
WHERE type = 'TRIGGER'
  AND name IN ('TRG_VEH_POUND_PARK_SPA_1_AIUDR'
    --,
    -- 'TRG_VEH_POUND_PARK_SPA_1_AUR',
    --  'TRG_VEH_POUND_PARK_SPA_1_AUS',
    --  'TRG_VEH_POUND_PARK_SPA_1_BUR'
    );






--test table
CREATE TABLE STUDENT ---创建student表
(
    id        NUMBER(19),   --id
    stu_no    VARCHAR2(20), --学号
    stu_name  VARCHAR2(32), --姓名
    stu_age   NUMBER,       --年龄
    stu_major VARCHAR2(32)  --专业
);

CREATE TABLE STU_LOG ---创建stu_log表，用于记录对student表的操作日志
(
    log_id      NUMBER,        --日志id
    log_action  VARCHAR2(100), --操作名称
    log_date    DATE,          --操作时间
    log_message VARCHAR2(32)   --
);



--触发器是一种在事件发生时隐式地自动执行的PL/SQL块, 不能接受参数, 不能被显式调用

--对数据表进行DML语句操作（如insert、update、delete）时所触发的触发器, 可以分为: 
--语句级触发器或行级触发器: 行级触发器会对数据库表中的受影响的每一行触发一次触发器代码, 语句级触发器则只触发一次, 与语句所影响到的行数无关
--before触发器或after触发器: before触发器在触发事件发生之前执行触发器代码, after触发器则在触发事件发生之后执行

--DML触发器
CREATE OR REPLACE TRIGGER trigger_name
    BEFORE --before or after
        INSERT OR DELETE OR UPDATE --insert update delete
        OF CIS2.POLICE_CASE.RN
    ON POLICE_CASE
    FOR EACH ROW -- 每一行触发
DECLARE
    next_id NUMBER;
BEGIN
    --logic
END;


CREATE OR REPLACE TRIGGER modify_stu
    AFTER INSERT OR DELETE OR UPDATE OF stu_name
    ON student
    FOR EACH ROW
BEGIN
    --logic
END;

-- trigger_name: 触发器名称
-- before | after: 指定触发器是在触发事件发生之前触发还暗示发生之后触发
-- trigger_event: 触发事件, 在DML触发器中主要为 insert update delete 等
-- table_name: 表名, 表示发生触发器作用的对象
-- for each row: 指定创建的是行级触发器, 若没有该子句则创建的是语句级触发器
-- when trigger_condition: 添加的触发条件
-- trigger_body: 触发体, 是标准的PL/SQL语句块


--替代触发器（instead of触发器）
CREATE OR REPLACE TRIGGER trigger_name --触发器名称
    INSTEAD OF police_case.rn --触发事件
ON police_case --视图名称
FOR EACH ROW  --替代触发器必须指定为行级的触发器
DECLARE
    next_id NUMBER;
BEGIN
    --logic
END; --触发体，PL/SQL块


--系统事件触发器
