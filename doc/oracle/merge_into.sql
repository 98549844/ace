BEGIN
    --Merge into 用法（存在就更新，不存在就插入）

    --merge table
    MERGE INTO REF_CODE rc
        --merge data
    USING (SELECT 'new status' new_statuc FROM dual) d
        --merge条件
    ON (rc.CODE_TYPE = 'IND_YES_NO' AND rc.CODE_KEY = '0' AND rc.IND_REC_STATUS = d.new_statuc)
        --match to do
    WHEN MATCHED THEN
        UPDATE
        SET rc.CODE_DESCN='No',
            rc.CODE_DESCN_CHN='否',
            rc.SORT_SEQ = 2,
            rc.IND_REC_STATUS = 'A',
            rc.LAST_UPD_DATETIME=sysdate
    WHEN NOT MATCHED THEN
        --dont match to do
        INSERT (rc.CODE_TYPE,
                rc.CODE_KEY,
                rc.CODE_DESCN,
                rc.CODE_DESCN_CHN,
                rc.SORT_SEQ,
                rc.IND_REC_STATUS,
                rc.CREATED_DATETIME,
                rc.LAST_UPD_DATETIME)
        VALUES ('IND_YES_NO', '0', 'No', '否', 2, 'A', sysdate, sysdate);
END;