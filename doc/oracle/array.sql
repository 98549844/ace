DECLARE
    TYPE NUMBER_ARRAY IS VARRAY (5) OF NUMBER;
    displaySeq NUMBER_ARRAY := number_array(10, 50, 60, 90, NULL);
    v_count    NUMBER;
BEGIN
    displaySeq(4) := 70;
    DBMS_OUTPUT.PUT_LINE(displaySeq(1));
    DBMS_OUTPUT.PUT_LINE(displaySeq(4));
    DBMS_OUTPUT.PUT_LINE(displaySeq(2));
    DBMS_OUTPUT.PUT_LINE(displaySeq.LAST);
    DBMS_OUTPUT.PUT_LINE('----------------');

    FOR v_count IN 1..displaySeq.LAST
        LOOP
            dbms_output.put_line(v_count);
            dbms_output.put_line(displaySeq(v_count));
        END LOOP;

END;
/


DECLARE TYPE NUMBER_ARRAY IS VARRAY (1) OF NUMBER;
    displaySeq NUMBER_ARRAY := number_array(11);
    v_count    NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE(displaySeq.LAST);

END;
/
