package menu.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import menu.Menu;
import menu.MenuDto;
import util.DBManager;

public class MenuDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private MenuDao() {
	}

	private static MenuDao instance = new MenuDao();

	public static MenuDao getInstance() {
		return instance;
	}

	// C
	public void CreateMenu(MenuDto menuDto) {
		Menu menu = new Menu(menuDto);
		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "INSERT INTO menu (menu_name, menu_category, menu_price, menu_info)";
			sql += "VALUES (?, ?, ?, ?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, menu.getMenuName());
				this.pstmt.setString(2, menu.getMenuCategory());
				this.pstmt.setInt(3, menu.getMenuPrice());
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// R
	public ArrayList<Menu> getMenuAll() {
		ArrayList<Menu> list = new ArrayList<>();
		conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM menu ORDER BY menu_code";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					Integer menuCode = rs.getInt(1);
					String menuName = rs.getString(2);
					String menuCategory = rs.getString(3);
					Integer menuPrice = rs.getInt(4);
					String menuInfo = rs.getString(5);
					Timestamp menuRegistDate = rs.getTimestamp(6);

					Menu menu = new Menu(menuCode, menuName, menuCategory, menuPrice, menuInfo, menuRegistDate);
					list.add(menu);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}
	
	public ArrayList<Menu> getMenuByCategory(String menuCategory) {
		ArrayList<Menu> list = new ArrayList<>();
		conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM menu WHERE menu_category = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				
				this.pstmt.setString(1, menuCategory);
				
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					Integer menuCode = rs.getInt(1);
					String menuName = rs.getString(2);
//					String menuCategory = rs.getString(3);
					Integer menuPrice = rs.getInt(4);
					String menuInfo = rs.getString(5);
					Timestamp menuRegistDate = rs.getTimestamp(6);

					Menu menu = new Menu(menuCode, menuName, menuCategory, menuPrice, menuInfo, menuRegistDate);
					list.add(menu);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public String getMenuByMenuCode(int menuCode) {
		String menuName = null;
		conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT menu_name FROM menu WHERE menu_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				
				this.pstmt.setInt(1, menuCode);
				
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) 
					menuName = rs.getString(1);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return menuName;
	}
	
	public int getMenuCodeByMenuName(String menuName) {
		int menuCode = -1;
		conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT menu_code FROM menu WHERE menu_name = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				
				this.pstmt.setString(1, menuName);
				
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) 
					menuCode = rs.getInt(1);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return menuCode;
	}
	
	public int getMenuPriceByMenuCode(int menuCode) {
		int menuPrice = -1;
		conn = DBManager.getConnection();
		
		if (this.conn != null) {
			String sql = "SELECT menu_price FROM menu WHERE menu_code = ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				
				this.pstmt.setInt(1, menuCode);
				
				this.rs = this.pstmt.executeQuery();
				
				if (this.rs.next()) 
					menuPrice = rs.getInt(1);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return menuPrice;
	}
	// U
	public void updateMenuCode(MenuDto menuDto) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "UPDATE menu SET menu_name = ?, menu_category = ?, menu_price = ?, menu_info = ? WHERE menu_code = ?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, menuDto.getMenuName());
				this.pstmt.setString(2, menuDto.getMenuCategori());
				this.pstmt.setInt(3, menuDto.getMenuPrice());
				this.pstmt.setString(4, menuDto.getMenuInfo());
				this.pstmt.setInt(5, menuDto.getMenuCode());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// D
	public void deleteMenuByCode(int code) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "DELETE FROM menu WHERE menu_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, code);
				this.pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
}
