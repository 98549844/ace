--1.
SET serveroutput on;
DECLARE
    i   NUMBER(2)    := 10 ;
    s   VARCHAR2(10) := 'value' ;
    ena POLICE_CASE.RN%TYPE ; -- 引用型变量
    enb POLICE_CASE.CODE_POL_FORMATN%TYPE ; -- 引用型变量
BEGIN
    dbms_output.put_line(i);
    dbms_output.put_line(s);
    SELECT RN, CODE_POL_FORMATN INTO ena,enb FROM POLICE_CASE WHERE RN = 'ABDDIV 08000001';
    dbms_output.put_line(ena);
END;



--get 1 row data into variable
DECLARE
    row_POL_CASE POLICE_CASE%ROWTYPE;
BEGIN
    SELECT * INTO row_POL_CASE FROM POLICE_CASE WHERE rownum < 2;
    dbms_output.put_line(row_POL_CASE.POL_CASE_NO);
END;









