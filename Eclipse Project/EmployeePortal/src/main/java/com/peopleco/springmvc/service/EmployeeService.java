package com.peopleco.springmvc.service;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peopleco.springmvc.model.ProfileResponse;

@Service("employeeService")
@Transactional
public class EmployeeService {

	static void setEmployeeActivityStatus(Connection connection, String username, int status) throws SQLException {
		try {
			Statement stmt = connection.createStatement();
			if (stmt.executeUpdate(
					"UPDATE employees SET activity=" + status + ", last_updated=CURRENT_TIMESTAMP WHERE username='" + username + "'") != 1)
				throw new SQLException();
		} catch (SQLException e) {
			System.out.println("Error in setting activity status of " + username);
			throw e;
		}
	}

	static ProfileResponse getProfile(Connection connection, String username) throws SQLException {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT employees.first_name as first_name, employees.last_name as last_name, employees.job as job, employees.image as image, companies.id as id, companies.company_name as company_name FROM employees INNER JOIN companies ON employees.company_id=companies.id WHERE employees.username='"
							+ username + "'");
			ProfileResponse resp = new ProfileResponse();
			resp.setUsername(username);
			int count = 0;
			while (rs.next()) {
				resp.setFirstName(rs.getString("first_name"));
				resp.setLastName(rs.getString("last_name"));
				resp.setJob(rs.getString("job"));
				resp.setImage(rs.getString("image"));
				resp.setCompanyId(rs.getInt("id"));
				resp.setCompanyName(rs.getString("company_name"));
				count++;
			}
			if (count > 1) {
				System.out.println("Multiple rows found!");
				throw new SQLException();
			} else if (count == 0) {
				System.out.println("No rows found!");
				throw new SQLException();
			}
			return resp;
		} catch (SQLException e) {
			System.out.println("Error in getting profile of " + username);
			throw e;
		}
	}

	static ArrayList<String> getActiveUsers(Connection connection, int companyId) throws SQLException {
		try {
			Statement stmt = connection.createStatement();
			int timeout = 600;
			ResultSet rs = stmt
					.executeQuery("SELECT first_name, last_name FROM employees WHERE activity=3 AND company_id="
							+ companyId + " AND TIMESTAMPDIFF(SECOND, last_updated, CURRENT_TIMESTAMP)<" + timeout
							+ " ORDER BY first_name, last_name");
			ArrayList<String> ret = new ArrayList<String>();
			while (rs.next())
				ret.add(rs.getString("first_name") + " " + rs.getString("last_name"));
			return ret;
		} catch (SQLException e) {
			System.out.println("Error in getting active users of company" + companyId);
			throw e;
		}
	}

	static boolean checkPassword(Connection connection, String username, String password) throws SQLException {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT _password FROM employees WHERE username='" + username + "'");
			boolean resp = false;
			int count = 0;
			while (rs.next()) {
				resp = rs.getString("_password").equals(password);
				count++;
			}
			if (count > 1) {
				System.out.println("Multiple rows found!");
				throw new SQLException();
			} else if (count == 0) {
				System.out.println("No rows found!");
				throw new SQLException();
			}
			return resp;
		} catch (SQLException e) {
			System.out.println("Error in fetching password of " + username);
			throw e;
		}
	}

	public ProfileResponse getProfileResponse(String username) {
		Connection connection = null;
		try {
			connection = Utilities.connectToDB();
			setEmployeeActivityStatus(connection, username, 2);
			return getProfile(connection, username);
		} catch (Exception e) {
			return null;
		} finally {
			Utilities.disconnectFromDB(connection);
		}
	}

	public boolean authenticateLogin(String username, String password) {
		Connection connection = null;
		try {
			connection = Utilities.connectToDB();
			if(!checkPassword(connection, username, password))
				return false;
			setEmployeeActivityStatus(connection,username,1);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			Utilities.disconnectFromDB(connection);
		}
	}
	
	public boolean logout(String username) {
		Connection connection = null;
		try {
			connection = Utilities.connectToDB();
			setEmployeeActivityStatus(connection,username,0);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			Utilities.disconnectFromDB(connection);
		}
	}
}
