// $(document).ready(function () {
//     function update () {
//         console.log($(document).find('.priority').find('img').data('id'))
//         // var header = $("meta[name='_csrf_header']").attr("content");
//         // var token = $("meta[name='_csrf']").attr("content");
//         // var url = "user_story/edit/" + currentId;
//         // var form = $("#edit-user-story-form");
//         // var name = $(form).find("input[name='userStoryName']").val();
//         // var description = $(form).find("input[name='description']").val();
//         // var projectId = $(form).find("input[name='projectId']").val();
//         // var priority;
//         //
//         // var data = {userStoryName:name,
//         //     description: description,
//         //     priority: priority,
//         //     projectId: projectId};
//         //
//         // $.ajax({
//         //     type: "POST",
//         //     beforeSend: function (request) {
//         //         request.setRequestHeader(header, token);
//         //     },
//         //     url: url,
//         //     data: JSON.stringify(data),
//         //     contentType : "application/json",
//         //     dataType : "json",
//         //     success: function (response) {
//         //         var container = $("#"+ currentId);
//         //         $(container).find("h3").text(name);
//         //         $(container).find(".priority").text(priority);
//         //         $(container).find(".description").text(description);
//         //         // $(".modal-footer").find(".exit").trigger("click")
//         //         $('.modal').modal('close')
//         //     }
//         // })
//     }
//
//     $('#title_user_story').change(update())
// });