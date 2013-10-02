 function initAccounts() {
     $("#add_account_button").click(function () {
		 
		 $("#overlay").css("display", "block");
        $.ajax({
            type: "POST",
            url: "ContentLoaderServlet",
            data: "contentID=addAccountDialog",
            dataType: "text",
            success: function (responseText) {
                $("#overlay").html(responseText);
                initAddAcountDialog();
            }

        });
     });
 }
 
 function initAddAcountDialog() {
	 //TODO - all the login for the dialog
 }