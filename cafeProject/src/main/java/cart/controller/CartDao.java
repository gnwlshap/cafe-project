package cart.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cart.Cart;
import cart.CartDto;
import util.DBManager;

public class CartDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CartDao() {
	}

	private static CartDao instance = new CartDao();

	public static CartDao getInstance() {
		return instance;
	}

	// C
	public void createCart(CartDto cartDto) {
		Cart cart = new Cart(cartDto);
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "INSERT INTO cart(user_id, cafe_code, menu_code, menu_count) VALUES(?, ?, ?, ?)";
			try {
				this.pstmt = conn.prepareStatement(sql);

				this.pstmt.setString(1, cart.getUserId());
				this.pstmt.setInt(2, cart.getCafeCode());
				this.pstmt.setInt(3, cart.getMenuCode());
				this.pstmt.setInt(4, cart.getMenuCount());

				this.pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// R
	public ArrayList<Cart> getCartByUserId(String userId) {
		ArrayList<Cart> list = new ArrayList<>();

		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "SELECT * FROM cart WHERE user_id = ?";
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setString(1, userId);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					Cart cart = null;
//				String userId = this.rs.getString(1);
					int cafeCode = this.rs.getInt(2);
					int menuCode = this.rs.getInt(3);
					int menuCount = this.rs.getInt(4);

					cart = new Cart(userId, cafeCode, menuCode, menuCount);
					list.add(cart);
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
	public void updateCartMenuCount(CartDto cartDto) {

		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "UPDATE cart SET menu_count = ? WHERE user_id = ? AND cafe_code = ? AND menu_code = ? ";
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, cartDto.getMenuCount());
				this.pstmt.setString(2, cartDto.getUserId());
				this.pstmt.setInt(3, cartDto.getCafeCode());
				this.pstmt.setInt(4, cartDto.getMenuCode());

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// D
	public void deleteCartByMenuCode(int menuCode, String userId, int cafeCode) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "DELETE FROM cart WHERE menu_code = ? AND user_id = ? AND cafe_code = ?";
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setInt(1, menuCode);
				this.pstmt.setString(2, userId);
				this.pstmt.setInt(3, cafeCode);

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	public void deleteCart(String userId) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "DELETE FROM cart WHERE user_id = ?";
			try {
				this.pstmt = conn.prepareStatement(sql);
				this.pstmt.setString(1, userId);

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
}
