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
if (request.getAttribute("places") == null) {
	response.sendRedirect("admin_dashboard.jsp");
} else {
	String message = (String) request.getAttribute("message");
	ArrayList<String> places = (ArrayList<String>) request.getAttribute("places");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Places</title>
</head>
<body>
	<h1><%=message%></h1>
	<%
	if (!places.isEmpty()) {
	%>
	<table>
		<tr>
			<th>Places</th>
		</tr>
		<%
		for (String place : places) {
		%>
			<tr><td><%= place %></td></tr>
		<%
		}
		%>
	</table>
	<a href ="admin_dashboard.jsp">Go to dashboard</a>
</body>
</html>
<%
}
}
%>