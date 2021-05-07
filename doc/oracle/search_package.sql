SELECT name, line, text
FROM dba_source
WHERE upper(text) LIKE upper('%FOUND%') ESCAPE '\' order by NAME;






