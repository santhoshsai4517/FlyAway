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
if (request.getAttribute("bookings") == null) {
	response.sendRedirect("admin_dashboard.jsp");
} else {
	String message = (String) request.getAttribute("message");
	ArrayList<Booking> bookings = (ArrayList<Booking>) request.getAttribute("bookings");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookings</title>
</head>
<body>
	<h1><%=message%></h1>
	<%
	if (!bookings.isEmpty()) {
	%>
	<table>
		<tr>
			<th>Booking ID</th>
			<th>Flight ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Phone Number</th>
			<th>Email</th>
			<th>Date</th>
			<th>Seats</th>
			<th>Price</th>
		</tr>
		<%
		for (Booking booking : bookings) {
		%>
		<tr>
			<td><%=booking.getBookingId()%></td>
			<td><%=booking.getFlightId()%></td>
			<td><%=booking.getFirstName()%></td>
			<td><%=booking.getLastName()%></td>
			<td><%=booking.getNumber()%></td>
			<td><%=booking.getEmail()%></td>
			<td><%=booking.getDate()%></td>
			<td><%=booking.getSeats()%></td>
			<td><%=booking.getPrice()%></td>
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