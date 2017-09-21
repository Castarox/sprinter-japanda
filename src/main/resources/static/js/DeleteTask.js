$(document).ready(function () {

    var currentId;
    $(".delete-button").click(function () {
        var text = $(this).parents('.card').find("h3").text();
        currentId = $(this).parents(".m12").attr("id");
        $("#deleteTask").find("p").html(text);
    });

    $(".yes").click(function () {
        var url = "tasks/remove/" + currentId;
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: "POST",
            beforeSend: function(request) {
                request.setRequestHeader(header, token);
            },
            url: url,
            success: function(msg) {
                $('#'+ currentId).remove();
                $('.modal').modal('close');
            }
        })
    })
});
