/**
 * 
 */

function updateState(stateId,stateName,countryId,status)
{
	$("#hdnCard").empty();
	$("#hdnCard").append("<input type='hidden' id='stateId' name='stateId'/>")
	$("#stateId").val(stateId);
	$("#stateName").val(stateName);
	$("#countryId").val(countryId).prop("selected",true);
	if(status==1)
	{
	$("#a").prop("checked",true);

	}
else
	{
	$("#b").prop("checked",true);
	
	}
	$("#frm").prop("action","updateState.htm");
	$("#sbt").val("Update");
}