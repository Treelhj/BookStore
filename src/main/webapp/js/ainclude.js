$(function () {
    $.get("manage_header.html",function (data) {
        $("#header").html(data);
    });
});