<%@page import="com.scode.admin.model.Flight"%>
<%@page import="com.scode.user.model.Booking"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("isValid") == null)
	response.sendRedirect("admin_index.jsp");
else {
	boolean isValid = (Boolean) session.getAttribute("isValid");

	if (!isValid)
		response.sendRedirect("admin_index.jsp");
}
if (request.getAttribute("flights") == null) {
	response.sendRedirect("admin_dashboard.jsp");
} else {
	String message = (String) request.getAttribute("message");
	ArrayList<Flight> flights = (ArrayList<Flight>) request.getAttribute("flights");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flights</title>
</head>
<body>
	<h1><%=message%></h1>
	<%
	if (!flights.isEmpty()) {
	%>
	<table>
		<tr>
			<th>Flight ID</th>
			<th>Source</th>
			<th>Destination</th>
			<th>Airline</th>
			<th>seats</th>
			<th>Date</th>
			<th>Price</th>
		</tr>
		<%
		for (Flight flight : flights) {
		%>
		<tr>
			<td><%=flight.getId()%></td>
			<td><%=flight.getSource()%></td>
			<td><%=flight.getDestination()%></td>
			<td><%=flight.getAirline()%></td>
			<td><%=flight.getSeats()%></td>
			<td><%=flight.getDate()%></td>
			<td><%=flight.getPrice()%></td>
		</tr>
		<%
		}
		%>
	</table>
	<a href="admin_dashboard.jsp">Go to dashboard</a>
</body>
</html>
<%
}
}
%>