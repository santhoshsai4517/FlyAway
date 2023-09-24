package com.scode.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.scode.util.ConnectionUtil;

public class Place {

	private String name;

	private Connection con;
	private PreparedStatement stmt, stmt1,stmt2;

	public Place() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("select * from places where place = ?");
			stmt1 = con.prepareStatement("insert into places values(?)");
			stmt2 = con.prepareStatement("select * from places");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public int addPlace() {
		int result = 0;
		try {
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = -1;
			} else {
				stmt1.setString(1, name);
				result = stmt1.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> getPlaces() {
		ArrayList<String> places = new ArrayList<String>();
		try {
			ResultSet rs = stmt2.executeQuery();
			while(rs.next())
				places.add(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return places;
	}

	public void destroy() {
		try {
			con.close();
			stmt.close();
			stmt1.close();
			stmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
