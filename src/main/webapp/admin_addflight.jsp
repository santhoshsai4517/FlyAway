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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add flight</title>
<script>
	//    alert(new Date());
	function validateForm() {
		let source = document.getElementById("source").value;
		let destination = document.getElementById("destination").value;
		let isValid = true;
		let inputDate = document.getElementById("date").value;
		let currentDate = (new Date()).toLocaleDateString();

		let enteredDate = (new Date(inputDate)).toLocaleDateString();

		if (source === destination) {
			alert("Source and Destination cannnot be same. Please enter different source and destination details");
			isValid = false;
		}
		if (enteredDate < currentDate) {
			alert("Entered date is in past. Please enter a valid date");
			isValid = false;
		}
		return isValid;
	}
</script>

</head>
<body>
	<h1>Enter flight details to add</h1>
	<form action="addflight" method="post" onsubmit="return validateForm()">
		<table>
			<tr>
				<td>New Flight ID:</td>
				<td><input type="text" name="flightid" placeholder="Flight ID"
					required /></td>
			</tr>
			<tr>
				<td>Source:</td>
				<td><input type="text" name="source" placeholder="Source"
					id="source" required /></td>
			</tr>
			<tr>
				<td>Destination:</td>
				<td><input type="text" name="destination"
					placeholder="Destination" id="destination" required /></td>
			</tr>
			<tr>
				<td>Airline:</td>
				<td><input type="text" name="airline" placeholder="Airline"
					required /></td>
			</tr>
			<tr>
				<td>Total seats:</td>
				<td><input type="text" name="seats" placeholder="Seats"
					required /></td>
			</tr>
			<tr>
				<td>Date:</td>
				<td><input type="date" name="date" placeholder="Date" id="date"
					required /></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><input type="number" name="price" placeholder="Price"
					required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add flight" /></td>
			</tr>
		</table>
	</form>
</body>
</html>