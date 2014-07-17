package com.utfpr.restfulServer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

import org.codehaus.jettison.json.JSONException;

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
	public String usersIndex() throws ClassNotFoundException, SQLException, JSONException {
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
	public String showUser(@PathParam("id") String id) throws ClassNotFoundException, SQLException, JSONException {
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
	
	@GET
	@Path("posts")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/restfulServer/posts #json
	public String postsIndex() throws ClassNotFoundException, SQLException, JSONException {
		List<Post> posts = new ArrayList<Post>();
		posts = Post.index();
		String result = "";
		for (Post post : posts) {
			result += post.toJSON();
		}

		return result;
	}
	
	@GET
	@Path("posts/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	// http://localhost:8080/restfulServer/users #json
	public String showPost(@PathParam("id") String id) throws ClassNotFoundException, SQLException, JSONException {
		Post post = Post.getById(id);
		return post.toJSON();
	}

	@POST
	@Path("posts")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// http://localhost:8080/restfulServer/users #POST
	public void createPost(@FormParam("title") String title,
			@FormParam("content") String content,
			@FormParam("excerpt") String excerpt,
			@FormParam("user_id") String user_id,
			@Context HttpServletResponse servletResponse) throws IOException,
			ClassNotFoundException, SQLException {

		Post post = new Post(null, title, content, excerpt, User.getById(user_id));

		post.create();
		servletResponse
				.sendRedirect("http://localhost:8080/restfulServer/posts");
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
