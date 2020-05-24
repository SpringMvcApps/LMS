<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
<script src="./Scripts/jqMin.js"></script>
<script type="text/javascript" src="./Scripts/Admin/menu.js"></script>
</head>
<body>
<%String msg=null;
msg=(String)request.getAttribute("alert");
if(msg!=null)
{
if(msg.equals("Inserted"))
{%>
<script>
alert("Record Inserted Successfully");
</script>
<%}
else if(msg.equals("Updated"))
{%>
<script>
alert("Records Updated Successfully");
</script>
<%}else
	{%>
	<script>
	alert("Record Deleted Successfully");
	</script>
	<%} } %>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp"></jsp:include>
<div id="card">

	<h3>Menu</h3><br>
<form id="frm" action="AddMenu.htm" method="POST">
<div id="lcard">
	
Name&nbsp &nbsp<input type="text" id="pageName" name="pageName"/><br><br><br>
Status<br>
INACTIVE&nbsp &nbsp<input type="radio" name="status" id="a" value="1"/>ACTIVE<input type="radio" id="b" name="status" value="2"/>
<input type="submit" id="sbt" value="Submit"/>
</div>
<div id="rcard">
Code&nbsp &nbsp<input type="text" id="pageUrl" name="pageUrl"/><br><br><br>
Role&nbsp &nbsp<select id="roleId" name="roleId">
<option>Select Role</option>
<c:if test="${roleDTOList!=null}">
<c:forEach var="role" items="${roleDTOList}">
<option value="<c:out value='${role.roleId }'/>"><c:out value="${role.roleName}"/></option>
</c:forEach>
</c:if>
</select>

<div id="hdnCard"></div>
</div>

<div id="listCard">


</div>
<input type="hidden" id="hdn" name="cToken" value="<c:out value='${cToken}'/>"/>
</form>


</div>



</body>
</html>