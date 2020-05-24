/**
 * 
 */
function getStates()
{
	$("#state").empty();
	var countryId=$("#country").val();
	var count=0;
	//alert(countryId);
	$.ajax({
		type:"GET",
		url:"StateList.htm?countryId="+countryId,
		//data:{"countryId":countryId},other way to transfer data to controller
		success:function(result)
		{
		//alert(result);resut is in STring format so need to convert it to JSON as each() works on on json	
		var resultObj=JSON.parse(result);
		$("#state").append("<option>Select State</option>");
		$.each(resultObj,function()
				{
			
			// alert(resultObj[count].stateId+" "+resultObj[count].stateName);
			 var stateId=resultObj[count].stateId;
			 var stateName=resultObj[count].stateName;
			 $("#state").append("<option value='"+stateId+"'>"+stateName+"</option>");
			 count++;
				});
		
		},
		error:function()
		{
			alert("error");
		}
		
	});
}
function getRegion()
{
	$("#regionId").empty();
	var stateId=$("#state").val();
	var count=0;
	$.ajax({
		type:"GET",
		url:"RegionList.htm?stateId="+stateId,
		success:function(result)
		{
			$("#regionId").append("<option>Select Region</option>");
			var resultObj=JSON.parse(result);
			$.each(resultObj,function()
					{
				var regionId=resultObj[count].regionId;
				var regionName=resultObj[count].regionName;
				$("#regionId").append("<option value='"+regionId+"'>"+regionName+"</option>");
				count++;
					});
		}
		
		
	});
}
function getArea()
{
	$("#areaId").empty();
	var regionId=$("#regionId").val();
	var count=0;
	$.ajax({
		type:"GET",
		url:"AreaList.htm?regionId="+regionId,
		success:function(result)
		{
			alert(result);
			var resultObj=JSON.parse(result);
			$("#areaId").append("<option>Select Area</option>");
			$.each(resultObj,function()
					{
				var areaId=resultObj[count].areaId;
				alert(areaId);
				var areaName=resultObj[count].areaName;
				$("#areaId").append("<option value='"+areaId+"'>"+areaName+"</option>");
				alert(areaName);
				count++
					});
		}
	});
}
function getCity()
{
	$("#city").empty();
	var areaId=$("#areaId").val();
	var count=0;
	$.ajax({
		
		type:"GET",
		url:"CityList.htm?areaId="+areaId,
		success:function(result)
		{
			var resultObj=JSON.parse(result);
			$("#city").append("<option>Select City</option>");
			$.each(resultObj,function()
					{
				var cityId=resultObj[count].cityId;
				var cityName=resultObj[count].cityName;
				$("#city").append("<option value='"+cityId+"'>"+cityName+"</option>");
				count++;
					});
			
		}
	});
}









