DECLARE
    p_RN       POLICE_CASE.RN%TYPE := 'MKDIST 21000005';
    polCase_id POLICE_CASE.POL_CASE_ID%TYPE ;
BEGIN
    SELECT pc.POL_CASE_ID INTO polCase_id FROM POLICE_CASE pc WHERE pc.RN = p_RN;
    IF SQL%FOUND THEN DBMS_OUTPUT.PUT_LINE('polCase_id:' || polCase_id); END IF;
    --EXCEPTION
    --  WHEN no_data_found THEN DBMS_OUTPUT.PUT_LINE('polCase_id:' || polCase_id || 'not found');

    polCase_id := 2000027753;
    SELECT RN INTO p_RN FROM POLICE_CASE WHERE POL_CASE_ID = polCase_id;
    IF SQL%FOUND THEN DBMS_OUTPUT.PUT_LINE('RESULT_RN: ' || p_RN); END IF;

EXCEPTION
    WHEN no_data_found THEN DBMS_OUTPUT.PUT_LINE('polCase_id:' || polCase_id || ' not found');
    DBMS_OUTPUT.PUT_LINE('RN:' || p_RN || ' not found');

END;



DECLARE
    p_RN       POLICE_CASE.RN%TYPE := 'MKDIST 21000015';
    polCase_id POLICE_CASE.POL_CASE_ID%TYPE ;
BEGIN
    SELECT pc.POL_CASE_ID INTO polCase_id FROM POLICE_CASE pc WHERE pc.RN = p_RN;
    DBMS_OUTPUT.PUT_LINE('--- polCase_id:' || polCase_id);
EXCEPTION
    WHEN no_data_found THEN DBMS_OUTPUT.PUT_LINE('polCase_id:' || polCase_id || 'not found');

END;