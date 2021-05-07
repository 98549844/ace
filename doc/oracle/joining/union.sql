-- union和union all的区别是,
-- union会自动压缩多个结果集合中的重复结果，
-- 而union all则将所有的结果全部显示出来，不管是不是重复

SELECT col1 FROM table1 t1
UNION
SELECT col1 FROM table2 t2;


SELECT col1 FROM table1 t1
UNION ALL
SELECT col1 FROM table2 t2;