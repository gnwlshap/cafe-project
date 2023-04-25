package notice;

import java.sql.Timestamp;

public class NoticeDto {

	private int noticeNumber;
	private String userId;
	private String noticeTitle;
	private String noticeContent;
	private int noticeViewCount;
	private Timestamp noticePostDate;
	private Timestamp noticeEditDate;

	public NoticeDto(String userId, String noticeTitle, String noticeContent) {
		this.userId = userId;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
	}
	
	public NoticeDto(int noticeNumber,String userId, String noticeTitle, String noticeContent) {
		this.noticeNumber = noticeNumber;
		this.userId = userId;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
	}

	public NoticeDto(int noticeNumber, String userId, String noticeTitle, String noticeContent, int noticeViewCount,
			Timestamp noticePostDate, Timestamp noticeEditDate) {
		this.noticeNumber = noticeNumber;
		this.userId = userId;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeViewCount = noticeViewCount;
		this.noticePostDate = noticePostDate;
		this.noticeEditDate = noticeEditDate;
	}

	public int getNoticeNumber() {
		return noticeNumber;
	}

	public void setNoticeNumber(int noticeNumber) {
		this.noticeNumber = noticeNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public int getNoticeViewCount() {
		return noticeViewCount;
	}

	public void setNoticeViewCount(int noticeViewCount) {
		this.noticeViewCount = noticeViewCount;
	}

	public Timestamp getNoticePostDate() {
		return noticePostDate;
	}

	public void setNoticePostDate(Timestamp noticePostDate) {
		this.noticePostDate = noticePostDate;
	}

	public Timestamp getNoticeEditDate() {
		return noticeEditDate;
	}

	public void setNoticeEditDate(Timestamp noticeEditDate) {
		this.noticeEditDate = noticeEditDate;
	}

}
