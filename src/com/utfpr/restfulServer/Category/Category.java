package com.utfpr.restfulServer.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Category {
	private String id, name;

	public Category() {
	}

	public Category(String id, String name) {
		this.id = id;
		this.setName(name);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toJSON() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", this.id);
		obj.put("name", this.name);
		return obj.toString();
	}

	// CRUD using CategoryDAO
	public void create() throws ClassNotFoundException, SQLException {
		ResultSet rs = CategoryDAO.instance.create(this);

		if (rs.next())
			this.setId(rs.getString("id"));
	}

	// static get category by id
	public static Category getById(String id) throws ClassNotFoundException,
			SQLException {
		ResultSet rs = CategoryDAO.instance.read(id);

		if (rs.next()) {
			Category category = new Category(rs.getString("id"),
					rs.getString("name"));
			return category;
		}

		return null;
	}

	public static List<Category> getCategoriesByPostId(String id)
			throws SQLException, ClassNotFoundException {
		ResultSet rs = CategoryDAO.instance.findByPostId(id);
		List<Category> result = new ArrayList<Category>();

		while (rs.next()) {
			Category category = new Category(rs.getString("id"),
					rs.getString("name"));
			result.add(category);
		}

		return result;
	}

	// static index
	public static List<Category> index() throws SQLException,
			ClassNotFoundException {
		ResultSet rs = CategoryDAO.instance.index();
		List<Category> result = new ArrayList<Category>();

		while (rs.next()) {
			Category category = new Category(rs.getString("id"),
					rs.getString("name"));
			result.add(category);
		}

		return result;
	}

	public static Category getOrCreateCategory(String name)
			throws ClassNotFoundException, SQLException {
		ResultSet rs = CategoryDAO.instance.findByName(name);
		if (rs.next()) {
			Category category = new Category(rs.getString("id"),
					rs.getString("name"));
			return category;
		}

		Category category = new Category(null, name);
		category.create();
		return category;
	}

	public static void createPostsCategoriesRelationship(String post_id,
			String category_id) throws ClassNotFoundException, SQLException {
		CategoryDAO.instance.createPostsCategoriesRelationship(post_id,
				category_id);
	}
}
