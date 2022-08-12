SELECT tname
FROM (SELECT tname
      FROM (SELECT DISTINCT TABLE_NAME as tname
            FROM ALL_TAB_COLUMNS
            WHERE 1 = 1
              AND owner = 'CIS2'
              AND TABLE_NAME LIKE 'REF_%')
      WHERE 1 = 1
        AND tname not LIKE '%_X')
WHERE 1 = 1
  AND tname not LIKE '%_A';


SELECT tname
FROM (SELECT DISTINCT TABLE_NAME as tname
      FROM ALL_TAB_COLUMNS
      WHERE 1 = 1
        AND owner = 'CIS2'
        AND TABLE_NAME LIKE 'REF_%')
WHERE 1 = 1
  AND tname not LIKE '%_X';




SELECT DISTINCT TABLE_NAME as tname
FROM ALL_TAB_COLUMNS
WHERE 1 = 1
  AND owner = 'CIS2'
  AND TABLE_NAME LIKE 'REF_%';


--查询table信息
SELECT T.COLUMN_NAME,
       T.COLUMN_ID,
       T.NULLABLE,
       T.DATA_TYPE || '(' || T.DATA_LENGTH || ')' as DataType,
       T.DATA_DEFAULT as DATA_DEFAULT,
       C.COMMENTS
FROM USER_TAB_COLUMNS T, USER_COL_COMMENTS C
WHERE T.TABLE_NAME = C.TABLE_NAME
  AND T.COLUMN_NAME = C.COLUMN_NAME
  -- AND T.TABLE_NAME = '表名（大写）'
order by t.COLUMN_ID;