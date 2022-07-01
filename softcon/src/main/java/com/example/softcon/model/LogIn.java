package com.example.softcon.model;


//import java.util.List;

public class LogIn {
	private String email;
	private String password;

	public LogIn(String email,String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format(
				"LogIn [email=%s, password=%s]",email, password);
	}
}