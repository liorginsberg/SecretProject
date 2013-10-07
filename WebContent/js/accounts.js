//called after accounts content injected into the content
function bindAccountsJS() {

	$(".basic").spectrum();

	$("#addAccountBtn").click(function() {

		$("#overlay").css("display", "block");

		$.ajax({
			type : "POST",
			url : "ContentLoaderServlet",
			data : "contentID=addAccountDialog",
			dataType : "text",
			success : function(responseText) {
				$("#overlay").html(responseText);
				bindAccountDialogJS();
			}
		});
	});
}

// called after add account dialog injected to screen
function bindAccountDialogJS() {
	$("#accountColor").spectrum({
		color : "#FF0000"
	});

	$("#saveAccount").click(
			function(e) {

				var alias = $("#accountAlias").val();
				var admin = $("#accountAdmin").val();
				var color = $("#accountColor").spectrum("get");
				color = color.toHexString();
				console.log("get color: " + color);

				$.ajax({
					type : "POST",
					url : "AccountManagerServlet",
					data : "accountID=1&accountAlias=" + alias
							+ "&accountAdmin=" + admin + "&accountColor="
							+ color,
					dataType : "text",
					success : function(responseText) {
						console.log(responseText);
						if (responseText == "Success") {
							$("#addAccountDialog").remove();
							$("#overlay").css("display", "none");
							updateAccountContent();

						}
					}
				});
			});
	$("#cancelAccount").click(function(e) {

		$("#addAccountDialog").remove();
		$("#overlay").css("display", "none");
		$(".sp-container").last().remove();

	});
}
