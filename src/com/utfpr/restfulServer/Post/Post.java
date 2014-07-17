package com.utfpr.restfulServer.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.utfpr.restfulServer.User.User;

public class Post {
	private String id, title, content, excerpt;
	private User author;

	public Post(String id, String title, String content, String excerpt,
			User author) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.excerpt = excerpt;
		this.author = author;
	}

	public String getId() {
		return title;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String toJSON() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", this.id);
		obj.put("author", this.author.getUsername());
		obj.put("title", this.title);
		obj.put("content", this.content);
		obj.put("excerpt", this.excerpt);
		return obj.toString();
	}

	// CRUD using PostDAO
	public void create() throws ClassNotFoundException, SQLException {
		ResultSet rs = PostDAO.instance.create(this);

		if (rs.next())
			this.setId(rs.getString("id"));
	}

	// static get post by id
	public static Post getById(String id) throws ClassNotFoundException,
			SQLException {
		ResultSet rs = PostDAO.instance.read(id);

		if (rs.next()) {
			Post post = new Post(rs.getString("id"), rs.getString("title"),
					rs.getString("content"), rs.getString("excerpt"),
					User.getById(rs.getString("user_id")));
			return post;
		}

		return null;
	}

	// static index
	public static List<Post> index() throws SQLException,
			ClassNotFoundException {
		ResultSet rs = PostDAO.instance.index();
		List<Post> result = new ArrayList<Post>();

		while (rs.next()) {
			Post post = new Post(rs.getString("id"), rs.getString("title"),
					rs.getString("content"), rs.getString("excerpt"),
					User.getById(rs.getString("user_id")));
			result.add(post);
		}

		return result;
	}
}
