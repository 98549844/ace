SELECT listagg(str, ' ') WITHIN GROUP ( ORDER BY str)
FROM (SELECT '''' || trim(regexp_substr(str, '[^,]+', 1, LEVEL)) || '''' str
      FROM (SELECT '97, 98, 99, 00, 01' str FROM dual)
      CONNECT BY instr(str, ',', 1, LEVEL - 1) > 0);