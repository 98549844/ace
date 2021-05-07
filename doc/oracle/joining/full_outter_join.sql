--SQL FULL OUTER JOIN 关键字
--FULL OUTER JOIN 关键字只要左表（table1 t1）和右表（table2 t2）其中一个表中存在匹配，则返回行.
--FULL OUTER JOIN 关键字结合了 LEFT JOIN 和 RIGHT JOIN 的结果




SELECT t1.*, t2.*
FROM table1 t1
         FULL OUTER JOIN table2 t2 ON t1.column_name = t2.column_name;
