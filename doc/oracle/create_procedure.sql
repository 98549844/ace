CREATE OR REPLACE PROCEDURE procedure_name(name IN VARCHAR, age IN INT) AS
BEGIN
    dbms_output.put_line('name=' || name || ', age=' || age);
END;


DECLARE
    name VARCHAR(10);
    age  INT;
BEGIN
    name := 'Garlam';
    age := 1986;
    procedure_name(name=>name, age=>18);
END;

DROP PROCEDURE procedure_name;