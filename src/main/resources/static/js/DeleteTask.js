$(document).ready(function () {

    var currentId;

    $(".yes").click(function () {
        currentId = $(this).attr('id');
        var url = "";
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
                window.location.replace('/');
            }
        })
    })
});
