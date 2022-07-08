SET serveroutput on;

--while循环（条件成立时执行）
DECLARE
    pnum NUMBER := 1;
BEGIN
    WHILE PNUM <= 10
        LOOP
            dbms_output.PUT_LINE(pnum); pnum := pnum + 1;
        END LOOP;
END;

--loop循环（条件成立时退出）
DECLARE
    pnum NUMBER := 1;
BEGIN
    LOOP
        --退出条件：循环变量大于10
        EXIT WHEN pnum > 10;
        --打印该变量的值
        DBMS_OUTPUT.PUT_LINE(pnum);
        --循环变量+1
        pnum := pnum + 1;
    END LOOP;
END;

--for循环（1..10表示连续区间）
--exit 完全跳出当前循环
--CONTINUE 跳出当前循环
DECLARE
    punm NUMBER ;
BEGIN

    FOR pnum IN 1..1
        LOOP
            dbms_output.put_line(pnum);
        END LOOP;

    FOR pnum IN 1..10
        LOOP
            dbms_output.put_line(pnum);
            IF pnum = 5 THEN DBMS_OUTPUT.PUT_LINE(pnum || '   ****'); CONTINUE ; END IF;
            dbms_output.put_line('****');
        END LOOP;

    FOR pnum IN 1..10
        LOOP
            dbms_output.put_line(pnum);
            IF pnum = 5 THEN DBMS_OUTPUT.PUT_LINE(pnum || '   ****'); EXIT ; END IF;
            dbms_output.put_line('****');
        END LOOP;
END;


--fetch loop data
DECLARE
    CURSOR v_pc (dataRow NUMBER) IS SELECT ROWNUM, POL_CASE_ID, RN, CODE_POL_FORMATN, POL_CASE_NO
                                    FROM POLICE_CASE pc
                                    WHERE rownum < dataRow;
    num                NUMBER;
    V_POL_CASE_ID      POLICE_CASE.POL_CASE_ID%TYPE ;
    V_RN               POLICE_CASE.RN%TYPE;
    V_CODE_POL_FORMATN POLICE_CASE.CODE_POL_FORMATN%TYPE ;
    V_POL_CASE_NO      POLICE_CASE.POL_CASE_NO%TYPE;

BEGIN
    OPEN v_pc(20);
    LOOP
        FETCH v_pc INTO num,V_POL_CASE_ID,V_RN,V_CODE_POL_FORMATN,V_POL_CASE_NO;
        EXIT WHEN v_pc%NOTFOUND;
        dbms_output.put_line('line: ' || num || ' : POL_CASE_ID : ' || V_POL_CASE_ID);
        dbms_output.put_line(num || ' : *** POL_CASE_ID : ' || V_POL_CASE_ID);
        dbms_output.put_line('----------------------------------');
    END LOOP;
    CLOSE v_pc;
END;

DECLARE
accGrpId VARCHAR2(100);
CURSOR grpId (userName VARCHAR2 ) IS SELECT UAC_ACC_GRP_ID
                                     FROM R_UAC_USER_ACC_GRP
                                     WHERE UAC_USER_ID = userName;
BEGIN
OPEN grpId('BILLY');
LOOP
FETCH grpId INTO accGrpId;
        EXIT WHEN grpId%NOTFOUND;
        dbms_output.put_line(accGrpId);

INSERT INTO R_UAC_USER_ACC_GRP (UAC_USER_ID, UAC_ACC_GRP_ID, UAC_CREATE_DT, UAC_CREATE_USER)
VALUES ('KALAM', accGrpId, SYSDATE, 'KALAM');

END LOOP;
CLOSE grpId;
COMMIT;
END;