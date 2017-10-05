$(document).ready(function () {


    $('.save').click(function (e) {
        e.preventDefault();
        var priority = $(document).find('.priority').find('img').data('id');
        var header = $("meta[name='_csrf_header']").attr("content");
        var token = $("meta[name='_csrf']").attr("content");
        var currentId = $('#title_user_story').data('id');
        var url = "/projects/user_story/edit/" + currentId;
        var name = $('#title_user_story').val();
        var description = $('#description_user_story').val();
        var projectId = $('#delete-user-story').find('.yes-story').data('project');
        var data = {userStoryName:name,
            description: description,
            priority: priority,
            projectId: projectId};
        console.log(data);

        $.ajax({
            type: "POST",
            beforeSend: function (request) {
                request.setRequestHeader(header, token);
            },
            url: url,
            data: JSON.stringify(data),
            contentType : "application/json",
            dataType : "json",
            success: function (response) {
            }
        })
    })
});