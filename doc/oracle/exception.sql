DECLARE
    input_rn        POLICE_CASE.RN%TYPE := 'RN_XXX';
    row_police_case POLICE_CASE%ROWTYPE;
    row_ppty_dtl    PPTY_DTL%ROWTYPE;

BEGIN
    BEGIN
        SELECT * INTO row_police_case FROM POLICE_CASE pc WHERE pc.rn = input_rn;

    EXCEPTION
        WHEN no_data_found THEN DBMS_OUTPUT.PUT_LINE(
                    '^^^ERROR  ' || input_rn || ' not found, data not exist, Please RN or DB site');RETURN;
        WHEN Too_many_rows THEN DBMS_OUTPUT.PUT_LINE('^^^ERROR  ' || input_rn || ' return too many rows');RETURN;
    END;

END;