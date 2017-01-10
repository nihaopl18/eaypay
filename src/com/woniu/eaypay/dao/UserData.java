package com.woniu.eaypay.dao;

public class UserData {
	public static final int normal=1;
	public static final int admin=2;

	private int     id ;
	private String   userName ;
	private String   passWord ;
	private char     sex ;
	private String   birthday ;
	private String   identity_code;
	private String   email  ;
	private String   mobile ;
	private String   address  ;
	private int      status  ;//用户类型 1普通用户 2管理员
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getIdentity_code() {
		return identity_code;
	}
	public void setIdentity_code(String identity_code) {
		this.identity_code = identity_code;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "UserData [id=" + id + ", userName=" + userName + ", passWord="
				+ passWord + ", sex=" + sex + ", birthday=" + birthday
				+ ", identity_code=" + identity_code + ", email=" + email
				+ ", mobile=" + mobile + ", address=" + address + ", status="
				+ status + "]";
	}

}                                  
