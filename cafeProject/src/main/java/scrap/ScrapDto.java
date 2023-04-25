package scrap;

public class ScrapDto {

	private int cafeCode;
	private String userId;

	public ScrapDto(int cafecode, String userid) {
		this.cafeCode = cafecode;
		this.userId = userid;
	}

	public int getCafeCode() {
		return cafeCode;
	}

	public void setCafeCode(int cafeCode) {
		this.cafeCode = cafeCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
