<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

<% 
int userId=(Integer)session.getAttribute("userId");
String firstName=(String)session.getAttribute("firstName");
String lastName=(String)session.getAttribute("lastName");
int roleId=Integer.parseInt((String)session.getAttribute("roleId"));
String roleName=(String)session.getAttribute("roleName");
%>
<jsp:include page="Header.jsp"></jsp:include>

<div id="sidebar">
Hi<br><%=firstName+" "+lastName+" "%><br>
<%=roleName %>
	<ul type="square">
	<a href="AdminUtils.htm"><li>Admin Utils</li></a>
	<a href="AdminUtils.htm"><li>Admin Utils</li></a>
	</ul>
</div>
<div id="card">

	<h3>Dashboard</h3>
	

<div >



</body>
</html>