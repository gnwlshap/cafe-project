package notice.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import notice.Notice;
import notice.NoticeDto;
import util.DBManager;

public class NoticeDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private NoticeDao() {
	}

	private static NoticeDao instance = new NoticeDao();

	public static NoticeDao getInstance() {
		return instance;
	}

	// C
	public void writeNotice(NoticeDto noticeDto) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "INSERT INTO notice (user_id, notice_title, notice_content)";
			sql += "VALUES ( ?, ?, ?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, noticeDto.getUserId());
				this.pstmt.setString(2, noticeDto.getNoticeTitle());
				this.pstmt.setString(3, noticeDto.getNoticeContent());

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	// R
	// 전체 조회
	public ArrayList<Notice> getNoticeAll(int startIndex, int pageSize) {
		ArrayList<Notice> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM notice ORDER BY notice_post_date DESC LIMIT ?, ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, startIndex);
				this.pstmt.setInt(2, pageSize);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int noticeNumber = this.rs.getInt(1);
					String userId = this.rs.getString(2);
					String noticeTitle = this.rs.getString(3);
					String noticeContent = this.rs.getString(4);
					int reviewViewCount = this.rs.getInt(5);
					Timestamp noticePostDate = this.rs.getTimestamp(6);
					Timestamp noticeEditDate = this.rs.getTimestamp(7);

					Notice notice = new Notice(noticeNumber, userId, noticeTitle, noticeContent, reviewViewCount,
							noticePostDate, noticeEditDate);
					list.add(notice);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	// 게시판에서 게시글 클릭
	public Notice getNoticeByNoticeNum(int noticeNumber) {
		Notice notice = null;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM notice WHERE notice_number = ?";
			try {
				updateNoticeCountByNoticeNumber(noticeNumber);
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, noticeNumber);
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
					String userId = this.rs.getString(2);
					String noticeTitle = this.rs.getString(3);
					String noticeContent = this.rs.getString(4);
					int reviewViewCount = this.rs.getInt(5);
					Timestamp noticePostDate = this.rs.getTimestamp(6);
					Timestamp noticeEditDate = this.rs.getTimestamp(7);

					notice = new Notice(noticeNumber, userId, noticeTitle, noticeContent, reviewViewCount,
							noticePostDate, noticeEditDate);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return notice;
	}
	
	// 게시글 수정 게시글 정보
	public Notice getNoticeByNoticeNumOnlyData(int noticeNumber) {
		Notice notice = null;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM notice WHERE notice_number = ?";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, noticeNumber);
				this.rs = this.pstmt.executeQuery();

				if (this.rs.next()) {
					String userId = this.rs.getString(2);
					String noticeTitle = this.rs.getString(3);
					String noticeContent = this.rs.getString(4);
					int reviewViewCount = this.rs.getInt(5);
					Timestamp noticePostDate = this.rs.getTimestamp(6);
					Timestamp noticeEditDate = this.rs.getTimestamp(7);

					notice = new Notice(noticeNumber, userId, noticeTitle, noticeContent, reviewViewCount,
							noticePostDate, noticeEditDate);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return notice;
	}
	
	// 제목 검색
	public ArrayList<Notice> getNoticeByNoticeTile(String title, int startIndex, int pageSize) {
		ArrayList<Notice> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			// 제목 검색
			String sql = "SELECT * FROM notice WHERE notice_title LIKE ? ORDER BY notice_post_date DESC LIMIT ?, ?";
			try {
				String key = "%" + title + "%";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, key);
				this.pstmt.setInt(2, startIndex);
				this.pstmt.setInt(3, pageSize);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int noticeNumber = this.rs.getInt(1);
					String userId = this.rs.getString(2);
					String noticeTitle = this.rs.getString(3);
					String noticeContent = this.rs.getString(4);
					int noticeViewCount = this.rs.getInt(5);
					Timestamp noticePostDate = this.rs.getTimestamp(6);
					Timestamp noticeEditDate = this.rs.getTimestamp(7);

					Notice notice = new Notice(noticeNumber, userId, noticeTitle, noticeContent, noticeViewCount,
							noticePostDate, noticeEditDate);
					list.add(notice);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	// 내용 검색
	public ArrayList<Notice> getNoticeByNoticeContent(String content, int startIndex, int pageSize) {
		ArrayList<Notice> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			// 내용 검색
			String sql = "SELECT * FROM notice WHERE notice_content LIKE ? ORDER BY notice_post_date DESC LIMIT ?, ?";

			try {
				String key = "%" + content + "%";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, key);
				this.pstmt.setInt(2, startIndex);
				this.pstmt.setInt(3, pageSize);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int noticeNumber = this.rs.getInt(1);
					String userId = this.rs.getString(2);
					String noticeTitle = this.rs.getString(3);
					String noticeContent = this.rs.getString(4);
					int reviewViewCount = this.rs.getInt(5);
					Timestamp noticePostDate = this.rs.getTimestamp(6);
					Timestamp noticeEditDate = this.rs.getTimestamp(7);

					Notice notice = new Notice(noticeNumber, userId, noticeTitle, noticeContent, reviewViewCount,
							noticePostDate, noticeEditDate);
					list.add(notice);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	// 제목+내용 검색
	public ArrayList<Notice> getNoticeByKeyword(String keyword, int startIndex, int pageSize) {
		ArrayList<Notice> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			// 제목+내용 검색
			String sql = "SELECT * FROM notice WHERE notice_title LIKE ? OR notice_content LIKE ? ";
			sql += " ORDER BY notice_post_date DESC LIMIT ?, ?";

			try {
				String key = "%" + keyword + "%";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, key);
				this.pstmt.setString(2, key);
				this.pstmt.setInt(3, startIndex);
				this.pstmt.setInt(4, pageSize);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int noticeNumber = this.rs.getInt(1);
					String userId = this.rs.getString(2);
					String noticeTitle = this.rs.getString(3);
					String noticeContent = this.rs.getString(4);
					int noticeViewCount = this.rs.getInt(5);
					Timestamp noticePostDate = this.rs.getTimestamp(6);
					Timestamp noticeEditDate = this.rs.getTimestamp(7);

					Notice notice = new Notice(noticeNumber, userId, noticeTitle, noticeContent, noticeViewCount,
							noticePostDate, noticeEditDate);
					list.add(notice);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	public int getNoticeCount() {
		int count = 0;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "select count(*) from notice";
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return count;
	}

	public int getNoticeCountByCategory(int category, String keyword) {
		int count = 0;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "";
			if (category == 1) {
				sql = "select count(*) from notice WHERE notice_title LIKE ?";
				try {
					String key = "%" + keyword + "%";
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, key);
					this.rs = this.pstmt.executeQuery();

					if (rs.next()) {
						count = rs.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DBManager.close(conn, pstmt, rs);
				}
			} else if (category == 2) {
				sql = "select count(*) from notice WHERE notice_content LIKE ?";
				try {
					String key = "%" + keyword + "%";
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, key);
					this.rs = this.pstmt.executeQuery();

					if (rs.next()) {
						count = rs.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DBManager.close(conn, pstmt, rs);
				}
			} else if (category == 3) {
				sql = "select count(*) from notice WHERE notice_title LIKE ? OR notice_content LIKE ?";
				try {
					String key = "%" + keyword + "%";
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, key);
					this.pstmt.setString(2, key);
					this.rs = this.pstmt.executeQuery();

					if (rs.next()) {
						count = rs.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DBManager.close(conn, pstmt, rs);
				}
			}
		}
		return count;
	}

	// U
	// 수정하기
	public void updateNotice(NoticeDto noticeDto) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "UPDATE notice SET notice_title = ?, notice_content = ? WHERE notice_number = ?";

			try {
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, noticeDto.getNoticeTitle());
				pstmt.setString(2, noticeDto.getNoticeContent());
				pstmt.setInt(3, noticeDto.getNoticeNumber());

				int resultInt = pstmt.executeUpdate();
				System.out.println("resultInt ==> " + resultInt);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	private void updateNoticeCountByNoticeNumber(int noticeNumber) {
		String sql = "UPDATE notice SET notice_view = (notice_view + 1)";
		sql += "WHERE notice_number = ?";

		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNumber);

			int resultInt = pstmt.executeUpdate();
			System.out.println("resultInt ==> " + resultInt);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	public void updateNoticeCountByNoticeNumber(int noticeNumber) {
//		this.conn = DBManager.getConnection();
//
//		if (this.conn != null) {
//			String sql = "UPDATE notice SET notice_view = (notice_view + 1)";
//			sql += "WHERE notice_number = ?";
//
//			try {
//				pstmt = this.conn.prepareStatement(sql);
//				pstmt.setInt(1, noticeNumber);
//
//				int resultInt = pstmt.executeUpdate();
//				System.out.println("resultInt ==> " + resultInt);
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				DBManager.close(conn, pstmt);
//			}
//		}
//	}

	// D
	public void deleteBoardtByBoardNum(int noticeNumber) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "DELETE FROM notice WHERE notice_number= ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, noticeNumber);

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

}
