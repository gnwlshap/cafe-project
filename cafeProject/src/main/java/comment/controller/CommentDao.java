package comment.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import comment.Comment;
import comment.CommentDto;
import util.DBManager;

public class CommentDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CommentDao() {
	}

	private static CommentDao instance = new CommentDao();

	public static CommentDao getInstance() {
		return instance;
	}

	// C
	public void createComment(CommentDto commentDto) {
		Comment comment = new Comment(commentDto);
		this.conn = DBManager.getConnection();

		String sql = "INSERT INTO comment(review_number, user_id, comment_content) VALUES(?, ?, ?)";
		try {
			this.pstmt = conn.prepareStatement(sql);

			this.pstmt.setInt(1, comment.getReviewNumber());
			this.pstmt.setString(2, comment.getUserId());
			this.pstmt.setString(3, comment.getCommentContent());

			this.pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// R
	public ArrayList<Comment> getAllComment() {
		ArrayList<Comment> list = new ArrayList<>();

		this.conn = DBManager.getConnection();

		String sql = "SELECT * FROM comment ORDER BY comment_seq DESC";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();

			while (this.rs.next()) {
				Comment comment = null;
				int commentSeq = this.rs.getInt(1);
				int reviewNumber = this.rs.getInt(2);
				String userId = this.rs.getString(3);
				String commentContent = this.rs.getString(4);
				Timestamp commentPostDate = this.rs.getTimestamp(5);
				Timestamp commentEditDate = this.rs.getTimestamp(6);

				comment = new Comment(commentSeq, reviewNumber, userId, commentContent, commentPostDate,
						commentEditDate);
				list.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 리뷰 게시글 번호로 해당 게시글 댓글 가져오기
	public ArrayList<Comment> getCommentByReviewNumber(int reviewNumber) {
		ArrayList<Comment> list = new ArrayList<>();

		this.conn = DBManager.getConnection();

		String sql = "SELECT * FROM comment WHERE review_number = ? ORDER BY comment_seq";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, reviewNumber);

			this.rs = this.pstmt.executeQuery();

			while (this.rs.next()) {
				Comment comment = null;
				int commentSeq = this.rs.getInt(1);
				String userId = this.rs.getString(3);
				String commentContent = this.rs.getString(4);
				Timestamp commentPostDate = this.rs.getTimestamp(5);
				Timestamp commentEditDate = this.rs.getTimestamp(6);

				comment = new Comment(commentSeq, reviewNumber, userId, commentContent, commentPostDate,
						commentEditDate);
				list.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// id로 댓글가져오기 (마이페이지에서 내가쓴댓글)
	public ArrayList<Comment> getCommentByUserId(String userId) {
		ArrayList<Comment> list = new ArrayList<>();

		this.conn = DBManager.getConnection();

		String sql = "SELECT * FROM comment WHERE user_id = ? ORDER BY comment_seq DESC";

		try {
			this.pstmt = conn.prepareStatement(sql);

			this.pstmt.setString(1, userId);

			this.rs = this.pstmt.executeQuery();

			while (this.rs.next()) {
				Comment comment = null;
				int commentSeq = this.rs.getInt(1);
				int reviewNumber = this.rs.getInt(2);
//				String userId = this.rs.getString(3);
				String commentContent = this.rs.getString(4);
				Timestamp commentPostDate = this.rs.getTimestamp(5);
				Timestamp commentEditDate = this.rs.getTimestamp(6);

				comment = new Comment(commentSeq, reviewNumber, userId, commentContent, commentPostDate,
						commentEditDate);
				list.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	

	// U
	// 코멘트 코드로 식별 > 코멘트(댓글) 수정
	public Comment updateCommentContentBycommentSeq(String commentContent, int commentSeq) {
		Comment comment = null;

		this.conn = DBManager.getConnection();

		String sql = "UPDATE comment SET comment_content=? WHERE comment_seq=?";
		try {
			this.pstmt = conn.prepareStatement(sql);

			this.pstmt.setString(1, commentContent);
			this.pstmt.setInt(2, commentSeq);

			int resultInt = this.pstmt.executeUpdate();
			System.out.println("resultInt ==> " + resultInt);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		comment = getCommentBycommentSeq(commentSeq);		
		return comment;
	}
	
	private Comment getCommentBycommentSeq(int commentSeq) {
		Comment comment = null;
		this.conn = DBManager.getConnection();
		
		if( this.conn != null) {
			String sql = "SELECT * FROM comment WHERE comment_seq = ?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, commentSeq);
				this.rs = this.pstmt.executeQuery();
				
				if( this.rs.next()) {
					int seq = this.rs.getInt(1);
					int reviewNumber = this.rs.getInt(2);
					String userId = this.rs.getString(3);
					String content = this.rs.getString(4);
					Timestamp postDate = this.rs.getTimestamp(5);
					Timestamp editDate = this.rs.getTimestamp(5);
					
					comment = new Comment(seq, reviewNumber, userId, content, postDate, editDate);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
		}
		return comment;
	}

	// D
	public void deleteCommenBycommentSeq(int commentSeq) {
		this.conn = DBManager.getConnection();

		String sql = "DELETE FROM comment WHERE comment_seq=?";
		try {
			this.pstmt = conn.prepareStatement(sql);

			this.pstmt.setInt(1, commentSeq);

			this.pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

}
