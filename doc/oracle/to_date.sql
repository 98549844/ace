SELECT to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') AS nowTime
FROM dual;


--Oracle trunc()函数的用法
/**************日期********************/
select to_date('15-05-18 18:15:30','yy-mm-dd hh24:mi:ss') from dual;
SELECT trunc(sysdate)
FROM dual; --2013-01-06 今天的日期为2013-01-06
SELECT trunc(sysdate, 'yy')
FROM dual; --2013-01-01 返回当年第一天
SELECT trunc(sysdate, 'mm')
FROM dual; --2013-01-01 返回当月第一天.
SELECT trunc(trunc(sysdate, 'MM') - 1, 'MM')
FROM dual; --2013-01-01 返回当月前一月第一天.
SELECT trunc(sysdate, 'dd')
FROM dual; --2013-01-06 返回当前年月日
SELECT trunc(sysdate, 'yyyy')
FROM dual; --2013-01-01 返回当年第一天
SELECT trunc(sysdate, 'd')
FROM dual; --2013-01-06 (星期天)返回当前星期的第一天
SELECT trunc(sysdate, 'hh')
FROM dual; --2013-01-06 17:00:00 当前时间为17:35
SELECT trunc(sysdate, 'mi')
FROM dual; --2013-01-06 17:35:00 TRUNC()函数没有秒的精确