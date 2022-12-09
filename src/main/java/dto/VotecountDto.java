package dto;

public class VotecountDto {
	
	private String m_no;
	private String m_name;
	private String m_cnt;
	
	public VotecountDto() {
		
	}
	
	public VotecountDto(String m_no, String m_name, String m_cnt) {
		this.m_no = m_no;
		this.m_name = m_name;
		this.m_cnt = m_cnt;
	}

	public String getM_no() {
		return m_no;
	}

	public void setM_no(String m_no) {
		this.m_no = m_no;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_cnt() {
		return m_cnt;
	}

	public void setM_cnt(String m_cnt) {
		this.m_cnt = m_cnt;
	}

}
