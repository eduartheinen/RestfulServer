package com.utfpr.restfullServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum Database {
	INSTANCE;

	private static String userName = "restfullserver";
	private static String password = "teste@rest";
	private static String dbms = "postgresql";
	private static String portNumber = "5432";
	private static String dbName = "restfullserver";
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
