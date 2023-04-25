package menu;

import java.sql.Timestamp;

public class MenuDto {

	private int menuCode;
	private String menuName;
	private String menuCategory;
	private int menuPrice;
	private String menuInfo;
	private Timestamp menuRegistDate;

	public MenuDto(int menuCode, String menuName, String menuCategory, int menuPrice, String menuInfo,
			Timestamp menuRegistDate) {
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.menuCategory = menuCategory;
		this.menuPrice = menuPrice;
		this.menuInfo = menuInfo;
		this.menuRegistDate = menuRegistDate;
	}

	public int getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(int menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCategori() {
		return menuCategory;
	}

	public void setMenuCategori(String menuCategory) {
		this.menuCategory = menuCategory;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(String menuInfo) {
		this.menuInfo = menuInfo;
	}

	public Timestamp getMenuRegistDate() {
		return menuRegistDate;
	}

	public void setMenuRegistDate(Timestamp menuRegistDate) {
		this.menuRegistDate = menuRegistDate;
	}

}
