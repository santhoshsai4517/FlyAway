package com.scode.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.scode.util.ConnectionUtil;

public class Flight {
	private String id;
	private String source;
	private String destination;
	private String airline;
	private int seats;
	private String date;
	private double price;
	private int count;

	private Connection con;
	private PreparedStatement stmt, stmt1, stmt2, stmt3;

	public Flight() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement(
					"select * from flights where source = ? and destination = ? and date = ? and seats >= ?");
			stmt1 = con.prepareStatement("select * from flights where source = ? and destination = ?");
			stmt2 = con.prepareStatement(
					"select count(*) as count from flights where source = ? and destination = ? and date = ? and seats >= ?");
			stmt3 = con.prepareStatement("select * from flights where flightid = ?");
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

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", source=" + source + ", destination=" + destination + ", airline=" + airline
				+ ", seats=" + seats + ", date=" + date + ", price=" + price + "]";
	}

	public int getCount() {
		return count;
	}

	public ArrayList<Flight> searchFlight() {
		ArrayList<Flight> flightList = new ArrayList<Flight>();
		try {
			stmt2.setString(1, source);
			stmt2.setString(2, destination);
			stmt2.setString(3, date);
			stmt2.setInt(4, seats);
			ResultSet rs = stmt2.executeQuery();
			rs.next();
			count = rs.getInt(1);
			if (count > 0) {
				stmt.setString(1, source);
				stmt.setString(2, destination);
				stmt.setString(3, date);
				stmt.setInt(4, seats);
				rs = stmt.executeQuery();
			} else {
				stmt1.setString(1, source);
				stmt1.setString(2, destination);
				rs = stmt1.executeQuery();
			}
			while (rs.next()) {
				Flight flight = new Flight();
				flight.setSource(source);
				flight.setDestination(destination);
				flight.setDate(rs.getString(6));
				flight.setAirline(rs.getString(3));
				flight.setId(rs.getString(1));
				flight.setSeats(rs.getInt(5));
				flight.setPrice(rs.getDouble(7));
				flightList.add(flight);
				flight.destroy();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flightList;
	}
	
	public Flight searchFlight(String id) {
		Flight flight = new Flight();
		try {
			stmt3.setString(1, id);
			ResultSet rs = stmt3.executeQuery();
			rs.next();
			flight.setId(id);
			flight.setSource(rs.getString(2));
			flight.setDestination(rs.getString(3));
			flight.setAirline(rs.getString(4));
			flight.setSeats(rs.getInt(5));
			flight.setDate(rs.getString(6));
			flight.setPrice(rs.getDouble(7));
			flight.destroy();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flight;
	}

	public void destroy() {
		try {
			con.close();
			stmt.close();
			stmt1.close();
			stmt2.close();
			stmt3.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
