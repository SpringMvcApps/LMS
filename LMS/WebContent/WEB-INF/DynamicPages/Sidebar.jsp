<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    <%@ page import="com.lms.commons.MenuGenerator,com.lms.admin.beans.LmsMenuBean,java.util.List"%>
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
int roleId=(Integer)session.getAttribute("roleId");
String roleName=(String)session.getAttribute("roleName");
%>
<div id="sidebar">
Hi<br><%=firstName+" "+lastName+" "%><br>
<%=roleName %>
	<ul type="square">
	
	<%
	     List<LmsMenuBean> menu=MenuGenerator.getMenuList((Integer)session.getAttribute("roleId"));
	     pageContext.setAttribute("userMenu",menu);
	  %>
	   <c:if test="${userMenu!=null}">
	   <c:forEach var="menu" items="${userMenu}">
	    <a href="<c:out value='${menu.pageUrl}'/>">
	     <li><c:out value="${menu.pageName}"/></li>
	     </a>
	   </c:forEach>
	   </c:if>
	    
	  

	</ul>
</div>




</body>
</html>