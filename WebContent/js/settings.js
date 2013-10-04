function initSettings() {
    $("#cssmenu li").click(function () {
        var current = $(this);
        $("#cssmenu li").each(function (i, obj) {
            $(obj).removeClass("active");
            current.addClass("active");
        });
    });
}