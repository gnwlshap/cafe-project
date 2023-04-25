package item;

public class Item {
	private int orderCode, menuCode, itemCount;
	
	public Item(int orderCode, int menuCode, int itemCount) {
		this.orderCode = orderCode;
		this.menuCode = menuCode;
		this.itemCount = itemCount;
	}
	
	public Item(ItemDto itemDto) {
		this.orderCode = itemDto.getOrderCode();
		this.menuCode = itemDto.getMenuCode();
		this.itemCount = itemDto.getItemCount();
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
	
}
