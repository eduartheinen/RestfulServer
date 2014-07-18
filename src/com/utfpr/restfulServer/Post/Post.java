package com.utfpr.restfulServer.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.utfpr.restfulServer.Category.Category;
import com.utfpr.restfulServer.User.User;

public class Post {
	private String id, title, content, excerpt;
	private User author;
	private List<Category> categories;

	public Post(String id, String title, String content, User author,
			List<Category> categories) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.excerpt = content.substring(0, Math.min(content.length(), 255));
		this.author = author;
		this.setCategories(categories);
	}

	public String getId() {
		return id;
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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String toJSON() throws JSONException {
		JSONObject obj = new JSONObject();
		JSONObject categories = new JSONObject();

		for (Category category : this.categories) {
			categories.put(category.getId(), category.getName());
		}

		obj.put("id", this.id);
		obj.put("author", this.author.getUsername());
		obj.put("title", this.title);
		obj.put("content", this.content);
		obj.put("excerpt", this.excerpt);
		obj.put("categories", categories);

		return obj.toString();
	}

	// CRUD using PostDAO
	public void create() throws ClassNotFoundException, SQLException {
		ResultSet rs = PostDAO.instance.create(this);

		if (rs.next())
			this.setId(rs.getString("id"));

		for (Category category : categories) {
			Category.createPostsCategoriesRelationship(this.id,
					category.getId());
		}
	}

	// static get post by id
	public static Post getById(String id) throws ClassNotFoundException,
			SQLException {
		ResultSet rs = PostDAO.instance.read(id);

		if (rs.next()) {
			Post post = new Post(rs.getString("id"), rs.getString("title"),
					rs.getString("content"), User.getById(rs
							.getString("user_id")),
					Category.getCategoriesByPostId(rs.getString("id")));
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
					rs.getString("content"), User.getById(rs
							.getString("user_id")),
					Category.getCategoriesByPostId(rs.getString("id")));

			result.add(post);
		}

		return result;
	}
}
