package scrap.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import scrap.Scrap;
import scrap.ScrapDto;
import util.DBManager;

public class ScrapDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ScrapDao() {
	}

	private static ScrapDao instance = new ScrapDao();

	public static ScrapDao getInstance() {
		return instance;
	}

	// C
	public void createScrap(ScrapDto scrapDto) {
		this.conn = DBManager.getConnection();

		if (conn != null) {
			String sql = "INSERT INTO scrap (cafe_code, user_id) VALUES (?,?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, scrapDto.getCafeCode());
				this.pstmt.setString(2, scrapDto.getUserId());

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// R
	public ArrayList<Scrap> getScrapByUserId(String userid) {
		ArrayList<Scrap> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM scrap WHERE user_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, userid);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int cafecode = this.rs.getInt(1);

					Scrap scrap = new Scrap(cafecode, userid);
					list.add(scrap);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public ArrayList<Scrap> getScrapByCafeCode(int cafecode) {
		ArrayList<Scrap> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM scrap WHERE cafe_code = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, cafecode);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					String userid = this.rs.getString(2);

					Scrap scrap = new Scrap(cafecode, userid);
					list.add(scrap);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	// U - x

	// D
	public void deleteScraop(ScrapDto scrapDto) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "DELETE FROM scrap WHERE cafe_code = ? AND user_id = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, scrapDto.getCafeCode());
				this.pstmt.setString(2, scrapDto.getUserId());

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

}
