package comment;

import java.sql.Timestamp;

public class Comment {
	
	private int commentSeq, reviewNumber;
	private String userId, commentContent;
	private Timestamp commentPostDate, commentEditDate;
	
	public Comment(int reviewNumber, String userId, String commentContent) {
		this.reviewNumber = reviewNumber;
		this.userId = userId;
		this.commentContent = commentContent;
	}
	
	public Comment(int commentSeq, int reviewNumber, String userId, String commentContent, Timestamp commentPostDate, Timestamp commentEditDate) {
		this.commentSeq = commentSeq;
		this.reviewNumber = reviewNumber;
		this.userId = userId;
		this.commentContent = commentContent;
		this.commentPostDate = commentPostDate;
		this.commentEditDate = commentEditDate;
	}
	
	public Comment(CommentDto commentDto) {
		this.reviewNumber = commentDto.getReviewNumber();
		this.userId = commentDto.getUserId();
		this.commentContent = commentDto.getCommentContent();
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
		return commentContent.replaceAll("\\n","<br>");
	}

	public String getCommentPostDate() {
		return commentPostDate.toString().substring(0, 19);
	}

	public String getCommentEditDate() {
		return commentEditDate.toString().substring(0, 19);
	}
	
}
