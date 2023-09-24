package com.scode.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.scode.util.ConnectionUtil;

public class Booking {

	private String bookingId;
	private String flightId;
	private String firstName;
	private String lastName;
	private String number;
	private String email;
	private String date;
	private int seats;
	private double price;

	private Connection con;
	private PreparedStatement stmt, stmt1,stmt2;

	public Booking() {
		con = ConnectionUtil.getConnection();
		try {
			stmt = con.prepareStatement("insert into bookings values (?,?,?,?,?,?,?,?,?)");
			stmt1 = con.prepareStatement("update flights set seats = ? where flightid= ?");
			stmt2 = con.prepareStatement("select * from bookings");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", flightId=" + flightId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", number=" + number + ", email=" + email + ", date=" + date + ", seats=" + seats
				+ ", price=" + price + "]";
	}

	public int saveBooking() {
		int result = 0;
		try {
			stmt.setString(1, bookingId);
			stmt.setString(2, flightId);
			stmt.setString(3, firstName);
			stmt.setString(4, lastName);
			stmt.setString(5, number);
			stmt.setString(6, email);
			stmt.setString(7, date);
			stmt.setInt(8, seats);
			stmt.setDouble(9, price);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateSeats(int s) {
		int remainingSeats = s - seats;
		int result = 0;
		try {
			stmt1.setInt(1, remainingSeats);
			stmt1.setString(2, flightId);
			result = stmt1.executeUpdate();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Booking> getBookings() {
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		try {
			ResultSet rs = stmt2.executeQuery();
			while(rs.next()) {
				Booking booking = new Booking();
				booking.setBookingId(rs.getString(1));
				booking.setFlightId(rs.getString(2));
				booking.setFirstName(rs.getString(3));
				booking.setLastName(rs.getString(4));
				booking.setNumber(rs.getString(5));
				booking.setEmail(rs.getString(6));
				booking.setDate(rs.getString(7));
				booking.setSeats(rs.getInt(8));
				booking.setPrice(rs.getDouble(9));
				bookings.add(booking);
				booking.destroy();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return bookings;
	}

}
