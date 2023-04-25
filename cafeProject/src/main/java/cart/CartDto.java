package cart;

public class CartDto {
	private String userId;
	private int cafeCode, menuCode, menuCount;
	
	public CartDto(String userId, int cafeCode, int menuCode, int menuCount) {
		this.userId = userId;
		this.cafeCode = cafeCode;
		this.menuCode = menuCode;
		this.menuCount = menuCount;
	}

	public String getUserId() {
		return userId;
	}

	public int getCafeCode() {
		return cafeCode;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public int getMenuCount() {
		return menuCount;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCafeCode(int cafeCode) {
		this.cafeCode = cafeCode;
	}

	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}

	public void setMenuCount(int menuCount) {
		this.menuCount = menuCount;
	}
	
}
