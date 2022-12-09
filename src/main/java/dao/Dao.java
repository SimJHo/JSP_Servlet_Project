package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.CandidateDto;
import dto.RspDto;
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
			String query = "select m_no, "
					+ "m_name, "
					+ "p_name, "
					+ "case p_school when '1' then '고졸' when '2' then '학사' when '3' then '석사' when '4' then '박사' end as p_school, "
					+ "(substr(m_jumin, 1,6)||'-'||substr(m_jumin, 7, 13)) as m_jumin, "
					+ "m_city, "
					+ "p_tel1, "
					+ "p_tel2, "
					+ "p_tel3 "
					+ "from tbl_member m, tbl_party p where m.p_code = p.p_code";
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
			String query = "insert into tbl_vote "
		               + "(v_jumin, v_name, m_no, v_time, v_area, v_confirm) "
		               + "values (?, ?, ?, ?, ?, ?)";
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
			String query = "select v_name, "
					+ "(19||substr(v_jumin, 1,2)||'년'||substr(v_jumin, 3, 2)||'월'||substr(v_jumin, 5, 2)||'일생')as v_jumin, "
					+ "'만 '||TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE),"
					+ " TO_DATE(19||(substr(v_jumin,1,6)),'YYYYMMDD')) / 12)||'세' as v_age, "
					+ "case substr(v_jumin,7,1) when '1' then '남' when '2' then '여' end as v_gender, "
					+ "m_no, "
					+ "v_time, "
					+ "case v_confirm when 'Y' then '확인' when 'N' then '미확인' end as v_confirm "
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
			String query = "select  m.m_no, "
					+ "m.m_name, "
					+ "count(*) as m_cnt from tbl_member m, tbl_vote v "
					+ "where m.m_no = v.m_no and not v.v_confirm = 'N' "
					+ "group by m.m_name, m.m_no, v.m_no "
					+ "order by m_cnt desc";
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
	
	// 골프
	
	public List<TeacherDto> list(){
		ArrayList<TeacherDto> dtos = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String query= "select teacher_code, "
					+ "teacher_name, "
					+ "class_name, "
					+ "TO_CHAR(CLASS_PRICE, 'L999,999') AS CLASS_PRICE, "
					+ "SUBSTR(teacher_regist_date, 1, 4)||'년'||SUBSTR(teacher_regist_date, 5, 2)||'월'||SUBSTR(teacher_regist_date, 7, 2)||'일' AS teacher_regist_date "
					+ "FROM tbl_teacher_202201";
		           
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
			String query= "select SUBSTR(regist_month, 1, 4)||'년'||SUBSTR(regist_month, 5, 2)||'월' as regist_month, "
					+ "c.c_no, "
					+ "m.c_name, "
					+ "case c.teacher_code when '100' then '초급반' when '200' then '중급반' when '300' then '고급반' when '400' then '심화반' end as teacher_code, "
					+ "c.class_area, "
					+ "TO_CHAR(c.tuition, 'L999,999') as tuition, "
					+ "m.grade "
					+ "from tbl_class_202201 c, tbl_member_202201 m where c.c_no = m.c_no ";
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
			String query = "insert into tbl_class_202201 "
					+ "(regist_month, c_no, class_area, tuition, teacher_code)"
					+ "values (?,?,?,?,?)";

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
