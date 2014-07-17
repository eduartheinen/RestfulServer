package com.utfpr.restfulServer.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utfpr.restfulServer.Database;

public enum PostDAO {
	instance;
	public ResultSet create(Post post) throws SQLException,
			ClassNotFoundException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "INSERT INTO posts (user_id, title, content, excerpt) VALUES ('"
				+ post.getAuthor().getId()
				+ "', '"
				+ post.getTitle()
				+ "', '"
				+ post.getContent() + "', '" + post.getExcerpt() + "');";

		stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
		conn.close();

		return stmt.getGeneratedKeys();
	}

	public ResultSet read(String id) throws ClassNotFoundException,
			SQLException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "SELECT * FROM posts WHERE id=" + id + " LIMIT 1;";

		stmt.execute(sql);
		conn.close();

		return stmt.getResultSet();
	}

	public void update(Post post) {
	}

	public void delete(Post post) {
	}

	public ResultSet index() throws SQLException, ClassNotFoundException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();

		String sql = "SELECT * FROM posts;";

		stmt.execute(sql);
		conn.close();

		return stmt.getResultSet();
	}
}
