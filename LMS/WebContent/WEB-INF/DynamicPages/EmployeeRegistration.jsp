<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="./Scripts/jqMin.js"></script>
<script type="text/javascript" src="./Scripts/Admin/employee.js"></script>
<title>Employee Registration</title>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Sidebar.jsp"></jsp:include>
<div id="card">

	<h3>Employee Registration</h3><br>
<form id="frm" action="Employee.htm" method="POST">
<div id="lcard">
	
Name&nbsp &nbsp<input type="text" id="empName" name="empName"/><br><br>
Gender&nbsp &nbspMale<input type="radio" id="gen1" name="gender" value="Male"/>&nbsp&nbspFemale<input type="radio" id="gen2" name="gender" value="Female"/><br><br>
Email&nbsp &nbsp<input type="text" id="email" name="email"/><br><br>
State&nbsp &nbsp<select name="state" id="state" onchange="getRegion()"><option>Select State</option></select><br><br>
Role&nbsp &nbsp<select name="roleId" id="roleId"><option>Select Role</option>
<c:if test="${roleDtoList!=null}">
<c:forEach var="role" items="${roleDtoList}">
<option value="<c:out value='${role.roleId}'/>"><c:out value="${role.roleName}"/></option>
</c:forEach>
</c:if>
</select><br><br>
Area&nbsp &nbsp<select name="areaId" id="areaId" onchange="getCity()"><option>Select Area</option></select><br><br>
Status<br>
INACTIVE&nbsp &nbsp<input type="radio" name="status" id="a" value="1"/>ACTIVE<input type="radio" id="b" name="status" value="2"/>
<input type="submit" id="sbt" value="Submit"/>
</div>
<div id="rcard">
Last Name&nbsp &nbsp<input type="text" id="empLastName" name="empLastName"/><br><br>
Mobile&nbsp &nbsp<input type="text" id="mobileNo" name="mobileNo"/><br><br>
Country&nbsp &nbsp<select name="country" id="country" onchange="getStates()"><option>Select Country</option>
<c:if test="${countryDtoList!=null}">
<c:forEach var="country" items="${countryDtoList}">
<option value="<c:out value='${country.countryId}'/>"><c:out value="${country.countryName}"/></option>
</c:forEach>
</c:if>
</select><br><br>
Region&nbsp &nbsp<select name="regionId" id="regionId" onChange="getArea()"><option>Select Region</option></select><br><br>

City&nbsp &nbsp<select name="city" id="city"><option>Select City</option></select><br><br>


<div id="hdnCard"></div>
</div>

<div id="listCard">

</div>
<input type="hidden" id="hdn" name="cToken" value="<c:out value='${cToken}'/>"/>
</form>


</div>


</body>
</html>