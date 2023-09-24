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
<title>Change password</title>
</head>
<body>
	<h1>Enter a new password</h1>
	<form action="change" method="post">
		<table>
			<tr>
				<td>New Password:</td>
				<td><input type="password" name="password"
					placeholder="New password" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Change password" /></td>
			</tr>
		</table>
	</form>
</body>
</html>