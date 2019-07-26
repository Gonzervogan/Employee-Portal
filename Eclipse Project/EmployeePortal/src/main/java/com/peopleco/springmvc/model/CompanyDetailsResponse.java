package com.peopleco.springmvc.model;

import java.util.ArrayList;

public class CompanyDetailsResponse {

	private int id;
	private String name;
	private ArrayList<String> address;
	private String logo;
	private int pageViews;
	private ArrayList<String> activeUsers;

	public ArrayList<String> getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(ArrayList<String> activeUsers) {
		this.activeUsers = activeUsers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<String> address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getPageViews() {
		return pageViews;
	}

	public void setPageViews(int pageViews) {
		this.pageViews = pageViews;
	}

}
