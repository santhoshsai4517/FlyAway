<%@page import="com.scode.user.model.Flight"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("source") == null)
		response.sendRedirect("user_index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flights</title>
</head>
<body>
	<%
	ArrayList<Flight> flights = (ArrayList<Flight>) request.getAttribute("flights");
	String message = (String) request.getAttribute("message");
	if(flights != null){
	%>
	<h1><%=message%></h1>
	<form action="book" method="post">
		<table border="1">
			<thead>
				<tr>
					<th>FlightId</th>
					<th>Airline</th>
					<th>Available seats</th>
					<th>Date</th>
					<th>Price per seat</th>
					<th></th>
				</tr>
			</thead>
			<%
			if (!flights.isEmpty()) {
				for (Flight flight : flights) {
			%>
			<tr>
				<td><%=flight.getId()%></td>
				<td><%=flight.getAirline()%></td>
				<td><%=flight.getSeats()%></td>
				<td><%=flight.getDate()%></td>
				<td><%=flight.getPrice()%></td>
				<td><input type="submit" value="Book flight" /><input
					type="hidden" value=<%=flight.getId()%> name="bookingflightid" /></td>
			</tr>
			<%
			}
			}
			%>
		</table>
	</form>
	<a href="user_index.jsp">Go to search</a>
	<%} %>

</body>
</html>