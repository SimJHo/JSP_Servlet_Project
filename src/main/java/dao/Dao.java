package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.CandidateDto;
import dto.MemberDto;
import dto.RspDto;
import dto.SalesDto;
import dto.StudentDto;
import dto.SumDto;
import dto.TeacherDto;
import dto.VotecheckDto;
import dto.VotecountDto;

public class Dao {
	
	private DataSource dataSource = null;
	
	public Dao() {
		
		try {
			Context context = new InitialContext();
			
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	// 투표
	public List<CandidateDto> candidate() {
		
		List<CandidateDto> candi = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT M_NO, "
					+ "M_NAME, "
					+ "P_NAME, "
					+ "CASE P_SCHOOL WHEN '1' THEN '고졸' WHEN '2' THEN '학사' WHEN '3' THEN '석사' WHEN '4' THEN '박사' END AS P_SCHOOL, "
					+ "(SUBSTR(M_JUMIN, 1,6)||'-'||SUBSTR(M_JUMIN, 7, 13)) AS M_JUMIN, "
					+ "M_CITY, "
					+ "P_TEL1, "
					+ "P_TEL2, "
					+ "P_TEL3 "
					+ "FROM TBL_MEMBER M, TBL_PARTY P WHERE M.P_CODE = P.P_CODE";
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String m_no = rs.getString("m_no");
				String m_name = rs.getString("m_name");
				String p_name = rs.getString("p_name");
				String p_school = rs.getString("p_school");
				String m_jumin = rs.getString("m_jumin");
				String m_city = rs.getString("m_city");
				String p_tel1 = rs.getString("p_tel1");
				String p_tel2 = rs.getString("p_tel2");
				String p_tel3 = rs.getString("p_tel3");
				
				CandidateDto dto = new CandidateDto(m_no, m_name, p_name, p_school,
						m_jumin, m_city, p_tel1, p_tel2, p_tel3);
				
				candi.add(dto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return candi;
		
	}
	
	public void vote(String v_jumin, String v_name, String m_no, String v_time, String v_area, String v_confirm){
		System.out.println("vote()..");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			String query = "INSERT INTO TBL_VOTE "
		               + "(V_JUMIN, V_NAME, M_NO, V_TIME, V_AREA, V_CONFIRM) "
		               + "VALUES (?, ?, ?, ?, ?, ?)";
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, v_jumin);
			preparedStatement.setString(2, v_name);
			preparedStatement.setString(3, m_no);
			preparedStatement.setString(4, v_time);
			preparedStatement.setString(5, v_area);
			preparedStatement.setString(6, v_confirm);
			
			int rn = preparedStatement.executeUpdate();
			System.out.println("업데이트 개수 : " + rn);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
	
	public List<VotecheckDto> voteCheck() {
		
		List<VotecheckDto> check = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT V_NAME, "
					+ "(19||SUBSTR(V_JUMIN, 1,2)||'년'||SUBSTR(V_JUMIN, 3, 2)||'월'||SUBSTR(V_JUMIN, 5, 2)||'일생')AS V_JUMIN, "
					+ "'만 '||TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE),"
					+ " TO_DATE(19||(SUBSTR(V_JUMIN,1,6)),'YYYYMMDD')) / 12)||'세' AS V_AGE, "
					+ "CASE SUBSTR(V_JUMIN,7,1) WHEN '1' THEN '남' WHEN '2' THEN '여' END AS V_GENDER, "
					+ "M_NO, "
					+ "V_TIME, "
					+ "CASE V_CONFIRM WHEN 'Y' THEN '확인' WHEN 'N' THEN '미확인' END AS V_CONFIRM "
					+ "from tbl_vote";
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String v_name = rs.getString("v_name");
				String v_jumin = rs.getString("v_jumin");
				String v_age = rs.getString("v_age");
				String v_gender = rs.getString("v_gender");
				String m_no = rs.getString("m_no");
				String v_time = rs.getString("v_time");
				String v_confirm = rs.getString("v_confirm");
				
				VotecheckDto dto = new VotecheckDto(v_name, v_jumin, v_age, v_gender,
						m_no, v_time, v_confirm);
				
				check.add(dto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return check;
		
	}

	public List<VotecountDto> voteCount() {
		
		List<VotecountDto> count = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT  M.M_NO, "
					+ "M.M_NAME, "
					+ "COUNT(*) AS M_CNT FROM TBL_MEMBER M, TBL_VOTE V "
					+ "WHERE M.M_NO = V.M_NO AND NOT V.V_CONFIRM = 'N' "
					+ "GROUP BY M.M_NAME, M.M_NO, V.M_NO "
					+ "ORDER BY M_CNT DESC";
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String m_no = rs.getString("m_no");
				String m_name = rs.getString("m_name");
				String m_cnt = rs.getString("m_cnt");
				
				VotecountDto dto = new VotecountDto(m_no, m_name, m_cnt);
				
				count.add(dto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return count;
		
	}
	
	// 쇼핑
	
	public List<MemberDto> list4() {

        System.out.println("list4() ..");

        List<MemberDto> dtoList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT CUSTNO, CUSTNAME, PHONE, ADDRESS, JOINDATE, CITY," +
                    "CASE GRADE " +
                    "WHEN 'A' THEN 'VIP'" +
                    "WHEN 'B' THEN '일반'" +
                    "WHEN 'C' THEN '직원'" +
                    "END AS GRADE FROM MEMBER_TBL_02 ORDER BY CUSTNO ASC";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int custNo = resultSet.getInt("custNo");
                String custName = resultSet.getString("custName");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                Date joinDate = resultSet.getDate("joinDate");
                String city = resultSet.getString("city");
                String grade = resultSet.getString("grade");

                MemberDto memberDto = new MemberDto(custNo, custName, phone, address, joinDate, grade, city);
                dtoList.add(memberDto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return dtoList;
    }

    public String max_custNo() {

        System.out.println("max_custNo() ..");

        String custNo = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT MAX(CUSTNO) + 1 AS CUSTNO FROM MEMBER_TBL_02";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) custNo = resultSet.getString("custNo");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return custNo;
    }

    public void insert(String custNo, String custName, String phone,
                       String address, String joinDate, String grade, String city) {

        System.out.println("insert() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = "INSERT INTO member_tbl_02 (CUSTNO, CUSTNAME, PHONE, ADDRESS, JOINDATE, GRADE, CITY) " +
                    "VALUES (?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(custNo));
            preparedStatement.setString(2,custName);
            preparedStatement.setString(3,phone);
            preparedStatement.setString(4,address);
            preparedStatement.setString(5,joinDate);
            preparedStatement.setString(6,grade);
            preparedStatement.setString(7,city);

            int insert = preparedStatement.executeUpdate();
            System.out.println("업데이트 갯수 : " + insert);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<SalesDto> sales() {

        System.out.println("sales() ..");

        List<SalesDto> dtoList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT DISTINCT MEMBER_TBL_02.CUSTNO, MEMBER_TBL_02.CUSTNAME, CASE GRADE\n" +
                    "    WHEN 'A' THEN 'VIP'\n" +
                    "    WHEN 'B' THEN '일반'\n" +
                    "    WHEN 'C' THEN '직원'\n" +
                    "    END AS GRADE, PRICE.SALES\n" +
                    "FROM MEMBER_TBL_02,MONEY_TBL_02 ,(SELECT CUSTNO,SUM(PRICE) AS SALES FROM MONEY_TBL_02 GROUP BY CUSTNO) PRICE\n" +
                    "WHERE MEMBER_TBL_02.CUSTNO = MONEY_TBL_02.CUSTNO AND MEMBER_TBL_02.CUSTNO = PRICE.CUSTNO " +
                    "ORDER BY PRICE.SALES DESC";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int custNo = resultSet.getInt("custNo");
                String custName = resultSet.getString("custName");
                String grade = resultSet.getString("grade");
                int sales = resultSet.getInt("sales");

                SalesDto salesDto = new SalesDto(custNo, custName, grade, sales);
                dtoList.add(salesDto);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return dtoList;
    }

    public MemberDto content(String custNo) {

        MemberDto dto = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String query = "SELECT * FROM MEMBER_TBL_02 WHERE CUSTNO = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(custNo));
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int custNomber = resultSet.getInt("custNo");
                String custName = resultSet.getString("custName");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                Date joinDate = resultSet.getDate("joinDate");
                String city = resultSet.getString("city");
                String grade = resultSet.getString("grade");

                dto = new MemberDto(custNomber, custName, phone, address, joinDate, grade, city);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dto;
    }

    public void create(String custNo, String custName, String phone,
                       String address, String joinDate, String grade, String city) {

        System.out.println("create() ..");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            String query = "UPDATE MEMBER_TBL_02 " +
                           "SET CUSTNAME = ?, PHONE = ?, ADDRESS = ?, JOINDATE = ?, GRADE = ?, CITY = ? " +
                           "WHERE CUSTNO = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,custName);
            preparedStatement.setString(2,phone);
            preparedStatement.setString(3,address);
            preparedStatement.setString(4,joinDate);
            preparedStatement.setString(5,grade);
            preparedStatement.setString(6,city);
            preparedStatement.setInt(7, Integer.parseInt(custNo));

            int update = preparedStatement.executeUpdate();
            System.out.println("업데이트 갯수 : " + update);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) connection.close();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
	
	
	// 골프
	
	public List<TeacherDto> list(){
		ArrayList<TeacherDto> dtos = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String query= "SELECT TEACHER_CODE, "
					+ "TEACHER_NAME, "
					+ "CLASS_NAME, "
					+ "TO_CHAR(CLASS_PRICE, 'L999,999') AS CLASS_PRICE, "
					+ "SUBSTR(TEACHER_REGIST_DATE, 1, 4)||'년'||SUBSTR(TEACHER_REGIST_DATE, 5, 2)||'월'||SUBSTR(TEACHER_REGIST_DATE, 7, 2)||'일' AS TEACHER_REGIST_DATE "
					+ "FROM TBL_TEACHER_202201";
		           
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				String teacher_code = rs.getString("teacher_code");
				String teacher_name = rs.getString("teacher_name");
				String class_name = rs.getString("class_name");
				String class_price = rs.getString("class_price");
				String teacher_regist_date = rs.getString("teacher_regist_date");
				
				TeacherDto dto = new TeacherDto(teacher_code, teacher_name, class_name,
								  class_price, teacher_regist_date);

				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {

				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}	
		return dtos;
	}
	
	public List<StudentDto> list2(){
		ArrayList<StudentDto> dtos = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String query= "SELECT SUBSTR(REGIST_MONTH, 1, 4)||'년'||SUBSTR(REGIST_MONTH, 5, 2)||'월' AS REGIST_MONTH, "
					+ "C.C_NO, "
					+ "M.C_NAME, "
					+ "CASE C.TEACHER_CODE WHEN '100' THEN '초급반' WHEN '200' THEN '중급반' WHEN '300' THEN '고급반' WHEN '400' THEN '심화반' END AS TEACHER_CODE, "
					+ "C.CLASS_AREA, "
					+ "TO_CHAR(C.TUITION, 'L999,999') AS TUITION, "
					+ "M.GRADE "
					+ "FROM TBL_CLASS_202201 C, TBL_MEMBER_202201 M WHERE C.C_NO = M.C_NO ";
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				String regist_month = rs.getString("regist_month");
				int c_no = rs.getInt("c_no");
				String c_name = rs.getString("c_name");
				String teacher_code = rs.getString("teacher_code");
				String class_area = rs.getString("class_area");
				String tuition = rs.getString("tuition");
				String grade = rs.getString("grade");
				
				
				StudentDto dto = new StudentDto(regist_month, c_no, c_name,
												teacher_code, class_area, tuition, grade);

				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {

				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}	
		return dtos;
	}
	
	public void regist(String regist_month, String c_no,
					   String class_area, String class_name, String tuition){
		System.out.println("regist() ..");

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		System.out.println(class_name);
		//이때 ?,?,? 물음표는 아래에서 setString메소드로 넣어줄 값을 표현하는 것	

		try {
			/*
	           파라미터로 받는 값이 bname, btitle, bcontent 3개므로 3개만 ?로 넣고 나머지는 0으로
	           설정했다. 글 작성시 조회수(bhit)는 0이고, 원본 글로 취급하므로 bstep, bindent는
	           지수를 넣어줄 필요가 없기 때문이다.
			 */
			String query = "INSERT INTO TBL_CLASS_202201 "
					+ "(REGIST_MONTH, C_NO, CLASS_AREA, TUITION, TEACHER_CODE)"
					+ "VALUES (?,?,?,?,?)";

			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, regist_month);
			preparedStatement.setString(2, c_no);
			preparedStatement.setString(3, class_area);
			preparedStatement.setString(4, tuition);
			preparedStatement.setString(5, class_name);

			int rn = preparedStatement.executeUpdate();
			System.out.println("업데이트 갯수 :"+rn);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {			

				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public List<SumDto> list3(){
		ArrayList<SumDto> dtos = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String query= "SELECT T.TEACHER_CODE, "
					+ "CLASS_NAME, "
					+ "TEACHER_NAME, "
					+ "TO_CHAR(TUITION , 'L999,999') AS TUITION "
					+ "FROM TBL_TEACHER_202201 T, (SELECT TEACHER_CODE, SUM(TUITION) AS TUITION FROM TBL_CLASS_202201 GROUP BY TEACHER_CODE) C "
					+ "WHERE T.TEACHER_CODE = C.TEACHER_CODE";
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				String teacher_code = rs.getString("teacher_code");
				String class_name = rs.getString("class_name");
				String teacher_name = rs.getString("teacher_name");
				String tuition = rs.getString("tuition");
				
				
				SumDto dto = new SumDto(teacher_code, class_name, teacher_name, tuition);

				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {

				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}	
		return dtos;
	}
	
	// 가위바위보
	public List<RspDto> rsp(String rspInput) {
		
		List<RspDto> rspResult = new ArrayList<>();
		String you = rspInput;
		String com = String.valueOf((int) (Math.random() * 3 + 1));
		String result = null;
		
		if(you.equals("1") && com.equals("1")) {
			you = "scissors.png";
			com = "scissors.png";
			result = "비겼습니다.";
		}else if(you.equals("1") && com.equals("2")) {
			you = "scissors.png";
			com = "rock.png";
			result = "졌습니다.";
		}else if(you.equals("1") && com.equals("3")) {
			you = "scissors.png";
			com = "paper.png";
			result = "이겼습니다.";
		}else if(you.equals("2") && com.equals("1")) {
			you = "rock.png";
			com = "scissors.png";
			result = "이겼습니다.";
		}else if(you.equals("2") && com.equals("2")) {
			you = "rock.png";
			com = "rock.png";
			result = "비겼습니다.";
		}else if(you.equals("2") && com.equals("3")) {
			you = "rock.png";
			com = "paper.png";
			result = "졌습니다.";
		}else if(you.equals("3") && com.equals("1")) {
			you = "paper.png";
			com = "scissors.png";
			result = "졌습니다.";
		}else if(you.equals("3") && com.equals("2")) {
			you = "paper.png";
			com = "rock.png";
			result = "이겼습니다.";
		}else{
			you = "paper.png";
			com = "paper.png";
			result = "비겼습니다.";
		}
		
		System.out.println(you + com + result);
		RspDto dto = new RspDto(you, com, result);
		
		rspResult.add(dto);
		
		return rspResult;
	}
	
	

}
