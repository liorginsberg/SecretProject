$(document).ready(function(e) {
	
	$.ajax({
			type : "POST",
			url : "ContentLoaderServlet",
			data : "contentID=accountsContent",
			dataType : "text",
			success : function(responseText) {
				$("#content").html(responseText);
				initSpectrumWidgets();
			}
	});
});

	

function initSpectrumWidgets() {
	$(".basic").spectrum({
		color : "#f00",
		change : function(color) {

		}
	});
}


// TODO - for later use
function initAccounts() {
	$("#add_account_button").click(function() {

		$("#overlay").css("display", "block");
		$.ajax({
			type : "POST",
			url : "ContentLoaderServlet",
			data : "contentID=addAccountDialog",
			dataType : "text",
			success : function(responseText) {
				$("#overlay").html(responseText);
				initAddAcountDialog();
			}
		});
	});
}

