package com.utfpr.restfullServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectSetup {
	public static void setupDatabase() throws ClassNotFoundException, SQLException{
		//dropDatabase();
		createDatabase();
	}
	public static void createDatabase() throws ClassNotFoundException, SQLException{
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();
		stmt.execute("CREATE DATABASE restfullserver;");
		conn.close();
		createTables();
	}
	
	public static void dropDatabase() throws SQLException, ClassNotFoundException{
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();
		stmt.execute("DROP DATABASE restfullserver;");
		conn.close();
	}
	
	public static void createTables() throws ClassNotFoundException, SQLException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();
		String createTableUsers = "CREATE TABLE users("
				+ "id serial NOT NULL,"
				+ "username varchar(255) NOT NULL,"
				+ "password varchar(255) NOT NULL,"
				+ "email varchar(255),"
				+ "created_at timestamp without time zone,"
				+ "updated_at timestamp without time zone,"
				+ "CONSTRAINT users_pkey PRIMARY KEY (id));";
		String createTablePosts = "CREATE TABLE posts("
				+ "id serial NOT NULL,"
				+ "user_id integer REFERENCES users (id),"
				+ "title varchar(255) NOT NULL,"
				+ "content text,"
				+ "excerpt varchar(255),"
				+ "created_at timestamp without time zone,"
				+ "updated_at timestamp without time zone,"
				+ "CONSTRAINT posts_pkey PRIMARY KEY (id));";
		String createTableCategories = "CREATE TABLE categories("
				+ "id serial NOT NULL,"
				+ "name varchar(255) NOT NULL,"
				+ "created_at timestamp without time zone,"
				+ "CONSTRAINT categories_pkey PRIMARY KEY (id));";
		String createTablePostsCategories = "CREATE TABLE posts_categories("
				+ "post_id integer REFERENCES posts (id),"
				+ "category_id integer REFERENCES categories (id),"
				+ "created_at timestamp without time zone);";
		
		stmt.execute(createTableUsers + createTablePosts + createTableCategories + createTablePostsCategories);
		conn.close();
	}
}
