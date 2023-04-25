package orderdetail;

public class OrderDetail {

	private int orderCode, cafeCode;
	private String cafeName, userId, userName;
	private int menuCode;
	private String menuName;
	private int itemCount, menuPrice, totalPrice;

	public OrderDetail(int orderCode, int cafeCode, String cafeName, String userId, String userName, int menuCode,
			String menuName, int itemCount, int menuPrice, int totalPrice) {
		this.orderCode = orderCode;
		this.cafeCode = cafeCode;
		this.cafeName = cafeName;
		this.userId = userId;
		this.userName = userName;
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.itemCount = itemCount;
		this.menuPrice = menuPrice;
		this.totalPrice = totalPrice;
	}

	public OrderDetail(String menuName, int itemCount, int menuPrice, int totalPrice) {
		this.menuName = menuName;
		this.itemCount = itemCount;
		this.menuPrice = menuPrice;
		this.totalPrice = totalPrice;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public int getCafeCode() {
		return cafeCode;
	}

	public String getCafeName() {
		return cafeName;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public int getItemCount() {
		return itemCount;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

}
