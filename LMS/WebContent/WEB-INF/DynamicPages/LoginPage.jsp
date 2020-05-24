<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.lms.commons.security.SecurityService,javax.servlet.http.Cookie"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<%
String isSession=(String)request.getParameter("session");
String targetUrl=(String)request.getParameter("targetPage");
String cToken=null;
cToken=SecurityService.getToken();
int cookieToken=0;
if(cToken!=null)
{
	cookieToken=cToken.hashCode();
}
Cookie cookie=new Cookie("requestToken",Integer.toString(cookieToken));
cookie.setHttpOnly(true);
//cookie.setSecure(true); use only in case of https
//cookie.setMaxAge(6000);
response.addCookie(cookie);
%>
<c:if test="${authErr!=null}">
<h2><c:out value="${authErr}"/></h2>
</c:if>
<%
if(isSession!=null)
{
if(isSession.equalsIgnoreCase("false"))
{
%>
<h2>Your session has expired. Please login to continue.</h2>
<%}} %>
<body>
<jsp:include page="LoginHeader.jsp"></jsp:include>
<br><br>
<center><form action="<%=request.getContextPath()%>/authenticate" method="POST">
<table>
<tr><td>Username</td><td>
<input type="text" name="username" id="user"/></td></tr>
<tr><td>Password</td><td>
<input type="password" name="password" id="pass"></td></tr>
<tr><td colspan="2"><input type="submit" value="Log in"/></td></tr>
<tr><td>Change Password</td><td>&nbsp&nbsp&nbsp&nbsp
Reset Password</td></tr></table>

<input type="hidden" id="hdn" name="cToken" value="<%=cToken%>"/>
<%if(targetUrl!=null){%>
<input type="hidden" name="targetUrl" value="<%=targetUrl%>"/>
<%}%>


</form></center>
</body>
</html>