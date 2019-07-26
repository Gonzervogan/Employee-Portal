package com.peopleco.springmvc.service;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peopleco.springmvc.model.CompanyDetailsResponse;

@Service("companyService")
@Transactional
public class CompanyService {

	static void incrementPageViews(Connection connection, String username) throws SQLException
	{
		try {
		Statement stmt=connection.createStatement();
		int count=stmt.executeUpdate("UPDATE companies SET page_Views=page_Views+1 WHERE id=(SELECT company_id FROM employees WHERE username='"+username+"')");
		if(count>1)
		{
			System.out.println("More rows found!");
			throw new SQLException();
		}
		if(count==0)
		{
			System.out.println("No entry found!");
			throw new SQLException();
		}
		}
		catch(SQLException e)
		{
			System.out.println("Error in incrementing page views of "+username+"'s company");
			throw e;
		}
	}
	
	static CompanyDetailsResponse getCompanyDetails(Connection connection, String username) throws SQLException
	{
		try {
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT companies.id as id, companies.company_name as company_name, companies.address_line_1 as address_line_1, companies.address_line_2 as address_line_2, companies.zip as zip, companies.logo as logo, companies.page_views as page_views FROM companies INNER JOIN employees ON employees.company_id=companies.id WHERE employees.username='"+username+"'");
			CompanyDetailsResponse resp=new CompanyDetailsResponse();
			int count=0;
			while(rs.next())
			{
				resp.setId(rs.getInt("id"));
				resp.setName(rs.getString("company_name"));
				resp.setLogo(rs.getString("logo"));
				resp.setPageViews(rs.getInt("page_views"));
				String addressLine1=rs.getString("address_line_1");
				String addressLine2=rs.getString("address_line_2");
				String zip=rs.getString("zip");
				ArrayList<String> address=new ArrayList<String>();
				if(addressLine1!=null)
					address.add(addressLine1);
				if(addressLine2!=null)
					address.add(addressLine2);
				if(zip!=null)
					address.add(zip);
				resp.setAddress(address);
				count++;
			}
			if(count>1)
			{
				System.out.println("More rows found!");
				throw new SQLException();
			}
			if(count==0)
			{
				System.out.println("No entry found!");
				throw new SQLException();
			}
			return resp;
		}
		catch(SQLException e)
		{
			System.out.println("Error in getting company details of "+username);
			throw e;
		}
	}
	
	public CompanyDetailsResponse getCompanyDetailsResponse(String username)
	{
		Connection connection=null;
		try
		{
			connection=Utilities.connectToDB();
			incrementPageViews(connection,username);
			CompanyDetailsResponse resp=getCompanyDetails(connection,username);
			EmployeeService.setEmployeeActivityStatus(connection, username, 3);
			resp.setActiveUsers(EmployeeService.getActiveUsers(connection, resp.getId()));
			return resp;
		}
		catch(Exception e)
		{
			return null;
		}
		finally {
			Utilities.disconnectFromDB(connection);
		}
	}
}
