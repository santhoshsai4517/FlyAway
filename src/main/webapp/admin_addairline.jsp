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
<title>Add airline</title>
</head>
<body>
	<h1>Enter airline name to add</h1>
	<form action="addairline" method="post">
		<table>
			<tr>
				<td>New Airline:</td>
				<td><input type="text" name="airline"
					placeholder="Airline name" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add airline" /></td>
			</tr>
		</table>
	</form>
</body>
</html>