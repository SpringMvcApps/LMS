<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp"></jsp:include>

<div id="card">

<div id="lcard">
<c:if test="${errorId!=null}">

<h2>Error while procesing request</h2>&nbsp&nbsp Error Id::><br>

</c:if>
<c:out value="${ExceptionMessage}"/>
</div>
	<div id="rcard" >

	</div>
</div>

</body>
</html>