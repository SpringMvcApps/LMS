<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>

	<div id="sidebar">
		<ul type="square">
			<a href="#"><li>User</li></a>
		</ul>
	</div>

	<div id="card">
		<c:choose>
			<c:when test="${exception!=null && userId==null}">
				
				<h3>	
				<c:out value="${exception}" />
				</h3>
				<a href="">Click Here To Login</a>
				
			</c:when>
			<c:when test="${exception!=null && userId!=null}">
			<h3>
					<c:out value="${exception}" />
					
			</h3>
			<a href="Dashboard.htm">Click Here To Go Back To Home Page </a>
			</c:when>
			<c:when test="${exception==null && userId==null}">
			<h3>
			         Something Went Wrong
					
			</h3>
			<a href="">Click Here To Login</a>
			</c:when>
			<c:otherwise>

			        <h3>Something Went Wrong</h3>
					<a href="Dashboard.htm">Click Here To Go Back To Home Page </a>
	
			</c:otherwise>
		</c:choose>

		<div id="lcard"></div>
		<div id="rcard"></div>
	</div>

</body>
</html>