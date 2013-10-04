$(document).ready(function () {
	
    $(".nav_menu_item").bind('selectMenuItem', function () {
        $(this).addClass("selected_menu_item");
        $(this).find(".nav_title").css("opacity", 1);
        var srcString = $(this).find(".nav_icon").attr("src");
        var splitedSrc = srcString.split('.');
        var newSrc = splitedSrc[0] + "_color.png";
        $(this).find(".nav_icon").attr("src", newSrc);

    });


    $(".nav_menu_item").bind('unSelectMenuItem', function () {
        $(this).find(".nav_title").css("opacity", 0);
        var srcString = $(this).find(".nav_icon").attr("src");
        var newSrc = srcString.replace("_color", "");
        $(this).find(".nav_icon").attr("src", newSrc);

    });

    //this will trigger fo all menu items when hover In
    $(".nav_menu_item").bind('hoverInMenuItem', function () {
        if (!($(this).hasClass("selected_menu_item"))) {

            //colering the icon
            var navItemIcon = $(this).find(".nav_icon");
            var srcString = $(navItemIcon).attr("src");
            var splitedSrc = srcString.split('.');
            var newSrc = splitedSrc[0] + "_color.png";
            $(navItemIcon).attr("src", newSrc);

            //fading in the title
            var navItemTitle = $(this).find(".nav_title");
            $(navItemTitle).stop().animate({
                    opacity: 1
                },
                750, function () {});

        }
    });

    //this will trigger fo all menu items when hover Out
    $(".nav_menu_item").bind('hoverOutMenuItem', function () {
        if (!($(this).hasClass("selected_menu_item"))) {

            //un-colering the icon
            var navItemIcon = $(this).find(".nav_icon");
            var srcString = $(navItemIcon).attr("src");
            var newSrc = srcString.replace("_color", "");
            $(navItemIcon).attr("src", newSrc);

            //fading out the title
            var navItemTitle = $(this).find(".nav_title");
            $(navItemTitle).stop().animate({
                    opacity: 0
                },
                500, function () {});

        }
    });

    $(".nav_menu_item").hover(
        function () {
            $(this).trigger("hoverInMenuItem");
        },
        function () {
            $(this).trigger("hoverOutMenuItem");
        }
    );

    $(".nav_menu_item").click(function (e) {
        $(".nav_menu_item").removeClass("selected_menu_item");
        $(".nav_menu_item").trigger("unSelectMenuItem");
        $(this).trigger("selectMenuItem");


        var id = $(this).attr('id');
        
        if(id == "nav_item_accounts") {
        	$.ajax({
	            type: "POST",
	            url: "ContentLoaderServlet",
	            data: "contentID=accountsContent",
	            dataType: "text",
	            success: function (responseText) {
	                $("#content").html(responseText);
	                //TODO - init accounts javascript
	                //initAccounts();
	            }
	
	        });

        } else if(id == "nav_item_settings") {
	        $.ajax({
	            type: "POST",
	            url: "ContentLoaderServlet",
	            data: "contentID=settingsContent",
	            dataType: "text",
	            success: function (responseText) {
	                $("#content").html(responseText);
	                var h = $("#settings_content").css("height");
	                $("#settings_side_menu").css("height", h);
	                console.log("about to call...");
	                initSettings();
	            }
	
	        });

        }
    });

    $("#nav_item_messages").trigger("selectMenuItem");
});