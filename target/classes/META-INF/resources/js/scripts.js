var err=null;
var last_selected_radio_btn=null;
$('.stopMoving').click(function(e) {
	e.preventDefault();
})
$(function() {
	$("#stacked-menu").on("click", "li", function() {
		$("#stacked-menu li.active").removeClass("active");
		$(this).addClass("active");
	});
});

function logout() {
	if (document.execCommand) {
		return document.execCommand('ClearAuthenticationCache', 'false');
	}
}

function resetTabView(tabViewId) {
	$("[id='" + tabViewId + "_activeIndex']").val(0);
}



function viewRjct(flag) {

		
		last_selected_radio_btn=flag;

		if(!$(last_selected_radio_btn).is(':checked')){
			$(last_selected_radio_btn).prop("checked",true);
			$(last_selected_radio_btn).parent("div").next("div").attr("class","ui-radiobutton-box ui-widget ui-corner-all ui-state-default ui-state-active")
			$(last_selected_radio_btn).parent("div").next("div").find("span").attr("class","ui-radiobutton-icon ui-icon ui-icon-bullet");

		}
		
		var rec_id=$(flag).parents('.status_code').find('[id$=error_code]').attr('id');
		document.getElementById("recordId").value = rec_id;

		if(flag.value == 'tobeReturned'){
			PF('dlg1').show();
		}
		if(flag.value == 'tobeRejected'){
			PF('dlg1').show();
		}
		if(flag.value == 'toBeCancelled'){
			
			PF('dlg1').show();
		}
		
}
function selectRjCode(code) {
	
	var rec_id =document.getElementById("recordId").value ;
	var error=code.value;
	err=error;
	document.getElementById(rec_id).value=error;
	
	
	
}

//check if rjct reason code selected -Salaheddeen 4-12-2015
function validateReason(event){

	if(err){//reason is selected and hide
		PF('dlg1').hide();
	}else{
		
	}
}

function closeDlg(){
//	if(err){
//		
//	}else{
		$(last_selected_radio_btn).prop("checked",false);
		$(last_selected_radio_btn).parent("div").next("div").attr("class","ui-radiobutton-box ui-widget ui-corner-all ui-state-default")
		$(last_selected_radio_btn).parent("div").next("div").find("span").attr("class","ui-radiobutton-icon ui-icon ui-icon-blank");
	//}
	
	PF('dlg1').hide();
}
function saveSelection(){
	err_code = (document.getElementById("Rjctreason:r_input").value)
	$(last_selected_radio_btn).closest("tr").closest("td").find("[id$=error_code]").attr("value",err_code)
	validateReason();
}

function downloadDrmGuide(){
    var link = document.createElement('a');
    link.href = "/images/DRMGuid.pdf";
    link.download = 'DRMGuid.pdf';
    link.dispatchEvent(new MouseEvent('click'));
    
}