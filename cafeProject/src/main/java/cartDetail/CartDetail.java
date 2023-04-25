package cartDetail;

public class CartDetail {
	private String userId;
	private int menuCode, menuPrice, menuCount, totalPrice;
	
	public CartDetail(int menuCode, int menuPrice, int menuCount, int totalPrice) {
		this.menuCode = menuCode;
		this.menuPrice = menuPrice;
		this.menuCount = menuCount;
		this.totalPrice = totalPrice;
	}

	public String getUserId() {
		return userId;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public int getMenuCount() {
		return menuCount;
	}

	public int getTotalPrice() {
		return totalPrice;
	}
	
}
