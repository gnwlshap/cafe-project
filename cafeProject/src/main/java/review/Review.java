package review;

import java.sql.Timestamp;

public class Review {

	private int reviewNumber;
	private String userId;
	private String reviewTitle;
	private String reviewContent;
	private int reviewViewCount;
	private Timestamp reviewPostDate;
	private Timestamp reviewEditDate;

	public Review(String userId, String reviewTitle, String reviewContent) {
		this.userId = userId;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
	}

	public Review(int reviewNumber, String userId, String reviewTitle, String reviewContent, int reviewViewCount,
			Timestamp reviewPostDate, Timestamp reviewEditDate) {
		this.reviewNumber = reviewNumber;
		this.userId = userId;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewViewCount = reviewViewCount;
		this.reviewPostDate = reviewPostDate;
		this.reviewEditDate = reviewEditDate;
	}

	public Review(ReviewDto reviewDto) {
		this.reviewNumber = reviewDto.getReviewNumber();
		this.userId = reviewDto.getUserId();
		this.reviewTitle = reviewDto.getReviewTitle();
		this.reviewContent = reviewDto.getReviewTitle();
		this.reviewViewCount = reviewDto.getReviewViewCount();
	}

	public int getReviewNumber() {
		return reviewNumber;
	}

	public String getUserId() {
		return userId;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent.replaceAll("\\n","<br>");
	}

	public int getReviewViewCount() {
		return reviewViewCount;
	}

	public String getReviewPostDate() {
		return reviewPostDate.toString().substring(0, 10);
	}

	public String getReviewEditDate() {
		return reviewEditDate.toString().substring(0, 19);
	}

}
