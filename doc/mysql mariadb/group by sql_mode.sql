-- MySQL错误-this is incompatible with sql_mode=only_full_group_by完美解决方案
-- https://blog.csdn.net/u012660464/article/details/113977173
-- 这个错误发生在mysql 5.7.5 版本及以上版本会出现的问题:
-- mysql 5.7.5版本以上默认的sql配置是:sql_mode=“ONLY_FULL_GROUP_BY”,这个配置严格执行了"SQL92标准".
-- 很多从5.6升级到5.7时,为了语法兼容,大部分都会选择调整sql_mode,使其保持跟5.6一致,为了尽量兼容程序


-- 解决方案一:
-- 使用函数ANY_VALUE()包含报错字段
SELECT ANY_VALUE(ID),USER_ID,ANY_VALUE(problems),ANY_VALUE(last_updated_date) FROM  t_iov_help_feedback GROUP BY USER_ID;

-- 解决方案二:通过sql语句暂时性修改sql_mode
--     去掉ONLY_FULL_GROUP_BY,重新设置值
--         改变全局sql_mode
SET @@global.sql_mode ='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
--         改变已存在的数据库sql_mode
SET sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
--         springboot url: jdbc:mysql://localhost:3306/daatm?拼入
    &sessionVariables=sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION'

-- 解决方案三:通过配置文件永久修改sql_mode
-- 登录进入MySQL
使用命令 mysql -u username -p 进行登陆,然后输入密码,输入SQL:
show variables like '%sql_mode%';
--     编辑my.cnf文件
-- 文件地址一般在:/etc/my.cnf,/etc/mysql/my.cnf
-- 使用vim命令编辑文件,不知道vim命令怎么使用的,可以参考我的另外篇文章:Linux中使用vi工具进行文本编辑
-- 找到sql-mode的位置,去掉ONLY_FULL_GROUP_BY
-- 然后重启MySQL:
-- 有的my.cnf中可能没有sql-mode,需要追加
    sql-mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
service mysql restart



