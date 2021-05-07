--单一字段
DECLARE TYPE USER_NAME_TYPE IS TABLE OF VARCHAR2(10) INDEX BY BINARY_INTEGER;
    user_name_arr USER_NAME_TYPE;
BEGIN
    user_name_arr(0) := 'Peter';
    user_name_arr(1) := 'Mary';
    user_name_arr(2) := 'Lily';
    user_name_arr(3) := 'Lucy';
    user_name_arr(4) := 'Lee';
    user_name_arr(5) := 'Chris';

    FOR i IN 0 .. 5
        LOOP
            dbms_output.put_line('User Name:' || user_name_arr(i));
        END LOOP;
END;


--row record
DECLARE TYPE USER_INFO_TYPE IS RECORD ( user_name VARCHAR2(20), user_age NUMBER(3), user_sex VARCHAR2(5)); TYPE USER_INFO_ARR_TYPE IS TABLE OF USER_INFO_TYPE INDEX BY BINARY_INTEGER;
    user_info_arr USER_INFO_ARR_TYPE;

BEGIN
    user_info_arr(0).user_name := 'Peter';
    user_info_arr(0).user_age := 19;
    user_info_arr(0).user_sex := 'M';

    user_info_arr(1).user_name := 'Lee';
    user_info_arr(1).user_age := 23;
    user_info_arr(1).user_sex := 'F';

    FOR i IN 0 .. 1
        LOOP
            dbms_output.put_line('User Name:' || user_info_arr(i).user_name);
            dbms_output.put_line('User Age:' || user_info_arr(i).user_age);
            dbms_output.put_line('User Sex:' || user_info_arr(i).user_sex);
            dbms_output.put_line('****************');
        END LOOP;
END;

DECLARE TYPE NUMBER_ARRAY IS VARRAY (5) OF NUMBER;
    displaySeq NUMBER_ARRAY := number_array(10, 50, 60, 90, NULL);
    v_count    NUMBER;
BEGIN
    --displaySeq(4) := 70;
    DBMS_OUTPUT.PUT_LINE(displaySeq(1));
    DBMS_OUTPUT.PUT_LINE(displaySeq(4));
    DBMS_OUTPUT.PUT_LINE(displaySeq(2));
    DBMS_OUTPUT.PUT_LINE('list size:' || displaySeq.LAST);
    DBMS_OUTPUT.PUT_LINE('----------------');

    FOR v_count IN 1..displaySeq.LAST
        LOOP
            dbms_output.put_line(v_count);
            dbms_output.put_line('######' || displaySeq(v_count));
        END LOOP;

END;
/
