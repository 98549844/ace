DECLARE


    condition  VARCHAR2(10) ;
    conditionA VARCHAR2(10) := 'A';
    conditionB VARCHAR2(10) := 'B';
    conditionC VARCHAR2(10) := 'C';
    conditionD VARCHAR2(10) := 'D';
    conditionE VARCHAR2(10) := 'E';
    conditionF VARCHAR2(10) := 'F';

BEGIN
    condition := 'B';

    --type 1
    DBMS_OUTPUT.PUT_LINE('type 1 ********************');
    IF condition = 'A' THEN DBMS_OUTPUT.PUT_LINE(conditionA); END IF;

    --type 2
    DBMS_OUTPUT.PUT_LINE('type 2 ********************');
    IF condition = 'B' THEN DBMS_OUTPUT.PUT_LINE(conditionB); ELSE DBMS_OUTPUT.PUT_LINE('Other Condition'); END IF;

    --type 3
    DBMS_OUTPUT.PUT_LINE('type 3 ********************');
    IF condition = 'C' THEN
        DBMS_OUTPUT.PUT_LINE(conditionC);

    ELSIF condition = 'D' THEN
        DBMS_OUTPUT.PUT_LINE(conditionD);

    END IF;

    --type 4
    DBMS_OUTPUT.PUT_LINE('type 4 ********************');
    IF condition = 'E' THEN
        DBMS_OUTPUT.PUT_LINE(conditionE);

    ELSIF condition = 'F' THEN
        DBMS_OUTPUT.PUT_LINE(conditionF);

    ELSE
        DBMS_OUTPUT.PUT_LINE('Other Condition');

    END IF;

--varchar 判空处理
    declare
        -- Local variables here
        i varchar2(16);
    begin
        -- Test statements here
        i:='';
        dbms_output.put_line('i的值是:'|| i ||'.');
        if(i='') then
            dbms_output.put_line('i 是空字符串');
        end if;
        if(i is not null) then
            dbms_output.put_line('i不为null');
        end if;

        if(i is  null) then
            dbms_output.put_line('i为null');
        end if;
    end;

END;