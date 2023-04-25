package comment;

import java.sql.Timestamp;

public class CommentDto {
	
	private int commentSeq, reviewNumber;
	private String userId, commentContent;
	private Timestamp commentPostDate, commentEditDate;
	
	public CommentDto(int reviewNumber, String userId, String commentContent) {
		this.reviewNumber = reviewNumber;
		this.userId = userId;
		this.commentContent = commentContent;
	}
	
	public CommentDto(int commentSeq, int reviewNumber, String userId, String commentContent, Timestamp commentPostDate, Timestamp commentEditDate) {
		this.commentSeq = commentSeq;
		this.reviewNumber = reviewNumber;
		this.userId = userId;
		this.commentContent = commentContent;
		this.commentPostDate = commentPostDate;
		this.commentEditDate = commentEditDate;
	}
	
	public int getCommentSeq() {
		return commentSeq;
	}
	
	public int getReviewNumber() {
		return reviewNumber;
	}

	public String getUserId() {
		return userId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public Timestamp getCommentPostDate() {
		return commentPostDate;
	}

	public Timestamp getCommentEditDate() {
		return commentEditDate;
	}
	
	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
	}

	public void setReviewNumber(int reviewNumber) {
		this.reviewNumber = reviewNumber;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public void setCommentPostDate(Timestamp commentPostDate) {
		this.commentPostDate = commentPostDate;
	}

	public void setCommentEditDate(Timestamp commentEditDate) {
		this.commentEditDate = commentEditDate;
	}
	
}
