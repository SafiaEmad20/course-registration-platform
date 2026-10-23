package com.project.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	private static final String CONFIG_FILE = "db.properties";
	private static final Properties props = new Properties();

	static {
		try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
			props.load(in);
		} catch (IOException e) {
			System.out.println("Could not read " + CONFIG_FILE
					+ ". Make sure it is in the project root folder.");
		}
	}

	public static Connection provideConnection() {

		Connection conn = null;

		try {
			Class.forName(props.getProperty("db.driver", "com.mysql.cj.jdbc.Driver"));
			conn = DriverManager.getConnection(
					props.getProperty("db.url"),
					props.getProperty("db.user"),
					props.getProperty("db.password", ""));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}