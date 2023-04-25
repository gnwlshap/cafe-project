package user;

import java.sql.Timestamp;

public class User {
	private String userId;
	private String userPassword;
	private String userName;
	private String userAddress;
	private String userPhone;
	private Timestamp userRegistDate;

	public User(String userId, String userPassword, String userName, String userAddress, String userPhone) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
	}

	public User(String userId, String userPassword, String userName, String userAddress, String userPhone,
			Timestamp userRegistDate) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userRegistDate = userRegistDate;
	}

	public User(UserDto userDto) {
		this.userId = userDto.getUserId();
		this.userPassword = userDto.getUserPassword();
		this.userName = userDto.getUserName();
		this.userAddress = userDto.getUserAddress();
		this.userPhone = userDto.getUserPhone();
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public String getUserRegistDate() {
		return userRegistDate.toString().substring(0, 19);
	}
	
	public String getUserRegistDateOnlyDate() {
		return userRegistDate.toString().substring(0, 10);
	}
}
