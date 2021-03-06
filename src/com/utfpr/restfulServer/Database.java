package com.utfpr.restfulServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum Database {
	INSTANCE; // Singleton de acordo com:
				// http://www.vogella.com/tutorials/DesignPatternSingleton/article.html

	private static String userName = "restfulserver";
	private static String password = "teste@rest";
	private static String dbms = "postgresql";
	private static String portNumber = "5432";
	private static String dbName = "restfulserver";
	private static String hostname = "localhost";

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection conn = null;
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:" + dbms + "://" + hostname
				+ ":" + portNumber + "/" + dbName, userName, password);
		return conn;
	}
}
