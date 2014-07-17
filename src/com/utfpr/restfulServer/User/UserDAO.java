package com.utfpr.restfulServer.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.utfpr.restfulServer.Database;

public enum UserDAO {
	instance;
	public ResultSet create(User user) throws SQLException, ClassNotFoundException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO users (username, password, email) VALUES ('"
				+ user.getUsername() + "', '" + user.getPassword() + "', '"
				+ user.getEmail() + "');";
		stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
		conn.close();
		return stmt.getGeneratedKeys();
	}

	public void read(int id) {
	}

	public void update(User user) {
	}

	public void delete(User user) {
	}

	public List<User> index() {
		return null;
	}
}
