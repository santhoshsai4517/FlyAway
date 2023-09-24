package com.scode.user.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.scode.user.model.Flight;

/**
 * Servlet implementation class BookController
 */
@WebServlet("/book")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookController() {
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
		if (session.getAttribute("source") == null || request.getParameter("bookingflightid") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("user_index.jsp");
			rd.forward(request, response);
		}

		String flightid = request.getParameter("bookingflightid");
		Flight flight = new Flight();
		Flight result = flight.searchFlight(flightid);
		RequestDispatcher rd = request.getRequestDispatcher("user_confirmbooking.jsp");
		request.setAttribute("flightdetails", result);
		flight.destroy();
		rd.forward(request, response);
	}

}
