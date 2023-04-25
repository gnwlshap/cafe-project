package scrap;

public class Scrap {

	private int cafeCode;
	private String userId;

	public Scrap(int cafecode, String userid) {
		this.cafeCode = cafecode;
		this.userId = userid;
	}

	public Scrap(ScrapDto scrapDto) {
		this.cafeCode = scrapDto.getCafeCode();
		this.userId = scrapDto.getUserId();
	}

	public int getCafeCode() {
		return cafeCode;
	}

	public String getUserId() {
		return userId;
	}

}
