$(document).ready(function () {

    var currentId;
    $(".delete-user-story").click(function () {
        var text = $(this).parents('li').find(".title").text();
        currentId = $(this).parents("li").attr("id");
        $("#delete").find("p").html(text);
    });

    $(".yes-story").click(function () {
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
