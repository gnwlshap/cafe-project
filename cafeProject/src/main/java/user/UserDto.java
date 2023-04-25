package user;

import java.sql.Timestamp;

public class UserDto {
	private String userId;
	private String userPassword;
	private String userName;
	private String userAddress;
	private String userPhone;
	private Timestamp userRegistDate;

	public UserDto(String userId, String userPassword, String userName, String userAddress, String userPhone) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
	}

	public UserDto(String userId, String userPassword, String userName, String userAddress, String userPhone,
			Timestamp userRegistDate) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userRegistDate = userRegistDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Timestamp getUserRegistDate() {
		return userRegistDate;
	}

	public void setUserRegistDate(Timestamp userRegDate) {
		this.userRegistDate = userRegDate;
	}
}
