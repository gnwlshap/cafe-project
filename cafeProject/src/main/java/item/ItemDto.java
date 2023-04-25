package item;

public class ItemDto {
	private int orderCode, menuCode, itemCount;
	
	public ItemDto(int orderCode, int menuCode, int itemCount) {
		this.orderCode = orderCode;
		this.menuCode = menuCode;
		this.itemCount = itemCount;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
}
