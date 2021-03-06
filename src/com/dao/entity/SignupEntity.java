package com.dao.entity;

import java.sql.Timestamp;

public class SignupEntity {
	private int sid;
	private String username;
	private String password;
	private String email;
	private String name;
	private String salutation;
	private Timestamp datecreated;
	private String role;
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public Timestamp getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Timestamp datecreated) {
		this.datecreated = datecreated;
	}

	@Override
	public String toString() {
		return "SignupEntity [sid=" + sid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", name=" + name + ", salutation=" + salutation + ", datecreated=" + datecreated + "]";
	}

}
