<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Country</title>
<script src="./Scripts/jqMin.js"></script>
<script type="text/javascript" src="./Scripts/Admin/country.js"></script>

</head>
<body onload="loader()">
<%String msg=null;
String alert=null;
alert=request.getParameter("alert");
if(alert!=null)
{
if(alert.equals("Y"))
{%>
	<script>
	alert("Record Deleted Successfully");
	</script>
	
<%
}
else{%>

	<script>
	alert("Failed to delete the record");
	</script>
<% }}
msg=(String)request.getAttribute("alert");
if(msg!=null)
{
if(msg.equals("Inserted"))
{%>
<script>

alert("Record Inserted Successfully");

</script>
<% } 
else if(msg.equals("Deleted"))
{%>
	<script>
	
	alert("Record Deleted Successfully");

	</script>
<% }

else{%>
	<script>
	
	alert("Record Updated Successfully");

	</script>
	<%} }%>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp"></jsp:include>
<div id="card">

	<h3>Country</h3><br>
<form id="frm" action="AddCountry.htm" method="POST">
<div id="lcard">
	
Name&nbsp &nbsp<input type="text" id="countryName" name="countryName" autocomplete="off"/><br><br><br>
Status<br>
INACTIVE&nbsp &nbsp<input type="radio" name="status" id="a" value="1"/>ACTIVE<input type="radio" id="b" name="status" value="2"/>
<input type="submit" id="sbt" value="Submit"/>
</div>
<div id="rcard">
Code&nbsp &nbsp<input type="text" id="countryCode" name="countryCode" autocomplete="off"/><br><br><br>

<div id="hdnCard"></div>
</div>

<div id="listCard">
<c:if test="${countries!=null}">
<table border="1">
<tr><td>Name</td><td>Code</td><td>Status</td><td colspan="2">Action</td></tr>
<c:forEach var="country" items="${countries}">
<tr><td><c:out value="${country.countryName}"/></td>
<td><c:out value="${country.countryCode}"/></td>
<td><c:choose><c:when test="${country.status==2}"><c:out value="Active"/></c:when><c:otherwise><c:out value="In-Active"/></c:otherwise></c:choose></td>
<td><a href="#" onclick="updateCountry('<c:out value='${country.countryId}'/>','<c:out value='${country.countryName}'/>','<c:out value='${country.countryCode}'/>','<c:out value='${country.status}'/>')">Update</a></td><td><a href="deleteCountry.htm?id=<c:out value='${country.countryId}'/>">Delete</a></td></tr>
</c:forEach>
</table>
</c:if>

</div>
<br>

<input type="hidden" id="hdn" name="cToken" value="<c:out value='${cToken}'/>"/>
</form>


</div>



</body>
</html>