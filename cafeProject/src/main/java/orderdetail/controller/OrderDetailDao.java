package orderdetail.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import orderdetail.OrderDetail;
import util.DBManager;

public class OrderDetailDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private OrderDetailDao() {
	}

	private static OrderDetailDao instance = new OrderDetailDao();

	public static OrderDetailDao getInstance() {
		return instance;
	}

	// R
	public ArrayList<OrderDetail> getOrderDetailByOrderCode(int orderCode) {
		ArrayList<OrderDetail> list = new ArrayList<>();
		conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT menu_name, item_count, menu_price,";
			sql += " item_total_price FROM order_detail where order_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, orderCode);
				this.rs = this.pstmt.executeQuery();
				
				while (this.rs.next()) {
					String menuName = this.rs.getString(1);
					int itemCount = this.rs.getInt(2);
					int menuPrice = this.rs.getInt(3);
					int totalPrice = this.rs.getInt(4);

					OrderDetail order = new OrderDetail(menuName, itemCount, menuPrice, totalPrice);
					list.add(order);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public Integer getTotalPriceByOrderCode(int orderCode) {
		conn = DBManager.getConnection();
		int totalPrice = -1;
		
		if (this.conn != null) {
			String sql = "SELECT order_code, SUM(item_total_price) AS total_price ";
			sql += "FROM order_detail ";
			sql += "GROUP BY order_code, user_id, user_name ";
			sql += "having order_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, orderCode);
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
					totalPrice = this.rs.getInt(2);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return totalPrice;
	}

}
