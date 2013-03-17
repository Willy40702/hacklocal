package com.mkyong.customer;

import java.sql.Timestamp;

public class Customer 
{
	int userId;
	String name;
	String password;
	int enabled;
	
	
	public Customer(int userId, String name, String password, int enabled) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.enabled = enabled;
	}
	
	public int getuserId() {
		return userId;
	}
	public void setCustId(int custId) {
		this.userId = custId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setAge(String password) {
		this.password = password;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "Customer [password=" + password + ", UserId=" + userId + ", name=" + name
				+ "]";
	}
	
	
}
