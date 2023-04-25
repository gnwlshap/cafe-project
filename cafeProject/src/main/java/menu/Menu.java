package menu;

import java.sql.Timestamp;

public class Menu {

	private int menuCode;
	private String menuName;
	private String menuCategory;
	private int menuPrice;
	private String menuInfo;
	private Timestamp menuRegistDate;

	public Menu(int menuCode, String menuName, String menuCategory, int menuPrice, String menuInfo,
			Timestamp menuRegistDate) {
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.menuCategory = menuCategory;
		this.menuPrice = menuPrice;
		this.menuInfo = menuInfo;
		this.menuRegistDate = menuRegistDate;
	}

	public Menu(MenuDto menuDto) {
		this.menuCode = menuDto.getMenuCode();
		this.menuName = menuDto.getMenuName();
		this.menuCategory = menuDto.getMenuCategori();
		this.menuPrice = menuDto.getMenuPrice();
		this.menuInfo = menuDto.getMenuInfo();
		this.menuRegistDate = menuDto.getMenuRegistDate();
	}

	public int getMenuCode() {
		return menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getMenuCategory() {
		return menuCategory;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public String getMenuInfo() {
		return menuInfo;
	}

	public String getMenuRegistDate() {
		return menuRegistDate.toString().substring(0, 19);
	}

}
