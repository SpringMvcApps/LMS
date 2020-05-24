/**
 * 
 */
function updateRegion(regionId,regionName,status,stateId)
{
	$("#hdnCard").empty();
	$("#hdnCard").append("<input type='hidden' id='regionId' name='regionId'/>");
	$("#regionId").val(regionId);
	$("#regionName").val(regionName);
	$("#stateId").val(stateId).prop("selected",true);
	if(status==1)
		{
		$("#a").prop("checked",true);
		
		}
	else
		{
		$("#b").prop("checked",true);
		}
	$("#frm").prop("action","updateRegion.htm");
	$("#sbt").val("Update");
}