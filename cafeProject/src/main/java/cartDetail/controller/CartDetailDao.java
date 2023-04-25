package cartDetail.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBManager;

public class CartDetailDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CartDetailDao() {}

	private static CartDetailDao instance = new CartDetailDao();

	public static CartDetailDao getInstance() {
		return instance;
	}
	
	// R
	public int getCartTotalByUserId(String userId) {
		int cartTotal = 0;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT user_id, SUM(total_price) as cart_total "
					+ "FROM cart_detail WHERE user_id = ? GROUP BY user_id";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, userId);
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) 
					cartTotal = this.rs.getInt(2);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return cartTotal;
	}
}
