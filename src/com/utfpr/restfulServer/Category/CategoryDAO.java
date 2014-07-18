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

	public ResultSet findByName(String name) throws ClassNotFoundException,
			SQLException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "SELECT * FROM categories where name='" + name + "';";

		stmt.execute(sql);
		conn.close();

		return stmt.getResultSet();
	}

	public ResultSet findByPostId(String id) throws ClassNotFoundException,
			SQLException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "SELECT * FROM categories, posts_categories WHERE categories.id = posts_categories.category_id AND posts_categories.post_id ="
				+ id + ";";

		stmt.execute(sql);
		conn.close();

		return stmt.getResultSet();
	}

	public ResultSet createPostsCategoriesRelationship(String post_id,
			String category_id) throws ClassNotFoundException, SQLException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "INSERT INTO posts_categories (post_id, category_id) VALUES ("
				+ post_id  + ", " + category_id + ");";

		stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
		conn.close();

		return stmt.getGeneratedKeys();
	}
}
