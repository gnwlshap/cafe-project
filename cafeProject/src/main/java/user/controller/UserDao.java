package user.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import user.User;
import user.UserDto;
import util.DBManager;

public class UserDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private UserDao() {
	}

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	// C
	public void createUser(UserDto userDto) {
		User user = new User(userDto);

		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "INSERT INTO user (user_id, user_password,";
			sql += "user_name, user_address, user_phone) VALUES (?, ?, ?, ?, ?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, user.getUserId());
				this.pstmt.setString(2, user.getUserPassword());
				this.pstmt.setString(3, user.getUserName());
				this.pstmt.setString(4, user.getUserAddress());
				this.pstmt.setString(5, user.getUserPhone());

				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// R
	public ArrayList<User> getUserAll() {
		ArrayList<User> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM user ORDER BY user_reg_date";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String userId = this.rs.getString(1);
					String userPassword = this.rs.getString(2);
					String userName = this.rs.getString(3);
					String userAddress = this.rs.getString(4);
					String userPhone = this.rs.getString(5);
					Timestamp userRegistDate = this.rs.getTimestamp(6);

					User user = new User(userId, userPassword, userName, userAddress, userPhone, userRegistDate);
					list.add(user);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	// read (본인정보조회)
	public User getUserById(String id) {
		User user = null;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM user WHERE user_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					String userId = this.rs.getString(1);
					String userPassword = this.rs.getString(2);
					String userName = this.rs.getString(3);
					String userAddress = this.rs.getString(4);
					String userPhone = this.rs.getString(5);
					Timestamp userRegistDate = this.rs.getTimestamp(6);

					user = new User(userId, userPassword, userName, userAddress, userPhone, userRegistDate);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return user;
	}
	
	public boolean checkIdExist(String id) {
		boolean isIdExist = false;
		conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT COUNT(*) FROM user WHERE user_id = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					int count = rs.getInt(1);
					if (count > 0) {
						isIdExist = true;
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.idDuplCheck(conn, pstmt, rs);
			}
		}

		return isIdExist;
	}
	
	public boolean checkPhoneExist(String phone) {
	    boolean isPhoneExist = false;
	    conn = DBManager.getConnection();

	    if(this.conn != null) {
	    	String sql ="SELECT COUNT(*) FROM user WHERE user_phone = ?";
	    	try {
	    		pstmt = conn.prepareStatement(sql);
	    		pstmt.setString(1, phone);
	    		rs = pstmt.executeQuery();
	    		
	    		if (rs.next()) {
	    			int count = rs.getInt(1);
	    			if (count > 0) {
	    				isPhoneExist = true;
	    			}
	    		}
	    		
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	} finally {
	    		DBManager.idDuplCheck(conn, pstmt, rs);
	    	}
	    }

	    return isPhoneExist;
	  }
	

	// U
	public void updatePasswordById(String id, String password) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "UPDATE user SET user_password = ? WHERE user_id = ?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, password);
				this.pstmt.setString(2, id);

				int resultInt = this.pstmt.executeUpdate();
				System.out.println("resultInt ==> " + resultInt);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// 유저 업데이트
	public void updateUser(UserDto userDto) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "UPDATE user SET user_id = ?, user_password = ?, ";
			sql += "user_name = ?, user_address = ?, user_phone = ? WHERE user_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, userDto.getUserId());
				this.pstmt.setString(2, userDto.getUserPassword());
				this.pstmt.setString(3, userDto.getUserName());
				this.pstmt.setString(4, userDto.getUserAddress());
				this.pstmt.setString(5, userDto.getUserPhone());
				this.pstmt.setString(6, userDto.getUserId());

				int resultInt = pstmt.executeUpdate();
				System.out.println("resultInt ==> " + resultInt);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// D
	public void deleteUserById(String userId) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "DELETE FROM user WHERE user_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, userId);
				
				this.pstmt.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}
}
