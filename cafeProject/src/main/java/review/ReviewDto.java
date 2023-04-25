package review;

import java.sql.Timestamp;

public class ReviewDto {

	private int reviewNumber;
	private String userId;
	private String reviewTitle;
	private String reviewContent;
	private int reviewViewCount;
	private Timestamp reviewPostDate;
	private Timestamp reviewEditDate;

	public ReviewDto(String userId, String reviewTitle, String reviewContent) {
		this.userId = userId;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
	}
	
	public ReviewDto(int reviewNumber,String userId, String reviewTitle, String reviewContent) {
		this.reviewNumber = reviewNumber;
		this.userId = userId;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
	}

	public ReviewDto(int reviewNumber, String userId, String reviewTitle, String reviewContent, int reviewViewCount,
			Timestamp reviewPostDate, Timestamp reviewEditDate) {
		this.reviewNumber = reviewNumber;
		this.userId = userId;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewViewCount = reviewViewCount;
		this.reviewPostDate = reviewPostDate;
		this.reviewEditDate = reviewEditDate;
	}

	public int getReviewNumber() {
		return reviewNumber;
	}

	public void setReviewNumber(int reviewNumber) {
		this.reviewNumber = reviewNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getReviewViewCount() {
		return reviewViewCount;
	}

	public void setReviewViewCount(int reviewViewCount) {
		this.reviewViewCount = reviewViewCount;
	}

	public Timestamp getReviewPostDate() {
		return reviewPostDate;
	}

	public void setReviewPostDate(Timestamp reviewPostDate) {
		this.reviewPostDate = reviewPostDate;
	}

	public Timestamp getReviewEditDate() {
		return reviewEditDate;
	}

	public void setReviewEditDate(Timestamp reviewEditDate) {
		this.reviewEditDate = reviewEditDate;
	}

}
