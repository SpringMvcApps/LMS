<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<title>Role</title>
<script src="./Scripts/jqMin.js"></script>
<script type="text/javascript" src="./Scripts/Admin/city.js"></script>
<%
String msg=null;
msg=(String)request.getAttribute("alert");
if(msg!=null)
{
	if(msg.equals("Inserted"))
	{%>
	<script>alert("Record Inserted Successfully")</script>
	<%}
	else if(msg.equals("Update"))
	{%>
	<script>alert("Record Update Successfully")</script>
	<%}
	else{%>
	<script>alert("Record Deleted Successfully")</script>
	<%}} %>


</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp"></jsp:include>
<div id="card">

	<h3>Role</h3><br>
<form id="frm" action="AddRole.htm" method="POST">
<div id="lcard">
	
Name&nbsp &nbsp<input type="text" id="roleName" name="roleName"/><br><br><br>
Status<br>
INACTIVE&nbsp &nbsp<input type="radio" name="status" id="a" value="1"/>ACTIVE<input type="radio" id="b" name="status" value="2"/>
<input type="submit" id="sbt" value="Submit"/>
</div>
<div id="rcard">


<div id="hdnCard"></div>
  <br><br><br>

</div>

<div id="listCard">

<table border="1">
<c:if test="${roleDTOList!=null}">
<tr><td>Name</td><td>Status</td><td>Action</td></tr>
<c:forEach var="role" items="${roleDTOList}">
<tr>
<td><c:out value="${role.roleName}"/></td>
<td><c:choose><c:when test="${role.status==2}"><c:out value="Active"/></c:when><c:otherwise><c:out value="In-Active"/></c:otherwise></c:choose> </td>
<td><a href="#" onclick="updateRole('<c:out value='${role.roleId}'/>','<c:out value='${role.roleName }'/>','<c:out value='${role.status}'/>')">Update</a></td>
<td><a href="DeleteRole.htm?roleId=<c:out value='${role.roleId}'/>">Delete</a></td>
</tr>
</c:forEach>
</c:if>
</table>
</div>
<input type="hidden" id="hdn" name="cToken" value="<c:out value='${cToken}'/>"/>
</form>


</div>



</body>
</html>