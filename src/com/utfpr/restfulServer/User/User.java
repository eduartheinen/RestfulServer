package com.utfpr.restfulServer.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class User {
	private String id, username, password, email;

	public User(String id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String toJSON() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", this.id);
		obj.put("username", this.username);
		obj.put("password", this.password);
		obj.put("email", this.email);
		return obj.toString();
	}

	// CRUD using UserDAO
	public void create() throws ClassNotFoundException, SQLException {
		ResultSet rs = UserDAO.instance.create(this);

		if (rs.next())
			this.setId(rs.getString("id"));
	}
}
