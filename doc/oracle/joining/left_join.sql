--SQL LEFT JOIN 关键字
--LEFT JOIN 关键字从左表（table1 t1）返回所有的行，
-- 即使右表（table2 t2）中没有匹配。如果右表中没有匹配，则结果为 NULL

SELECT t1.*, t2.*
FROM table1 t1
         LEFT JOIN table2 t2 ON t1.column_name = t2.column_name;

SELECT t1.*, t2.*
FROM table1 t1
         LEFT OUTER JOIN table2 t2 ON t1.column_name = t2.column_name;