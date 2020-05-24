<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp"></jsp:include>
<%String msg=null;
msg=(String)request.getAttribute("alert");
if(msg!=null)
{%>

<%} %>

<div id="card">

	<h3>Country</h3><br>

<table>
<tr><td>Country Name</td></tr>

<c:forEach var="country" items="${countries}">
<tr><td> <a href="ViewCountryInfo.htm?id=<c:out value='${country.countryId}'></c:out>"><c:out value="${country.countryName}"/></a></td></tr>
</c:forEach>



</table>



</div>


</body>
</html>