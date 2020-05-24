<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Region</title>
<script src="./Scripts/jqMin.js"></script>
<script type="text/javascript" src="./Scripts/Admin/region.js"></script>
</head>
<body>
<%String msg=(String)request.getAttribute("alert"); 
if(msg!=null)
{
	if(msg.equals("Inserted"))
	{
		%>
	<script>
	alert("Record Inserted Successfully");
	</script>
	<%}
	else if(msg.equals("Deleted"))
	{%>
	<script >
	alert("Record Deleted Successfully");
	</script>
<%}
	else{%>
	<script>
	alert("Record Updated Successfully");
	</script>
<%}
	} %>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp"></jsp:include>
<div id="card">

	<h3>Region</h3><br>
<form id="frm" action="AddRegion.htm" method="POST">
<div id="lcard">
	
Name&nbsp &nbsp<input type="text" id="regionName" name="regionName"/><br><br><br>
Status<br>
INACTIVE&nbsp &nbsp<input type="radio" name="status" id="a" value="1"/>ACTIVE<input type="radio" id="b" name="status" value="2"/>
<input type="submit" id="sbt" value="Submit"/>
</div>
<div id="rcard">
<select id="stateId" name="stateId"> 
<option>Select Country</option>
<c:if test="${activeStates!=null}">
<c:forEach var="state" items="${activeStates}">
<option value="<c:out value='${state.stateId}'/>"><c:out value="${state.stateName}"/></option>
</c:forEach>
</c:if>
</select>

<div id="hdnCard"></div>
  <br><br><br>

</div>

<div id="listCard">
<c:if test="${regions!=null}">
<table border="1">
<tr><td>Name</td><td>State</td><td>Status</td><td>Action</td></tr>
<c:forEach var="region" items="${regions}">
<tr><td><c:out value="${region.regionName}"/></td>
<td><c:out value="${region.stateName}"/></td>
<td><c:choose><c:when test="${region.status==2}"><c:out value="Active"/></c:when>
<c:otherwise><c:out value="In-Active"/></c:otherwise></c:choose></td>
<td><a href="#" onClick="updateRegion('<c:out value='${region.regionId}'/>','<c:out value='${region.regionName}'/>','<c:out value='${region.status}'/>','<c:out value='${region.stateId}'/>')">Update</a></td>
<td><a href="DeleteRegion.htm?regionId=<c:out value='${region.regionId }'/>"> Delete</a></td>
</tr>
</c:forEach>
</table>
</c:if>



</div>
<input type="hidden" id="hdn" name="cToken" value="<c:out value='${cToken}'/>"/>
</form>


</div>



</body>
</html>