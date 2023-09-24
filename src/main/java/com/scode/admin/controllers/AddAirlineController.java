package com.scode.admin.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.scode.admin.model.Airline;
import com.scode.admin.model.Place;

/**
 * Servlet implementation class AddPlaceController
 */
@WebServlet("/addairline")
public class AddAirlineController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddAirlineController() {
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
		} else if (request.getParameter("airline") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("admin_dashboard.jsp");
			rd.forward(request, response);
		} else {
			String name = request.getParameter("airline");
			RequestDispatcher rd = request.getRequestDispatcher("admin_result.jsp");
			Airline airline = new Airline();
			airline.setName(name);
			int result = airline.addPlace();
			if (result == -1) {
				request.setAttribute("message", "Airline already exists in database. Please enter a new airline.");
			} else {
				request.setAttribute("message", name + " added succesfully into database");
			}
			airline.destroy();
			rd.forward(request, response);
		}
	}

}
