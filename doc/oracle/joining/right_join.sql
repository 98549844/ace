--SQL RIGHT JOIN 关键字
--RIGHT JOIN 关键字从右表（table2 t2）返回所有的行，
-- 即使左表（table1 t1）中没有匹配。如果左表中没有匹配，则结果为 NULL

SELECT t1.*, t2.*
FROM table1 t1
         RIGHT JOIN table2 t2 ON table1 t1.column_name=table2 t2.column_name;

SELECT t1.*, t2.*
FROM table1 t1
         RIGHT OUTER JOIN table2 t2 ON t1.column_name = t2.column_name;