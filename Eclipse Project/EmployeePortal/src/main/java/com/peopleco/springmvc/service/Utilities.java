package com.peopleco.springmvc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilities {

	static Connection connectToDB() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_portal", "user.employee", "Employee@123");
		} catch (Exception e) {
			System.out.println("Unable to connect to DB");
			e.printStackTrace();
		}
		return connection;
	}

	static void disconnectFromDB(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error while disconnecting from DB");
				e.printStackTrace();
			}
		}
	}
}
