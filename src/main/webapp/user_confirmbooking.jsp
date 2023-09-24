<%@page import="com.scode.user.model.Flight"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Flight flight = null;
if (session.getAttribute("source") == null || request.getAttribute("flightdetails") == null)
	response.sendRedirect("user_index.jsp");
else {
	flight = (Flight) request.getAttribute("flightdetails");
	session.setAttribute("flight", flight);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirm booking</title>

</head>
<body>
	<% if(flight != null) {%>
	<h1>Confirm booking</h1>
	<form action="confirmbooking" method="post"
		onsubmit="return validateForm()">
		<input type="hidden" name="flight" value="<%=flight%>" />
		<table>
			<tr>
				<td>Flight ID</td>
				<td><input type="text" value=<%=flight.getId()%> readonly /></td>
			</tr>
			<tr>
				<td>Source</td>
				<td><input type="text" value=<%=flight.getSource()%> readonly /></td>
			</tr>
			<tr>
				<td>Destination</td>
				<td><input type="text" value=<%=flight.getDestination()%>
					readonly /></td>
			</tr>
			<tr>
				<td>Airline</td>
				<td><input type="text" value=<%=flight.getAirline()%> readonly /></td>
			</tr>
			<tr>
				<td>Available seats</td>
				<td><input type="text" value=<%=flight.getSeats()%> readonly /></td>
			</tr>
			<tr>
				<td>Date</td>
				<td><input type="text" value=<%=flight.getDate()%> readonly /></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><input type="text" name="firstname"
					placeholder="First Name" required /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastname" placeholder="Last Name"
					required /></td>
			</tr>
			<tr>
				<td>Phone number</td>
				<td><input type="text" name="phno" placeholder="Phone number"
					required /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="email" name="email" placeholder="Email"
					required /></td>
			</tr>
			<tr>
				<td>Confirm number of seats</td>
				<td><input type="number" name="seats" placeholder="Seats"
					id="seats" required /></td>
			</tr>
			<tr>
				<td>Total Price</td>
				<td><input type="number" name="totalprice" id="price" readonly /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="book" /></td>
			</tr>
		</table>

	</form>
	

</body>
<script type="text/javascript">
	let price = document.getElementById("price");
	let seat  = document.getElementById("seats");
	function validateForm() {
		let isValid = true;
		let seats = parseInt(seat.value);
		if (seats <= 0) {
			alert("Seats cannot be less than are equal to 0. Please enter valid number of seats");
			isValid = false;
		}
		if (seats >
<%=flight.getSeats()%>
	) {
			alert("Entered number of seats is greater than available seats");
			isValid = false;
		}

		return isValid;
	}

	document.getElementById("seats").addEventListener('focusout',() => {
		let seats = parseInt(seat.value);
		price.value = seats * <%=flight.getPrice()%>;	
	});
	
</script>
<% } %>
</html>