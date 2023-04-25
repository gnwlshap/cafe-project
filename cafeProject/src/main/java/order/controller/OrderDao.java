package order.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import order.Order;
import order.OrderDto;
import util.DBManager;

public class OrderDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private OrderDao() {
	}

	private static OrderDao instance = new OrderDao();

	public static OrderDao getInstance() {
		return instance;
	}

	// C
	public void createOrder(OrderDto orderDto) {
		Order order = new Order(orderDto);
		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "INSERT INTO `order` (cafe_code, user_id) VALUES(?, ?)";
			try {
				this.pstmt = conn.prepareStatement(sql);

				this.pstmt.setInt(1, order.getCafeCode());
				this.pstmt.setString(2, order.getUserId());

				this.pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// R
	public ArrayList<Order> getAllOrder() {
		ArrayList<Order> list = new ArrayList<>();

		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "SELECT * FROM `order` ORDER BY order_code";

			try {
				this.pstmt = conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					Order order = null;
					int orderCode = this.rs.getInt(1);
					int cafeCode = this.rs.getInt(2);
					String userId = this.rs.getString(3);
					Timestamp orderTime = this.rs.getTimestamp(4);
					boolean written = this.rs.getBoolean(5);

					order = new Order(orderCode, cafeCode, userId, orderTime, written);
					list.add(order);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public ArrayList<Order> getOrderListByUserId(String userId) {
		ArrayList<Order> list = new ArrayList<>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM `order` WHERE user_id = ? ORDER BY order_code DESC";
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setString(1, userId);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					Order order = null;
					int orderCode = this.rs.getInt(1);
					int cafeCode = this.rs.getInt(2);
//					String userId = this.rs.getString(3);
					Timestamp orderTime = this.rs.getTimestamp(4);
					boolean written = this.rs.getBoolean(5);

					order = new Order(orderCode, cafeCode, userId, orderTime, written);
					list.add(order);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public int getThisOrderCodeByUserId(String userId) {
		int orderCode = -1;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT order_code FROM `order` WHERE user_id = ? ORDER BY order_time DESC LIMIT 1";

			try {
				this.pstmt = conn.prepareStatement(sql);

				this.pstmt.setString(1, userId);

				this.rs = this.pstmt.executeQuery();

				if (this.rs.next())
					orderCode = this.rs.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return orderCode;
	}

	public boolean getReviewWrittenByOrderCode(int orderCode) {
		boolean written = false;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "select review_written from `order`";
			sql += " where order_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, orderCode);
				this.rs = this.pstmt.executeQuery();
				
				if (this.rs.next()) {
					written = this.rs.getBoolean(1);
				}
				System.out.println("written : " + written);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return written;
	}

	// U
	public void updateReviewWrittenByOrderCode(int orderCode) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "UPDATE `order` SET review_written = true";
			sql += " WHERE order_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, orderCode);
				
				int resultInt = this.pstmt.executeUpdate();
				System.out.println("resultInt : " + resultInt);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// D
	public void deleteOrderByOrderCode(int orderCode) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "DELETE FROM `order` WHERE order_code=?";
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, orderCode);

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

}
