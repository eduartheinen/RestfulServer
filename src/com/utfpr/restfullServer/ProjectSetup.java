package com.utfpr.restfullServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectSetup {
	public static void createTables() throws ClassNotFoundException, SQLException {
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE posts("
				+ "id serial NOT NULL,"
				//+ "user_id integer REFERENCES users (id),"
				+ "title varchar(255) NOT NULL DEFAULT ''::character varying,"
				+ "content text DEFAULT ''::character varying,"
				+ "excerpt varchar(255) DEFAULT ''::character varying,"
				+ "created_at timestamp without time zone,"
				+ "updated_at timestamp without time zone,"
				+ "CONSTRAINT posts_pkey PRIMARY KEY (id));";
		
		stmt.execute(sql);
	}
	
	public static void dropTables() throws ClassNotFoundException, SQLException{
		
		Connection conn = Database.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "DROP TABLE posts;";
		
		stmt.execute(sql);
	} 
}
