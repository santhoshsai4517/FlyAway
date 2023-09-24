package com.scode.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.scode.util.ConnectionUtil;


public class Flight {
	private String id;
	private String source;
	private String destination;
	private String airline;
	private int seats;
	private String date;
	private double price;

	private Connection con;
	private PreparedStatement stmt,stmt1,stmt2,stmt3,stmt4;
	
	public Flight() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("select * from airline where airlinename = ?");
			stmt1 = con.prepareStatement("select * from places where place = ?");
			stmt2 = con.prepareStatement("select * from flights where flightid = ?");
			stmt3 = con.prepareStatement("insert into flights values(?,?,?,?,?,?,?)");
			stmt4 = con.prepareStatement("select * from flights");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	
	
	@Override
	public String toString() {
		return "Flight [id=" + id + ", source=" + source + ", destination=" + destination + ", airline=" + airline
				+ ", seats=" + seats + ", date=" + date + ", price=" + price + "]";
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int addFlight() {
		int result = 0;
		try {
			stmt.setString(1, airline);
			ResultSet rs = stmt.executeQuery();
			if(!rs.next())
				return -1;
			stmt1.setString(1, source);
			rs = stmt1.executeQuery();
			if(!rs.next())
				return -2;
			stmt1.setString(1, destination);
			rs = stmt1.executeQuery();
			if(!rs.next())
				return -3;
			stmt2.setString(1, id);
			rs = stmt2.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString(1));
				return -4;
			}

			stmt3.setString(1, id);
			stmt3.setString(2, source);
			stmt3.setString(3, destination);
			stmt3.setString(4, airline);
			stmt3.setInt(5, seats);
			stmt3.setString(6, date);
			stmt3.setDouble(7, price);
			result = stmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public void destroy() {
		try {
			con.close();
			stmt.close();
			stmt1.close();
			stmt2.close();
			stmt3.close();
			stmt4.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Flight> getBookings() {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		try {
			ResultSet rs = stmt4.executeQuery();
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setId(rs.getString(1));
				flight.setSource(rs.getString(2));
				flight.setDestination(rs.getString(3));
				flight.setAirline(rs.getString(4));
				flight.setSeats(rs.getInt(5));
				flight.setDate(rs.getString(6));
				flight.setPrice(rs.getDouble(7));
				flights.add(flight);
				flight.destroy();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}
}
