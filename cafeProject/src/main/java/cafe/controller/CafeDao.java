package cafe.controller;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cafe.Cafe;
import cafe.CafeDto;
import util.DBManager;

public class CafeDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CafeDao() {
	}

	private static CafeDao instance = new CafeDao();

	public static CafeDao getInstance() {
		return instance;
	}

	// C
	public void CreateCafe(CafeDto cafeDto) {
		Cafe cafe = new Cafe(cafeDto);
		this.conn = DBManager.getConnection();
		if (this.conn != null) {
			String sql = "INSERT INTO cafe(cafe_name, cafe_address, cafe_phone)";
			sql += "VALUES (?, ?, ?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, cafe.getCafeName());
				this.pstmt.setString(2, cafe.getCafeAddress());
				this.pstmt.setString(3, cafe.getCafePhone());

				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// R
	public ArrayList<Cafe> getCafeAll() {
		ArrayList<Cafe> list = new ArrayList<>();
		conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM cafe ORDER BY cafe_regist_date";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int cafeCode = this.rs.getInt(1);
					String cafeName = this.rs.getString(2);
					String cafeAddress = this.rs.getString(3);
					String cafePhone = this.rs.getString(4);
					Timestamp cafeRegsitDate = this.rs.getTimestamp(5);

					Cafe cafe = new Cafe(cafeCode, cafeName, cafeAddress, cafePhone, cafeRegsitDate);
					list.add(cafe);

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public Cafe getCafeByCode(int code) { 
		Cafe cafe = null;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM cafe WHERE cafe_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				pstmt.setInt(1, code);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					int cafeCode = this.rs.getInt(1);
					String cafeName = this.rs.getString(2);
					String cafeAddress = this.rs.getString(3);
					String cafePhone = this.rs.getString(4);

					cafe = new Cafe(cafeCode, cafeName, cafeAddress, cafePhone);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return cafe;
	}

	public ArrayList<Cafe> getCafeByName(String cafeName) {
		ArrayList<Cafe> list = new ArrayList<>();
		this.conn = DBManager.getConnection();
		
		if(this.conn!= null) {
			String sql = "SELECT * FROM cafe WHERE cafe_name LIKE ?";
			
			try {
				String key = '%' + cafeName + '%';
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, key);
				
				this.rs = this.pstmt.executeQuery();
						
				while(this.rs.next()) {
					int cafeCode = this.rs.getInt(1);
					String name = this.rs.getString(2);
					String cafeAddress = this.rs.getString(3);
					String cafePhone = this.rs.getString(4);
					Timestamp cafeRegistDate = this.rs.getTimestamp(5);
					Cafe cafe = new Cafe(cafeCode, name, cafeAddress, cafePhone, cafeRegistDate);
					list.add(cafe);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public ArrayList<Cafe> getCafeByAddress(String cafeAddress) {
		ArrayList<Cafe> list = new ArrayList<>();
		this.conn = DBManager.getConnection();
		
		if(this.conn!= null) {
			String sql = "SELECT * FROM cafe WHERE cafe_address LIKE ?";
			
			try {
				String key = '%' + cafeAddress + '%';
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, key);
				
				this.rs = this.pstmt.executeQuery();
						
				while(this.rs.next()) {
					int cafeCode = this.rs.getInt(1);
					String cafeName = this.rs.getString(2);
					String address = this.rs.getString(3);
					String cafePhone = this.rs.getString(4);
					Timestamp cafeRegistDate = this.rs.getTimestamp(5);
					Cafe cafe = new Cafe(cafeCode, cafeName, address, cafePhone, cafeRegistDate);
					list.add(cafe);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	// U
	public void updateCafeCode(CafeDto cafeDto) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "UPDATE cafe SET cafe_name = ?, cafe_address = ?, cafe_phone = ? WHERE cafe_code = ?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, cafeDto.getCafeName());
				this.pstmt.setString(2, cafeDto.getCafeAddress());
				this.pstmt.setString(3, cafeDto.getCafePhone());
				this.pstmt.setInt(4, cafeDto.getCafeCode());

				int cafeCode = this.pstmt.executeUpdate();
				System.out.println("cafeCode ==> " + cafeCode);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// D
	public void deleteCafeByCode(int code) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "Delete FROM cafe WHERE cafe_code = ?";

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
