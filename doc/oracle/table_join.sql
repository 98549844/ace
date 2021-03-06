--left join
SELECT pd.PPTY_DTL_ID, pd.POL_CASE_ID, pc.POL_CASE_ID
FROM PPTY_DTL pd,
     POLICE_CASE pc
WHERE pd.POL_CASE_ID = pc.POL_CASE_ID (+);

--right join
SELECT pd.PPTY_DTL_ID, pd.POL_CASE_ID, pc.POL_CASE_ID
FROM PPTY_DTL pd,
     POLICE_CASE pc
WHERE pd.POL_CASE_ID (+) = pc.POL_CASE_ID;