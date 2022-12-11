package dao;

public class SQL {
	protected String candidate = "SELECT M_NO, "
			+ "M_NAME, "
			+ "P_NAME, "
			+ "DECODE(P_SCHOOL, '1', '고졸', '2', '학사', '3', '석사', '4', '박사') AS P_SCHOOL, "
			+ "(SUBSTR(M_JUMIN, 1,6)||'-'||SUBSTR(M_JUMIN, 7, 13)) AS M_JUMIN, "
			+ "M_CITY, "
			+ "P_TEL1, "
			+ "P_TEL2, "
			+ "P_TEL3 "
			+ "FROM TBL_MEMBER M, TBL_PARTY P WHERE M.P_CODE = P.P_CODE";
	
	protected String vote = "INSERT INTO TBL_VOTE "
            + "(V_JUMIN, V_NAME, M_NO, V_TIME, V_AREA, V_CONFIRM) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
	
	protected String voteCheck = "SELECT V_NAME, "
			+ "(19||SUBSTR(V_JUMIN, 1,2)||'년'||SUBSTR(V_JUMIN, 3, 2)||'월'||SUBSTR(V_JUMIN, 5, 2)||'일생')AS V_JUMIN, "
			+ "'만 '||TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(19||(SUBSTR(V_JUMIN,1,6)),'YYYYMMDD')) / 12)||'세' AS V_AGE, "
			+ "DECODE(SUBSTR(V_JUMIN,7,1), '1', '남', '2', '여') AS V_GENDER, "
			+ "M_NO, "
			+ "V_TIME, "
			+ "DECODE(V_CONFIRM, 'Y', '확인', 'N', '미확인') AS V_CONFIRM "
			+ "FROM TBL_VOTE";
	
	protected String voteCount = "SELECT  M.M_NO, "
			+ "M.M_NAME, "
			+ "COUNT(*) AS M_CNT FROM TBL_MEMBER M, TBL_VOTE V "
			+ "WHERE M.M_NO = V.M_NO AND NOT V.V_CONFIRM = 'N' "
			+ "GROUP BY M.M_NAME, M.M_NO, V.M_NO "
			+ "ORDER BY M_CNT DESC";
	
	protected String list4 = "SELECT CUSTNO, CUSTNAME, PHONE, ADDRESS, JOINDATE, CITY," +
            "CASE GRADE " +
            "WHEN 'A' THEN 'VIP'" +
            "WHEN 'B' THEN '일반'" +
            "WHEN 'C' THEN '직원'" +
            "END AS GRADE FROM MEMBER_TBL_02 ORDER BY CUSTNO ASC";
	
	protected String max_custNo = "SELECT MAX(CUSTNO) + 1 AS CUSTNO FROM MEMBER_TBL_02";
	
	protected String insert = "INSERT INTO member_tbl_02 (CUSTNO, CUSTNAME, PHONE, ADDRESS, JOINDATE, GRADE, CITY) " +
            "VALUES (?,?,?,?,?,?,?)";
	
	protected String sales = "SELECT DISTINCT MEMBER_TBL_02.CUSTNO, MEMBER_TBL_02.CUSTNAME, CASE GRADE\n" +
            "    WHEN 'A' THEN 'VIP'\n" +
            "    WHEN 'B' THEN '일반'\n" +
            "    WHEN 'C' THEN '직원'\n" +
            "    END AS GRADE, PRICE.SALES\n" +
            "FROM MEMBER_TBL_02,MONEY_TBL_02 ,(SELECT CUSTNO,SUM(PRICE) AS SALES FROM MONEY_TBL_02 GROUP BY CUSTNO) PRICE\n" +
            "WHERE MEMBER_TBL_02.CUSTNO = MONEY_TBL_02.CUSTNO AND MEMBER_TBL_02.CUSTNO = PRICE.CUSTNO " +
            "ORDER BY PRICE.SALES DESC";
	
	protected String content = "SELECT * FROM MEMBER_TBL_02 WHERE CUSTNO = ?";
	
	protected String create = "UPDATE MEMBER_TBL_02 " +
            "SET CUSTNAME = ?, PHONE = ?, ADDRESS = ?, JOINDATE = ?, GRADE = ?, CITY = ? " +
            "WHERE CUSTNO = ?";
	
	protected String list = "SELECT TEACHER_CODE, "
			+ "TEACHER_NAME, "
			+ "CLASS_NAME, "
			+ "TO_CHAR(CLASS_PRICE, 'L999,999') AS CLASS_PRICE, "
			+ "SUBSTR(TEACHER_REGIST_DATE, 1, 4)||'년'||SUBSTR(TEACHER_REGIST_DATE, 5, 2)||'월'"
			+ "||SUBSTR(TEACHER_REGIST_DATE, 7, 2)||'일' AS TEACHER_REGIST_DATE "
			+ "FROM TBL_TEACHER_202201";
	
	protected String list2 = "SELECT SUBSTR(REGIST_MONTH, 1, 4)||'년'||SUBSTR(REGIST_MONTH, 5, 2)||'월' AS REGIST_MONTH, "
			+ "C.C_NO, "
			+ "M.C_NAME, "
			+ "CASE C.TEACHER_CODE WHEN '100' THEN '초급반' WHEN '200' THEN '중급반' WHEN '300' THEN '고급반' "
			+ "WHEN '400' THEN '심화반' END AS TEACHER_CODE, "
			+ "C.CLASS_AREA, "
			+ "TO_CHAR(C.TUITION, 'L999,999') AS TUITION, "
			+ "M.GRADE "
			+ "FROM TBL_CLASS_202201 C, TBL_MEMBER_202201 M WHERE C.C_NO = M.C_NO ";
	
	protected String regist = "INSERT INTO TBL_CLASS_202201 "
			+ "(REGIST_MONTH, C_NO, CLASS_AREA, TUITION, TEACHER_CODE)"
			+ "VALUES (?,?,?,?,?)";
	
	protected String list3 = "SELECT T.TEACHER_CODE, "
			+ "CLASS_NAME, "
			+ "TEACHER_NAME, "
			+ "TO_CHAR(TUITION , 'L999,999') AS TUITION "
			+ "FROM TBL_TEACHER_202201 T, (SELECT TEACHER_CODE, SUM(TUITION) AS TUITION "
			+ "FROM TBL_CLASS_202201 GROUP BY TEACHER_CODE) C "
			+ "WHERE T.TEACHER_CODE = C.TEACHER_CODE";
}
