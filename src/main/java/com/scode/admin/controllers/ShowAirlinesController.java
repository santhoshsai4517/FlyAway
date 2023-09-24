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

/**
 * Servlet implementation class ShowPlacesController
 */
@WebServlet("/showairlines")
public class ShowAirlinesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAirlinesController() {
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
			Airline airline = new Airline();
			ArrayList<String> airlines = airline.getAirlines();
			RequestDispatcher rd = request.getRequestDispatcher("admin_showairlines.jsp");
			if(airlines.isEmpty()) {
				request.setAttribute("message", "No airlines found in database");
			}else {
				request.setAttribute("message", "Found below airlines in database");
			}
			request.setAttribute("airlines", airlines);
			airline.destroy();
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
