copy table form table
	insert into dbName.tableNameDesc (parentId,name,active) select parentId,name,active from dbName.tableNameSrc

col更新:values的算计运算
	update dbName.tableName set colName = colName-2

根据id删除数据
1.	delete from dbName.tableName where id = '10'

counting
1.	select colName,count(l.id) as dispName from tableName as l group by l.colName

2.	select l.partsId,count(l.id) as dispName,
	(select count(la.id) from tableName as la where la.partsId = l.partsId and dateCol >'2017-10-30' and dateCol<='2017-10-30') as dispName1,
	(select count(lb.id) from tableName as lb where lb.partsId = l.partsId and dateCol >'2017-10-18' and dateCol<='2017-10-30') as dispName2
	from tableName as l group by l.partsId

根据条件查数据
	查询t2.id在t.colId找不到
1.	select * from tableName t where t.colId not in (select t2.id from tableName t2)
	查询t2.id在t.colId找不到
2.	select * from tableName t where (select count(*) from tableName t2 where t.id = t2.colId) = 0 (false)
	查询t2.id在t.colId找到
3.	select * from tableName t where (select count(*) from tableName t2 where t.id = t2.colId) = 1 (true)


Table编辑
新增col
ALTER TABLE tableName ADD columnName dataType

删除col
ALTER TABLE tableName DROP COLUMN columnName

插入数据
INSERT INTO tableName (colName1 ,colName2, colName3, colName4, colName5) VALUES(3,'元朗','Y','元朗','Yuen Long')