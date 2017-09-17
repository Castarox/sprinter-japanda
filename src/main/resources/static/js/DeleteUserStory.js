$(document).ready(function () {

    var currentId;
   $(".delete-button").click(function () {
       var text = $(this).parent().find(".card-title").html();
       currentId = $(this).parent().find("h3").data("id");
       $("#delete").find("p").html(text);
   });

    $(".yes").click(function () {
        var url = "user_story/" + currentId;
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: "POST",
            beforeSend: function(request) {
                request.setRequestHeader(header, token);
            },
            url: url,
            success: function(msg) {
                $('#'+ currentId).remove()
            }
        })
    })
});