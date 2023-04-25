package orderhistory.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import orderhistory.OrderHistory;
import util.DBManager;

public class OrderHistoryDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private OrderHistoryDao() {
	}

	private static OrderHistoryDao instance = new OrderHistoryDao();

	public static OrderHistoryDao getInstance() {
		return instance;
	}
	
	public ArrayList<OrderHistory> getOrderHistoryByUserId(String userId) {
		ArrayList<OrderHistory> list = new ArrayList<>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM order_history WHERE user_id = ? ORDER BY order_time DESC;";
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setString(1, userId);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					OrderHistory oh = null;
					int orderCode = this.rs.getInt(1);
					String cafeName = this.rs.getString(2);
					Timestamp orderTime = this.rs.getTimestamp(3);
					Boolean reviewWritten = this.rs.getBoolean(4);
					String id = this.rs.getString(5);

					oh = new OrderHistory(orderCode, cafeName, orderTime, reviewWritten, id);
					list.add(oh);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}
	
	
	
}
