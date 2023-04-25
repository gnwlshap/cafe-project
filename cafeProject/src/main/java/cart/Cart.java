package cart;

public class Cart {
	private String userId;
	private int cafeCode, menuCode, menuCount;
	
	public Cart(String userId, int cafeCode, int menuCode, int menuCount) {
		this.userId = userId;
		this.cafeCode = cafeCode;
		this.menuCode = menuCode;
		this.menuCount = menuCount;
	}
	
	public Cart(CartDto cartDto) {
		this.userId = cartDto.getUserId();
		this.cafeCode = cartDto.getCafeCode();
		this.menuCode = cartDto.getMenuCode();
		this.menuCount = cartDto.getMenuCount();
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
	
}
