<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<title>City</title>
<script src="./Scripts/jqMin.js"></script>
<script type="text/javascript" src="./Scripts/Admin/city.js"></script>
<%
String msg=null;
msg=(String)request.getAttribute("alert");
if(msg!=null)
{
	if(msg.equals("Inserted"))
	{%>
	<script>alert("Record Inserted Successfully");</script>
	<%}
	else if(msg.equals("Update"))
	{%>
	<script>alert("Record Update Successfull")y</script>
	<%}
	else{%>
	<script>alert("record Deleted Successfully")</script>
	<%}} %>


</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp"></jsp:include>
<div id="card">

	<h3>City</h3><br>
<form id="frm" action="AddCity.htm" method="POST">
<div id="lcard">
	
Name&nbsp &nbsp<input type="text" id="cityName" name="cityName"/><br><br><br>
Status<br>
INACTIVE&nbsp &nbsp<input type="radio" name="status" id="a" value="1"/>ACTIVE<input type="radio" id="b" name="status" value="2"/>
<input type="submit" id="sbt" value="Submit"/>
</div>
<div id="rcard">
<select id="areaId" name="areaId">
<option>Select Area</option>
<c:if test="${areaDTOList!=null}">
<c:forEach var="area" items="${areaDTOList}">
<option value="<c:out value='${area.areaId}'/>"><c:out value="${area.areaName}"/></option>
</c:forEach>
</c:if>
</select>

<div id="hdnCard"></div>
  <br><br><br>

</div>

<div id="listCard">

<table border="1">
<c:if test="${cityDTOList!=null}">
<tr><td>Name</td><td>Area</td><td>Status</td><td>Action</td></tr>
<c:forEach var="city" items="${cityDTOList}">
<tr>
<td><c:out value="${city.cityName}"/></td>
<td><c:out value="${city.areaName}"/></td>
<td><c:choose><c:when test="${city.status==2}"><c:out value="Active"/></c:when><c:otherwise><c:out value="In-Active"/></c:otherwise></c:choose> </td>
<td><a href="#" onclick="updateCity('<c:out value='${city.cityId}'/>','<c:out value='${city.cityName }'/>','<c:out value='${city.status}'/>','<c:out value='${city.areaId}'/>')">Update</a></td>
<td><a href="DeleteCity.htm?cityId=<c:out value='${city.cityId}'/>">Delete</a></td>
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