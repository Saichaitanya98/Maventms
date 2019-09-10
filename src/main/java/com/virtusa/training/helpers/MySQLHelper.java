package com.virtusa.training.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MySQLHelper {
	private static Connection conn;
	private static Logger logger = Logger.getLogger(MySQLHelper.class);

	static {
		PropertyConfigurator.configure("log4j.properties");
	}
//For connecting to the mysql database
	public static Connection getConnection() {
		ResourceBundle rb = ResourceBundle.getBundle("com/virtusa/training/resources/db");
		String userName = rb.getString("user");
		String password = rb.getString("password");
		String url = rb.getString("url");
		String driverName = rb.getString("driver");
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			logger.error("Class not found Exception" + e.getMessage());
		} catch (SQLException e) {
			// e.printStackTrace();
			logger.error("SQL Exception" + e.getMessage());
		}
		return conn;
	}
}
