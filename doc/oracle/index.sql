--查看索引个数和类别:
SELECT INDEX_NAME, TABLE_NAME, TABLE_OWNER, INDEX_TYPE
FROM all_indexes
WHERE table_name = 'PAWNED_PPTY' ORDER BY INDEX_NAME;

SELECT INDEX_NAME, TABLE_NAME
FROM user_ind_columns
WHERE table_name = 'VEH_POUND_PARK_SPACE_REQ';

--读取create index script
select dbms_metadata.get_ddl( 'INDEX', 'IDX_VEH_POUND_PARK_SPACE_REQ_2' , 'CIS2') from dual;




/*online|offline
改变表空间的状态。online使表空间创建后立即有效.这是缺省值.
offline使表空间创建后无效.这个值，可以从dba_tablespace中得到*/
--create index
CREATE [UNIQUE] INDEX index_name
ON table_name(column_name1,column_name2,column_name3...) online;

--drop index
drop index INDEX_CONCURRENCY;

commit;



