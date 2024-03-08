ALTER TABLE ace.users
    CHANGE COLUMN username TEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';


#要让MySQL数据库在执行SQL语句时不区分大小写，
#可以在连接字符串中添加一个参数。
#具体而言，
#需要将参数lower_case_table_names设置为1，
#这样MySQL将不再区分表名和列名的大小写。
#在jdbc连接上加入
#lower_case_table_names=1
