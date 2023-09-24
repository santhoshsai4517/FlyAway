package com.scode.user.controllers;

import java.io.IOException;
import java.util.Random;

import com.scode.user.model.Booking;
import com.scode.user.model.Flight;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ConfirmBookingController
 */
@WebServlet("/confirmbooking")
public class ConfirmBookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmBookingController() {
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
//		String flight = request.getParameter("flight");
	    Flight flight = (Flight) request.getSession().getAttribute("flight");
		HttpSession session = request.getSession();
		if (session.getAttribute("source") == null || request.getParameter("firstname") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("user_index.jsp");
			rd.forward(request, response);
		}
	    Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 10;
        StringBuilder randomText = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            randomText.append(characters.charAt(randomIndex));
        }
        
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phno = request.getParameter("phno");
        String email = request.getParameter("email");
        int seats = Integer.parseInt(request.getParameter("seats"));
        double price = seats * flight.getPrice();
        
        Booking booking = new Booking();
        booking.setBookingId(randomText.toString());
        booking.setFlightId(flight.getId());
        booking.setFirstName(firstname);
        booking.setLastName(lastname);
        booking.setNumber(phno);
        booking.setEmail(email);
        booking.setDate(flight.getDate());
        booking.setSeats(seats);
        booking.setPrice(price);
        int result = booking.saveBooking();
        booking.updateSeats(flight.getSeats());
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.print("<h1>Processing payment request</h1>");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        RequestDispatcher rd = request.getRequestDispatcher("user_bookingresult.jsp");
        if(result > 0) {
        	request.setAttribute("messageid", "1");
        	request.setAttribute("booking", booking);
        	request.setAttribute("message", "Flight booked. Find the details below");
        }else {
        	request.setAttribute("messageid", "-1");
        	request.setAttribute("message", "Some error occured. Please try again later");
        }
        booking.destroy();
        rd.forward(request, response);
//		System.out.println(booking.toString());
	}

}
