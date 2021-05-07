--create function
CREATE OR REPLACE FUNCTION log_event(p_message IN VARCHAR2(1000)) RETURN NUMBER IS
    i_status NUMBER;
BEGIN
    INSERT INTO SYS_EVENT_LOG(log_id, log_date, script_name, message)
    VALUES (SEQ_SYS_EVENT_LOG.NEXTVAL, SYSDATE, 'mock disposal 2000 items', '*****  ' || p_message);
    i_status := 0;
    RETURN (i_status);
END log_event;