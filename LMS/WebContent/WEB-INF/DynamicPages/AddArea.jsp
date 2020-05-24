<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Area</title>
<script src="./Scripts/jqMin.js"></script>
<script type="text/javascript" src="./Scripts/Admin/area.js"></script>
<%
String msg=null;
msg=(String)request.getAttribute("alert");
if(msg!=null)
{
	if(msg.equals("Inserted"))
	{%>
	<script>Record Inserted Successfully</script>
	<%}
	else if(msg.equals("Update"))
	{%>
	<script>Record Update Successfully</script>
	<%}
	else{%>
	<script>record Deleted Successfully</script>
	<%}} %>


</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp"></jsp:include>
<div id="card">

	<h3>Area</h3><br>
<form id="frm" action="AddArea.htm" method="POST">
<div id="lcard">
	
Name&nbsp &nbsp<input type="text" id="areaName" name="areaName"/><br><br><br>
Status<br>
INACTIVE&nbsp &nbsp<input type="radio" name="status" id="a" value="1"/>ACTIVE<input type="radio" id="b" name="status" value="2"/>
<input type="submit" id="sbt" value="Submit"/>
</div>
<div id="rcard">
<select id="regionId" name="regionId">
<option>Select Region</option>
<c:if test="${regions!=null}">
<c:forEach var="region" items="${regions}">
<option value="<c:out value='${region.regionId}'/>"><c:out value="${region.regionName}"/></option>
</c:forEach>
</c:if>
</select>

<div id="hdnCard"></div>
  <br><br><br>

</div>

<div id="listCard">

<table border="1">
<c:if test="${areas!=null}">
<tr><td>Name</td><td>Region</td><td>Status</td><td>Action</td></tr>
<c:forEach var="area" items="${areas}">
<tr>
<td><c:out value="${area.areaName}"/></td>
<td><c:out value="${area.regionName}"/></td>
<td><c:choose><c:when test="${area.status==2}"><c:out value="Active"/></c:when><c:otherwise><c:out value="In-Active"/></c:otherwise></c:choose></td>
<td><a href="#" onclick="updateArea('<c:out value='${area.areaId}'/>','<c:out value='${area.areaName }'/>','<c:out value='${area.regionId}'/>','<c:out value='${area.status}'/>')">Update</a></td>
<td><a href="DeleteArea.htm?areaId=<c:out value='${area.areaId}'/>">Delete</a></td>
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