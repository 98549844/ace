DECLARE
    input_rn        POLICE_CASE.RN%TYPE := 'MKDIST 210000121';
    row_police_case POLICE_CASE%ROWTYPE;
BEGIN
    BEGIN
        SELECT * INTO row_police_case FROM POLICE_CASE WHERE 1 = 1 AND RN = input_rn;
        DBMS_OUTPUT.PUT_LINE('RN:  ' || row_police_case.rn);
        DBMS_OUTPUT.PUT_LINE('police_case_id:  ' || row_police_case.POL_CASE_ID);
    EXCEPTION
        WHEN no_data_found THEN DBMS_OUTPUT.PUT_LINE('^^^ERROR  ' || input_rn || ' not found, data not exist');
        WHEN Too_many_rows THEN DBMS_OUTPUT.PUT_LINE('^^^ERROR  ' || input_rn || ' return too many rows');
    END;
END;/