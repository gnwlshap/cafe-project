package notice;

import java.sql.Timestamp;

public class Notice {

	private int noticeNumber;
	private String userId;
	private String noticeTitle;
	private String noticeContent;
	private int noticeViewCount;
	private Timestamp noticePostDate;
	private Timestamp noticeEditDate;

	public Notice(String userId, String noticeTitle, String noticeContent) {
		this.userId = userId;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
	}

	public Notice(int noticeNumber, String userId, String noticeTitle, String noticeContent, int noticeViewCount,
			Timestamp noticePostDate, Timestamp noticeEditDate) {
		this.noticeNumber = noticeNumber;
		this.userId = userId;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeViewCount = noticeViewCount;
		this.noticePostDate = noticePostDate;
		this.noticeEditDate = noticeEditDate;
	}

	public Notice(NoticeDto noticeDto) {
		this.noticeNumber = noticeDto.getNoticeNumber();
		this.userId = noticeDto.getUserId();
		this.noticeTitle = noticeDto.getNoticeTitle();
		this.noticeContent = noticeDto.getNoticeContent();
		this.noticeViewCount = noticeDto.getNoticeViewCount();
	}

	public int getNoticeNumber() {
		return noticeNumber;
	}

	public String getUserId() {
		return userId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent.replaceAll("\\n","<br>");
	}

	public int getNoticeViewCount() {
		return noticeViewCount;
	}

	public String getNoticePostDate() {
		return noticePostDate.toString().substring(0, 10);
	}

	public String getNoticeEditDate() {
		return noticeEditDate.toString().substring(0, 10);
	}

}
