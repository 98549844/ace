SELECT 'exec dbms_output.enable(10000);'
FROM dual
UNION ALL
SELECT 'exec dbms_output.enable(null);'
FROM dual
UNION ALL
SELECT 'set serveroutput off;'
FROM dual
UNION ALL
SELECT 'EXEC SYS.UTL_RECOMP.recomp_serial(''CIS2'');'
FROM dual
UNION ALL
SELECT 'CREATE OR REPLACE SYNONYM ' || ass.owner || '.' || ao.object_name || ' FOR ' || ass.table_owner || '.' ||
       ao.object_name || ';'
FROM all_synonyms ass
         JOIN all_objects ao ON ass.owner = ao.owner AND ass.synonym_name = ao.object_name
WHERE ass.owner LIKE ass.table_owner || '\_%' ESCAPE '\' AND ass.table_owner = 'CIS2' AND ao.status <> 'VALID'
UNION ALL
SELECT 'EXEC CIS2_DBS.PKG_DEPLOY.pr_create_private_synonym_all;'
FROM dual
UNION ALL
SELECT 'EXEC CIS2_DBS.PKG_DEPLOY.PR_GRANT_RIGHT_all;'
FROM dual;
--union all
--select 'exec SYS.PKG_SYS.SYNC_ARCHIVELOG;' from dual;





