package review.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import review.Review;
import review.ReviewDto;
import util.DBManager;

public class ReviewDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ReviewDao() {
	}

	private static ReviewDao instance = new ReviewDao();

	public static ReviewDao getInstance() {
		return instance;
	}

	// C
	public boolean writeReview(ReviewDto reviewDto) {
		boolean isSuccess = false;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "INSERT INTO review (user_id, review_title, review_content)";
			sql += "VALUES ( ?, ?, ?)";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, reviewDto.getUserId());
				this.pstmt.setString(2, reviewDto.getReviewTitle());
				this.pstmt.setString(3, reviewDto.getReviewContent());

				int resultInt = this.pstmt.executeUpdate(); 
				isSuccess = (resultInt > 0);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
		
		return isSuccess;
	}

	// R
	// 전체 조회 [조회수 순서 출력 따로 넣어야하나?]
	public ArrayList<Review> getReviewAll(int startIndex, int pageSize) {
		ArrayList<Review> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM review ORDER BY review_post_date DESC LIMIT ?, ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, startIndex);
				this.pstmt.setInt(2, pageSize);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int reviewNumber = this.rs.getInt(1);
					String userId = this.rs.getString(2);
					String reviewTitle = this.rs.getString(3);
					String reviewContent = this.rs.getString(4);
					int reviewViewCount = this.rs.getInt(5);
					Timestamp reviewPostDate = this.rs.getTimestamp(6);
					Timestamp reviewEditDate = this.rs.getTimestamp(7);

					Review review = new Review(reviewNumber, userId, reviewTitle, reviewContent, reviewViewCount,
							reviewPostDate, reviewEditDate);
					list.add(review);
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
	public Review getReviewByReviewNumber(int reviewNumber) {
		Review review = null;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM review WHERE review_number = ?";

			try {
				updateReviewCountByReviewNumber(reviewNumber);
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, reviewNumber);
				this.rs = this.pstmt.executeQuery();


				if (this.rs.next()) {
					String userId = this.rs.getString(2);
					String reviewTitle = this.rs.getString(3);
					String reviewContent = this.rs.getString(4);
					int reviewViewCount = this.rs.getInt(5);
					Timestamp reviewPostDate = this.rs.getTimestamp(6);
					Timestamp reviewEditDate = this.rs.getTimestamp(7);

					review = new Review(reviewNumber, userId, reviewTitle, reviewContent, reviewViewCount,
							reviewPostDate, reviewEditDate);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return review;
	}
	
	// 게시글 수정 게시글 정보
	public Review getReviewByReviewNumberOnlyData(int reviewNumber) {
		Review review = null;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM review WHERE review_number = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, reviewNumber);
				this.rs = this.pstmt.executeQuery();


				if (this.rs.next()) {
					String userId = this.rs.getString(2);
					String reviewTitle = this.rs.getString(3);
					String reviewContent = this.rs.getString(4);
					int reviewViewCount = this.rs.getInt(5);
					Timestamp reviewPostDate = this.rs.getTimestamp(6);
					Timestamp reviewEditDate = this.rs.getTimestamp(7);

					review = new Review(reviewNumber, userId, reviewTitle, reviewContent, reviewViewCount,
							reviewPostDate, reviewEditDate);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return review;
	}

	// 작성자 검색
	public ArrayList<Review> getReviewByUserId(String userId, int startIndex, int pageSize) {
		ArrayList<Review> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM review WHERE user_id LIKE ? ORDER BY review_post_date DESC LIMIT ?, ?";

			try {
				String key = "%" + userId + "%";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, key);
				this.pstmt.setInt(2, startIndex);
				this.pstmt.setInt(3, pageSize);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int reviewNumber = this.rs.getInt(1);
					String id = this.rs.getString(2);
					String reviewTitle = this.rs.getString(3);
					String reviewContent = this.rs.getString(4);
					int reviewViewCount = this.rs.getInt(5);
					Timestamp reviewPostDate = this.rs.getTimestamp(6);
					Timestamp reviewEditDate = this.rs.getTimestamp(7);

					Review review = new Review(reviewNumber, id, reviewTitle, reviewContent, reviewViewCount,
							reviewPostDate, reviewEditDate);
					list.add(review);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}

	// 제목 검색
	public ArrayList<Review> getReviewByReviewTitle(String title, int startIndex, int pageSize) {
		ArrayList<Review> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM review WHERE review_title LIKE ? ORDER BY review_post_date DESC LIMIT ?, ?";

			try {
				String key = "%" + title + "%";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, key);
				this.pstmt.setInt(2, startIndex);
				this.pstmt.setInt(3, pageSize);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int reviewNumber = this.rs.getInt(1);
					String userId = this.rs.getString(2);
					String reviewTitle = this.rs.getString(3);
					String reviewContent = this.rs.getString(4);
					int reviewViewCount = this.rs.getInt(5);
					Timestamp reviewPostDate = this.rs.getTimestamp(6);
					Timestamp reviewEditDate = this.rs.getTimestamp(7);

					Review review = new Review(reviewNumber, userId, reviewTitle, reviewContent, reviewViewCount,
							reviewPostDate, reviewEditDate);
					list.add(review);
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
	public ArrayList<Review> getReviewByReviewContent(String content, int startIndex, int pageSize) {
		ArrayList<Review> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM review WHERE review_content LIKE ? ORDER BY review_post_date DESC LIMIT ?, ?";

			try {
				String key = "%" + content + "%";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, key);
				this.pstmt.setInt(2, startIndex);
				this.pstmt.setInt(3, pageSize);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int reviewNumber = this.rs.getInt(1);
					String userId = this.rs.getString(2);
					String reviewTitle = this.rs.getString(3);
					String reviewContent = this.rs.getString(4);
					int reviewViewCount = this.rs.getInt(5);
					Timestamp reviewPostDate = this.rs.getTimestamp(6);
					Timestamp reviewEditDate = this.rs.getTimestamp(7);

					Review review = new Review(reviewNumber, userId, reviewTitle, reviewContent, reviewViewCount,
							reviewPostDate, reviewEditDate);
					list.add(review);
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
	public ArrayList<Review> getReviewByKeyword(String keyword, int startIndex, int pageSize) {
		ArrayList<Review> list = new ArrayList<>();
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM review WHERE review_title LIKE ? OR review_content LIKE ?";
			sql += "ORDER BY review_post_date DESC LIMIT ?, ?";

			try {
				String key = "%" + keyword + "%";
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, key);
				this.pstmt.setString(2, key);
				this.pstmt.setInt(3, startIndex);
				this.pstmt.setInt(4, pageSize);

				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int reviewNumber = this.rs.getInt(1);
					String userId = this.rs.getString(2);
					String reviewTitle = this.rs.getString(3);
					String reviewContent = this.rs.getString(4);
					int reviewViewCount = this.rs.getInt(5);
					Timestamp reviewPostDate = this.rs.getTimestamp(6);
					Timestamp reviewEditDate = this.rs.getTimestamp(7);

					Review review = new Review(reviewNumber, userId, reviewTitle, reviewContent, reviewViewCount,
							reviewPostDate, reviewEditDate);
					list.add(review);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return list;
	}
	
	public int getReviewCount() {
		int count = 0;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "select count(*) from review";
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
	
	public int getReviewCountByCategory(int category, String keyword) {
		int count = 0;
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "";
			if (category == 1) {
				sql = "select count(*) from review WHERE review_title LIKE ?";
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
				sql = "select count(*) from review WHERE review_content LIKE ?";
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
				sql = "select count(*) from review WHERE user_id LIKE ?";
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
			}  else if (category == 4) {
				sql = "select count(*) from review WHERE review_title LIKE ? OR review_content LIKE ?";
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
	public void updateReview(ReviewDto reviewDto) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "UPDATE review SET review_title = ?, review_content = ? WHERE review_number = ?";

			try {
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, reviewDto.getReviewTitle());
				pstmt.setString(2, reviewDto.getReviewContent());
				pstmt.setInt(3, reviewDto.getReviewNumber());

				int resultInt = pstmt.executeUpdate();
				System.out.println("resultInt ==> " + resultInt);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

	public void updateReviewCountByReviewNumber(int reviewNumber) {
		String sql = "UPDATE review SET review_view = (review_view + 1)";
		sql += "WHERE review_number = ?";

		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNumber);

			int resultInt = pstmt.executeUpdate();
			System.out.println("resultInt ==> " + resultInt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// D
	public void deleteBoardtByBoardNum(int reviewNumber) {
		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "DELETE FROM review WHERE review_number = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, reviewNumber);

				this.pstmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

}
