package com.utfpr.restfulServer;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

import com.utfpr.restfulServer.Category.Category;
import com.utfpr.restfulServer.Post.Post;
import com.utfpr.restfulServer.User.User;

@Path("/")
public class Resources {
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_HTML)
	// http://localhost:8080/restfulServer/hello #html
	public String sayHtmlHello() {
		return "<html><body><h1>Hello REST</h1></body></html>";
	}

	@GET
	@Path("users")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/restfulServer/users #json
	public String usersIndex() throws ClassNotFoundException, SQLException,
			JSONException {
		List<User> users = new ArrayList<User>();
		users = User.index();
		String result = "";
		for (User user : users) {
			result += user.toJSON();
		}
		return result;
	}

	@GET
	@Path("users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/restfulServer/users #json
	public String showUser(@PathParam("id") String id)
			throws ClassNotFoundException, SQLException, JSONException {
		User user = User.getById(id);
		return user.toJSON();
	}

	@POST
	@Path("users")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// http://localhost:8080/restfulServer/users #POST
	public void createUser(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("email") String email,
			@Context HttpServletResponse servletResponse) throws IOException,
			ClassNotFoundException, SQLException {

		User user = new User(null, username, password, email);

		user.create();
		servletResponse
				.sendRedirect("http://localhost:8080/restfulServer/users");
	}

	@POST
	@Path("users")
	@Consumes(MediaType.APPLICATION_JSON)
	// http://localhost:8080/restfulServer/users #POST
	public Response createUserJson(User user) {
		Logger.getGlobal()
				.info("Request manipulado em post#user.json: " + user);

		try {
			user.create();
			URI uri = URI.create("http://192.168.0.5:8080/restfulServer/users/"
					+ user.getId());
			return Response.created(uri).entity(user).build();
		} catch (Exception e) {
			return Response.noContent().build();
		}
	}

	@GET
	@Path("posts")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/restfulServer/posts #json
	public String postsIndex() throws ClassNotFoundException, SQLException,
			JSONException {
		List<Post> posts = new ArrayList<Post>();
		posts = Post.index();
		String result = "";
		for (Post post : posts) {
			result += post.toJSON();
		}

		return result;

		// List<Category> categories = new ArrayList<Category>();
		// for (int i = 0; i < 3; i++) {
		// Category cat = new Category(null, "cat" + i);
		// categories.add(cat);
		// }
		//
		// Post post = new Post(
		// null,
		// "teste",
		// "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce eros purus, interdum eget nisi quis, hendrerit vestibulum libero. Nulla facilisi. Nulla faucibus volutpat consequat. Vivamus euismod, augue scelerisque aliquet venenatis, mi est sodales nunc, ac condimentum nisl nisi a nisi. Nulla sed arcu lorem.",
		// null, categories);
		// return post.toJSON();
	}

	@GET
	@Path("posts/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/restfulServer/users #json
	public String showPost(@PathParam("id") String id)
			throws ClassNotFoundException, SQLException, JSONException {
		Post post = Post.getById(id);
		return post.toJSON();
	}

	// @POST
	// @Path("posts")
	// @Produces(MediaType.TEXT_HTML)
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// // http://localhost:8080/restfulServer/users #POST
	// public void createPost(@FormParam("title") String title,
	// @FormParam("content") String content,
	// @FormParam("excerpt") String excerpt,
	// @FormParam("user_id") String user_id,
	// @FormParam("categories") String categories,
	// @Context HttpServletResponse servletResponse) throws IOException,
	// ClassNotFoundException, SQLException {
	//
	// List<Category> categories_list = new ArrayList<Category>();
	//
	// for (String name : categories.split(",")) {
	// Category cat = Category.getOrCreateCategory(name.replaceAll("\\s",
	// ""));
	// categories_list.add(cat);
	// }
	//
	// Post post = new Post(null, title, content, User.getById(user_id),
	// categories_list);
	//
	// post.create();
	// servletResponse
	// .sendRedirect("http://localhost:8080/restfulServer/posts");
	// }

	@POST
	@Path("posts")
	@Consumes(MediaType.APPLICATION_JSON)
	// http://localhost:8080/restfulServer/users #POST
	public Response createPostJson(String request) {
		Logger.getGlobal().info(
				"Request manipulado em posts.json: post#" + request);
		return null;

		// try {
		// post.create();
		// URI uri = URI.create("http://192.168.0.5:8080/restfulServer/posts/"
		// + post.getId());
		// return Response.created(uri).entity(post).build();
		// } catch (ClassNotFoundException | SQLException e) {
		// return Response.noContent().build();
		// }
	}

	@GET
	@Path("categories")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/restfulServer/categories #json
	public String categoriesIndex() throws ClassNotFoundException,
			SQLException, JSONException {
		List<Category> categories = new ArrayList<Category>();
		categories = Category.index();
		String result = "";
		for (Category category : categories) {
			result += category.toJSON();
		}

		return result;
	}

	@GET
	@Path("categories/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/restfulServer/users #json
	public String showCategory(@PathParam("id") String id)
			throws ClassNotFoundException, SQLException, JSONException {
		// Category category = Category.getById(id);
		// return category.toJSON();
		return null;
	}

	@GET
	@Path("categories/{id}/posts")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/restfulServer/users #json
	public String postsbyCategoryId(@PathParam("id") String id)
			throws ClassNotFoundException, SQLException, JSONException {
		List<Post> posts = new ArrayList<Post>();
		posts = Post.getPostsByCategoryId(id);
		String result = "";
		for (Post post : posts) {
			result += post.toJSON();
		}

		return result;
	}

	@POST
	@Path("categories")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// http://localhost:8080/restfulServer/users #POST
	public void createCategory(@FormParam("name") String name,
			@Context HttpServletResponse servletResponse) throws IOException,
			ClassNotFoundException, SQLException {

		Category category = new Category(null, name);

		category.create();
		servletResponse
				.sendRedirect("http://localhost:8080/restfulServer/categories");
	}

	@GET
	@Path("setup")
	@Produces(MediaType.TEXT_HTML)
	// http://localhost:8080/restfulServer/setup #html
	public String setup() {
		try {
			ProjectSetup.setupDatabase();
			return "<html><body><p> success! </p></body></html>";
		} catch (Exception e) {
			return "<html><body><p> error: " + e.getMessage()
					+ "</p></body></html>";
		}
	}
}
