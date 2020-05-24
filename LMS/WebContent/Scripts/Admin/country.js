/**
 * 
 */
function loader()
{
	//alert("loaded");
//	var flag=$("#countryName").val();
//	$("#countryName").empty();
//	alert(flag);
//	var n=$("#hdn").val();
//	alert(n);
	
	}
function updateCountry(countryId,countryName,countryCode,status)
{
	$("#hdnCard").empty();
	$("#hdnCard").append("<input type='hidden' id='countryId' name='countryId'/>");
	$("#countryId").val(countryId);
	$("#countryCode").val(countryCode);
	$("#countryName").val(countryName);
	if(status==1)
		{
		$("#a").prop("checked",true);
	
		}
	else
		{
		$("#b").prop("checked",true);
		
		}
	$("#frm").prop("action","updateCountry.htm");
	$("#sbt").val("Update");
}