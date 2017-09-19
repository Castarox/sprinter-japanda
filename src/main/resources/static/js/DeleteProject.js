$(document).ready(function () {

    var currentId;
   $(".delete-button").click(function () {
       var text = $(this).parent().find(".projects").html();
       currentId = $(this).parent().find("h3").data("id");
       $("#delete-project").find("p").html(text);
   });

    $(".yes").click(function () {
        var url = "projects/remove/" + currentId;
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