package dto;

public class RspDto {
	
	private String you;
	private String com;
	private String result;
	
	public RspDto() {
		
	}

	public RspDto(String you, String com, String result) {
		this.you = you;
		this.com = com;
		this.result = result;
	}

	public String getYou() {
		return you;
	}

	public void setYou(String you) {
		this.you = you;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
