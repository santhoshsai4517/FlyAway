package com.scode.admin.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.scode.admin.model.Airline;
import com.scode.admin.model.Place;
import com.scode.user.model.Booking;

/**
 * Servlet implementation class ShowPlacesController
 */
@WebServlet("/showbookings")
public class ShowBookingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBookingsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("isValid") == null || session.getAttribute("isValid") == "false") {
			RequestDispatcher rd = request.getRequestDispatcher("admin_index.jsp");
			rd.forward(request, response);
		}else {
			Booking booking = new Booking();
			ArrayList<Booking> bookings = booking.getBookings();
			RequestDispatcher rd = request.getRequestDispatcher("admin_showbookings.jsp");
			if(bookings.isEmpty()) {
				request.setAttribute("message", "No bookings found in database");
			}else {
				request.setAttribute("message", "Found below bookings in database");
			}
			request.setAttribute("bookings", bookings);
			booking.destroy();
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
