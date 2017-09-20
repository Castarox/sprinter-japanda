$(document).ready(function () {

    var currentId;
   $(".delete-button").click(function () {
       var text = $(this).parents(".m4").find("h3").text();
       currentId = $(this).parents(".m4").find("h3").data("id");
       console.log(text);
       $("#delete-project").find("p").html(text);
   });

    $(".yes").click(function (e) {
        console.log("działa?");
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
                console.log("halo co sie dzieje");
                $('#'+ currentId).remove()
                $(".modal").modal("close");
            }
        })
    })
});