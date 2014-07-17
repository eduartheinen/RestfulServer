package com.utfpr.restfulServer.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utfpr.restfulServer.Database;

public enum CategoryDAO {
	instance;
	public ResultSet create(Category category) throws SQLException,
			ClassNotFoundException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "INSERT INTO categories (name) VALUES ('"
				+ category.getName() + "');";

		stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
		conn.close();

		return stmt.getGeneratedKeys();
	}

	public ResultSet read(String id) throws ClassNotFoundException,
			SQLException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "SELECT * FROM categories WHERE id=" + id + " LIMIT 1;";

		stmt.execute(sql);
		conn.close();

		return stmt.getResultSet();
	}

	public void update(Category category) {
	}

	public void delete(Category category) {
	}

	public ResultSet index() throws SQLException, ClassNotFoundException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "SELECT * FROM categories;";

		stmt.execute(sql);
		conn.close();

		return stmt.getResultSet();
	}
}
