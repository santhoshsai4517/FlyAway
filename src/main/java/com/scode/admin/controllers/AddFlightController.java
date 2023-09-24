package com.scode.admin.controllers;

import java.io.IOException;

import com.scode.admin.model.Flight;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AddFlightController
 */
@WebServlet("/addflight")
public class AddFlightController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFlightController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("isValid") == null || session.getAttribute("isValid") == "false") {
			RequestDispatcher rd = request.getRequestDispatcher("admin_index.jsp");
			rd.forward(request, response);
		} else if (request.getParameter("flightid") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("admin_dashboard.jsp");
			rd.forward(request, response);
		} else {
			String id = request.getParameter("flightid");
			String source = request.getParameter("source");
			String destination = request.getParameter("destination");
			String airline = request.getParameter("airline");
			int seats = Integer.parseInt(request.getParameter("seats"));
			String date = request.getParameter("date");
			double price = Double.parseDouble(request.getParameter("price"));

			Flight flight = new Flight();
			flight.setId(id);
			flight.setSource(source);
			flight.setDestination(destination);
			flight.setAirline(airline);
			flight.setSeats(seats);
			flight.setDate(date);
			flight.setPrice(price);

			RequestDispatcher rd = request.getRequestDispatcher("admin_result.jsp");

			if (source == destination) {
				request.setAttribute("message",
						"Source and destination are same. Please enter different source and destination");
			}
			int result = flight.addFlight();
			if (result == -1) {
				request.setAttribute("message", "Airline " + airline
						+ " does not exist in database. Please use existing airline or create a new airline");
			}
			if (result == -2) {
				request.setAttribute("message", "Source " + source
						+ " does not exist in database. Please use existing places or create a new place");
			}
			if (result == -3) {
				request.setAttribute("message", "Destination " + destination
						+ " does not exist in database. Please use existing places or create a new place");
			}
			if (result == -4) {
				request.setAttribute("message",
						"Flight id " + id + " already exists in database. Please use new flight id");
			}
			if (result > 0) {
				request.setAttribute("message", "Flight " + flight.toString() + " created successfully");

			}
			flight.destroy();
			rd.forward(request, response);
//		System.out.println(request.getParameter("date"));
//		System.out.println(flight.toString());
		}
	}

}
