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