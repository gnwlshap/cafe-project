package orderhistory;

import java.sql.Timestamp;

public class OrderHistory {

	private int orderCode;
	private String cafeName;
	private Timestamp orderTime;
	private Boolean reviewWritten;
	private String userId;

	public OrderHistory(int orderCode, String cafeName, Timestamp orderTime, Boolean reviewWritten, String userId) {
		this.orderCode = orderCode;
		this.cafeName = cafeName;
		this.orderTime = orderTime;
		this.reviewWritten = reviewWritten;
		this.userId = userId;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public String getCafeName() {
		return cafeName;
	}

	public String getOrderTime() {
		return orderTime.toString().substring(0,19);
	}

	public Boolean getReviewWritten() {
		return reviewWritten;
	}

	public String getUserId() {
		return userId;
	}

}