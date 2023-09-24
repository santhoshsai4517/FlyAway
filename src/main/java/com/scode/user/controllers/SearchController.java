package com.scode.user.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.scode.user.model.Flight;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("source") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("user_index.jsp");
			rd.forward(request, response);
		}
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		int seats = Integer.parseInt(request.getParameter("seats"));
		String date = request.getParameter("date");
		System.out.println(date);
		Flight flight = new Flight();
		flight.setDestination(destination);
		flight.setDate(date);
		flight.setSource(source);
		flight.setSeats(seats);
		RequestDispatcher rd = request.getRequestDispatcher("user_searchresult.jsp");
		ArrayList<Flight> result = flight.searchFlight();

		if(flight.getCount() == 0 && !result.isEmpty()) {
			request.setAttribute("message", "Not able to find flights for your search. Here are some other flights between "+ source+" and "+destination);
			request.setAttribute("flights", result);
		}else if(result.isEmpty()) {
			request.setAttribute("message", "No flights between "+ source+" and "+destination);
			request.setAttribute("flights", result);
		}else {
			request.setAttribute("message", "Here are some flights between "+ source+" and "+destination);
			request.setAttribute("flights", result);
		}
		if(session.getAttribute("source") == null)
			session.setAttribute("source", source);
		flight.destroy();
		rd.forward(request, response);
		
	}

}
