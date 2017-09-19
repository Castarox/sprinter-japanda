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

    function validate(form, isNameCorrect, isPriorityCorrect) {
        var isNameCorrect = isNameCorrect;
        var isPriorityCorrect =isPriorityCorrect

        $(form).find("input[name='userStoryName']").keyup(function () {
            if ($(this).val().length < 3){
                $(".storyNameError").text("User story name to short");
                $(this).css("border", "1px solid red");
                isNameCorrect = false;
            } else {
                $(".storyNameError").text("");
                $(this).css("border", "1px solid #ccc");
                isNameCorrect = true;
            }
        });

        $(form).change(function () {
            var input =$(this).find("input[name='priority']");
            if (!input.is(':checked')){
                $(".priorityError").text("User story name to short");
                input.css("border", "1px solid red");
                isPriorityCorrect = false;
            } else {
                $(".priorityError").text("");
                input.css("border", "1px solid #ccc");
                isPriorityCorrect = true;
            }
            if (isNameCorrect && isPriorityCorrect) {
                $(form).find(".toValid").prop("disabled", false);
            } else {
                $(form).find(".toValid").prop("disabled", true);
            }
        })

    }

    function clear(form) {
        $(form).find("input[name='userStoryName']").val("").css("border", "1px solid #ccc");
        $(form).find(".storyNameError").text("");

        $(form).find("input[name='priority']").prop('checked', false);
        $(form).find(".priorityError").text("");


    }

    $(".add-story-button").click(function () {
        var form = $("#add-user-story-form");
        clear(form);
        validate(form, false, false);


    });

    $(".edit-button").click(function () {
        var form = $("#edit-user-story-form");
        clear(form);
        validate(form, true, true);
        currentId = $(this).parents(".col-sm-9").attr("id")
        var name = $(this).parent().find("h3").text();
        var description = $(this).parent().find(".description").text();
        var priority = $(this).parent().find(".priority").text();
        $(form).find("input[name='userStoryName']").val(name);
        $(form).find("input[name='description']").val(description);
        $(form).find("input[name='priority']").each(function () {
            if ($(this).val() === priority) {
                $(this).prop('checked', true)
            }
        });

    });

    $(".toValid").click(function (e) {
        e.preventDefault();

        var header = $("meta[name='_csrf_header']").attr("content");
        var token = $("meta[name='_csrf']").attr("content");
        var url = "user_story/edit/" + currentId;

        var form = $("#edit-user-story-form");
        var name = $(form).find("input[name='userStoryName']").val();
        var description = $(form).find("input[name='description']").val();
        var projectId = $(form).find("input[name='projectId']").val();
        var priority;
        $(form).find("input[name='priority']").each(function () {

            if ($(this).prop('checked') === true) {
                priority = $(this).val();
            }

        });

        var data = {userStoryName:name,
            description: description,
            priority: priority,
            projectId: projectId};

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
                var container = $("#"+ currentId);
                $(container).find("h3").text(name);
                $(container).find(".priority").text(priority);
                $(container).find(".description").text(description);
                $(".modal-footer").find(".exit").trigger("click")
            }
        })
    })


});