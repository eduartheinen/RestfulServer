package com.utfpr.restfulServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectSetup {
	public static void setupDatabase() throws ClassNotFoundException, SQLException{
		createTables();
	}

	public static void createTables() throws ClassNotFoundException, SQLException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();
		String users = "CREATE TABLE users("
				+ "id serial NOT NULL,"
				+ "username varchar(255) NOT NULL UNIQUE,"
				+ "password varchar(255) NOT NULL,"
				+ "email varchar(255) UNIQUE,"
				+ "created_at timestamp without time zone,"
				+ "updated_at timestamp without time zone,"
				+ "CONSTRAINT users_pkey PRIMARY KEY (id));";
		String posts = "CREATE TABLE posts("
				+ "id serial NOT NULL,"
				+ "user_id integer REFERENCES users (id),"
				+ "title varchar(255) NOT NULL,"
				+ "content text,"
				+ "excerpt varchar(255),"
				+ "created_at timestamp without time zone,"
				+ "updated_at timestamp without time zone,"
				+ "CONSTRAINT posts_pkey PRIMARY KEY (id));";
		String categories = "CREATE TABLE categories("
				+ "id serial NOT NULL,"
				+ "name varchar(255) NOT NULL,"
				+ "created_at timestamp without time zone,"
				+ "CONSTRAINT categories_pkey PRIMARY KEY (id));";
		String posts_categories = "CREATE TABLE posts_categories("
				+ "post_id integer REFERENCES posts (id),"
				+ "category_id integer REFERENCES categories (id),"
				+ "created_at timestamp without time zone);";
		
		stmt.execute(users + posts + categories + posts_categories);
		conn.close();
	}
}
