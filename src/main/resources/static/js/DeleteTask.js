$(document).ready(function () {

    var currentId;
    var usId;

    $('.delete-task').click(function () {
        var text = $(this).parents('li').find(".title").text();
        currentId = $(this).parents('li').attr('id');
        usId = $('#title_user_story').data('id');
        $("#delete").find("p").html(text);
    });

    $(".yes").click(function () {
        console.log(currentId);
        var url = "tasks/remove/" + currentId;
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            type: "POST",
            beforeSend: function (request) {
                request.setRequestHeader(header, token);
            },
            url: url,
            success: function (msg) {
                $('#' + currentId).remove();
                $('.modal').modal('close');
                // window.location.replace('/');
            }
        })
    })
});
