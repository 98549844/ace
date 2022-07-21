with xxx as 用法

CTE后面也可以跟其他的CTE, 但只能使用一个with,
多个CTE中间用逗号(,)分隔,如下面的SQL语句所示:
with
    cte1 as
    (
    select * from table1 where name like 'abc%'
    ),
    cte2 as
    (
    select * from table2 where id > 20
    ),
    cte3 as
    (
    select * from table3 where price < 100
    )
select a.* from cte1 a, cte2 b, cte3 c where a.id = b.id and a.id = c.id


如果CTE的表达式名称与某个数据表或视图重名,
则紧跟在该CTE后面的SQL语句使用的仍然是CTE,
当然,后面的SQL语句使用的就是数据表或视图了,如下面的SQL语句所示:

with
    table1 as
    (
    select * from persons where age < 30
    )
select * from table1  --  使用了名为table1的公共表表达式
select * from table1  --  使用了名为table1的数据表