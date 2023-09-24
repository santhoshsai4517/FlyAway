<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//System.out.println(session.getAttribute("isValid"));
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
<title>Dashboard</title>
</head>
<body>
	<h1>Welcome to dashboard</h1>
	<table>
		<tr>
			<td><a href="admin_changepassword.jsp">Change password</a></td>
		</tr>
		<tr>
			<td><a href="admin_addplace.jsp">Add new place</a></td>
		</tr>
		<tr>
			<td><a href="admin_addairline.jsp">Add new airline</a></td>
		</tr>
		<tr>
			<td><a href="admin_addflight.jsp">Add new flight</a></td>
		</tr>
		<tr>
			<td><a href="showflights">Show flights</a></td>
		</tr>
		<tr>
			<td><a href="showbookings">Show bookings</a></td>
		</tr>
		<tr>
			<td><a href="showairlines">Show airlines</a></td>
		</tr>
		<tr>
			<td><a href="showplaces">Show places</a></td>
		</tr>
		<tr>
			<td><a href="logout">Logout</a></td>
		</tr>
	</table>
</body>
</html>