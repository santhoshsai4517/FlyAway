<%@page import="com.scode.user.model.Booking"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("source") == null)
	response.sendRedirect("user_index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirm page</title>
</head>
<body>
	<h1 id="payment">Processing payment</h1>
	<div id="container" style="visibility: hidden">
		<%
		String id = (String) request.getAttribute("messageid");
		String message = (String) request.getAttribute("message");
		Booking booking = (Booking) request.getAttribute("booking");
		%>
		<h1><%=message%></h1>
		<%
		if (id == "1") {
		%>
		<table>
			<tr>
				<td>Booking ID</td>
				<td><%=booking.getBookingId()%></td>
			</tr>
			<tr>
				<td>Flight ID</td>
				<td><%=booking.getFlightId()%></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><%=booking.getFirstName()%></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><%=booking.getLastName()%></td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td><%=booking.getNumber()%></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><%=booking.getEmail()%></td>
			</tr>
			<tr>
				<td>Date</td>
				<td><%=booking.getDate()%></td>
			</tr>
			<tr>
				<td>Seats</td>
				<td><%=booking.getSeats()%></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><%=booking.getPrice()%></td>
			</tr>
			<tr>
				<td colspan="2"><a href="user_index.jsp">Go to search</a></td>
			</tr>
		</table>
		<%
		}
		%>
	</div>
</body>
<script>
let heading = document.getElementById("payment");
let container = document.getElementById("container");
const myTimeout = setTimeout(() => {
	heading.style.visibility = "hidden";
	
	container.style.visibility = "visible";
}, 5000);

</script>
</html>