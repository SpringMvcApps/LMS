/**
 * 
 */
function updateArea(areaId,areaName,regionId,status)
{
	$("#hdnCard").empty();
	$("#hdnCard").append("<input type='hidden' id='areaId' name='areaId'/>");
	$("#areaId").val(areaId);
	$("#areaName").val(areaName);
	$("#regionId").val(regionId).prop("selected",true);
	if(status==1)
		{
		$("#a").prop("checked",true)
		}
	else
		{
		$("#b").prop("checked",true);
		}
	$("#frm").prop("action","updateArea.htm");
	$("#sbt").val("Update");
}
