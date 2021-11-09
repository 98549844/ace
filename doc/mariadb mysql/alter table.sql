--alter table 旧名 rename (to)新名,下面2种方式都可以
alter table my_contacts rename students;
alter table my_contacts rename to students;

--alter table 表名 change  ( column)原列名 新列名 列类型;
以下二选一都可以
alter table students change gender 性别 varchar(4);
alter table students change column gender 性别 varchar(4);

ALTER TABLE table_name
    ADD column_name datatype;

ALTER TABLE table_name
DROP COLUMN column_name;

ALTER TABLE table_name
ALTER COLUMN column_name datatype

