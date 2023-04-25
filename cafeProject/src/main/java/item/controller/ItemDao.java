package item.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import item.Item;
import item.ItemDto;
import util.DBManager;

public class ItemDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private ItemDao() {}
	private static ItemDao instance = new ItemDao();
	public static ItemDao getInstance() {
		return instance;
	}
	
	// C
	public void createItem(ItemDto itemDto) {
		Item item = new Item(itemDto);
		this.conn = DBManager.getConnection();
		if( this.conn != null) {
			String sql = "INSERT INTO item(order_code, menu_code, item_count) VALUES(?, ?, ?)";
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, item.getOrderCode());
				this.pstmt.setInt(2, item.getMenuCode());
				this.pstmt.setInt(3, item.getItemCount());
				
				this.pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
	
	// R
	public ArrayList<Item> getAllItem(int orderCode) {
		ArrayList<Item> list = new ArrayList<>();
		
		this.conn = DBManager.getConnection();
		if( this.conn != null) {
			String sql = "SELECT * FROM item WHERE order_code = ? ORDER BY order_code DESC";
			
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, orderCode);
				
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					Item item = null;
//				int orderCode = this.rs.getInt(1);
					int menuCode = this.rs.getInt(2);
					int itemCount = this.rs.getInt(3);
					
					item = new Item(orderCode, menuCode, itemCount);
					list.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}
	
	// U
	
	// D
	
}
