DECLARE
    r_police_case police_case%ROWTYPE;

BEGIN

    EXECUTE IMMEDIATE 'SELECT *
                       FROM POLICE_CASE pc
                       WHERE 1=1
                         AND pc.POL_CASE_ID = 1000027587' INTO r_police_case;
    DBMS_OUTPUT.PUT_LINE(r_police_case.RN);
END;

select lower('REGEXP_SUBSTR') from dual;