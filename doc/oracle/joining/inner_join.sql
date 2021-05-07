--SQL INNER JOIN 关键字
--INNER JOIN 关键字在表中存在至少一个匹配时返回行

SELECT t1.*, t2.*
FROM table1 t1
         INNER JOIN table2 t2 ON t1.column_name = t2.column_name;


SELECT t1.*, t2.*
FROM table1 t1
         JOIN table2 t2 ON t1.column_name = t2.column_name;