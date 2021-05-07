DECLARE

    sex        VARCHAR2(10);
    displaySex VARCHAR2(10);

BEGIN

    sex := 1;


    SELECT DECODE(sex, '1', '男', '2', '女', '其他') INTO displaySex FROM dual;
    -- = SELECT CASE sex WHEN '1' THEN '男' WHEN '2' THEN '女' ELSE '其他' END INTO displaySex FROM dual;


    DBMS_OUTPUT.PUT_LINE('FIRST.' || displaySex);

    --Case搜索函数

    SELECT CASE WHEN sex = '1' THEN '男' WHEN sex = '2' THEN '女' ELSE '其他' END INTO displaySex FROM dual;

    DBMS_OUTPUT.PUT_LINE('SECOND.' || displaySex);

END ;
/