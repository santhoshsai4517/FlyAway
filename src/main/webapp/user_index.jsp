<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search flight</title>
<script type="text/javascript">
	function validateForm() {
		let source = document.getElementById("source").value;
		let destination = document.getElementById("destination").value;
		let isValid = true;
		let inputDate = document.getElementById("date").value;
		let currentDate = (new Date()).toLocaleDateString();
		let enteredDate = (new Date(inputDate)).toLocaleDateString();
		let seats = parseInt(document.getElementById("seats").value);
		if (source === destination) {
			alert("Source and Destination cannnot be same. Please enter different source and destination details");
			isValid = false;
		}
		if (enteredDate < currentDate) {
			alert("Entered date is in past. Please enter a valid date");
			isValid = false;
		}
		if(seats <= 0){
			alert("Seats cannot be less than are equal to 0. Please enter valid number of seats");
			isValid = false;
		}
			
		return isValid;
	}
</script>
</head>
<body>
	<h1>Welcome to FlyAway</h1>
	<form action="search" method="post" onsubmit="return validateForm()">

		<table>
			<tr>
				<td>Source</td>
				<td><input type="text" name="source" placeholder="Source city"
					id="source" required /></td>
			</tr>
			<tr>
				<td>Destination</td>
				<td><input type="text" name="destination"
					placeholder="Destination city" id="destination" required /></td>
			</tr>
			<tr>
				<td>Number of seats</td>
				<td><input type="number" name="seats"
					placeholder="Number of seats" id="seats" required /></td>
			</tr>
			<tr>
				<td>Date</td>
				<td><input type="date" name="date" placeholder="Date" id="date"
					required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Search" /></td>
			</tr>
		</table>


	</form>
</body>
</html>