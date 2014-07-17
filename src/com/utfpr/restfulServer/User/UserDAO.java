package com.utfpr.restfulServer.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.utfpr.restfulServer.Database;
import com.utfpr.restfulServer.User.User;

public enum UserDAO {
	instance;
	public ResultSet create(User user) throws SQLException,
			ClassNotFoundException {
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

	public List<User> index() throws SQLException, ClassNotFoundException {

		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM users;";
		stmt.execute(sql);
		conn.close();

		ResultSet rs = stmt.getResultSet();
		List<User> result = new ArrayList<User>();
		while (rs.next()) {
			User user = new User(rs.getString("id"), rs.getString("username"), rs.getString("password"), rs.getString("email"));
			result.add(user);
		}
		return result;
	}
}
