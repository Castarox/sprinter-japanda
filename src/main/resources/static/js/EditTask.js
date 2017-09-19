$(document).ready(function () {
    var id;

    function validate(form, nameCorrect) {
        var isNameCorrect = nameCorrect;


        $(form).change(function () {

            var input = $(form).find("input[name='taskName']");
            var name = input.val();

            if (name.length < 3) {
                input.css("border", "1px solid red");
                $(form).find(".taskNameError").text("Name is too short");
                isNameCorrect = false;

            } else {
                input.css("border", "1px solid #ccc");
                $(form).find(".taskNameError").text("");
                isNameCorrect = true;
            }

            if (isNameCorrect) {
                $(form).find(".valid").prop("disabled", false);
            } else {
                $(form).find(".valid").prop("disabled", true);
            }
        });
    }

    function clear(form) {
        $(form).find("input[name='taskName']").val("").css("border", "1px solid #ccc");
        $(form).find("input[name='taskDescription']").val("").css("border", "1px solid #ccc");
        $(form).find(".taskNameError").text("");
        $(form).find(".valid").prop("disabled", true);

    }

    $(".add-button").click(function () {
        var form = $(".addTask");
        clear(form);
        validate(form, false);
    });

    $(".edit-button").click(function () {
        var form = $(".editTask");
        clear(form);
        validate(form, true);
        var name = $(this).parent().find("h3").text();
        var description = $(this).parent().find("p").text();
        id = $(this).parents(".col-sm-9").attr("id");

        $(form).find("input[name='taskName']").val(name);
        $(form).find("input[name='taskDescription']").val(description);

    });


    $(".editTask .valid").click(function (e) {
        e.preventDefault();
        var form = $(".editTask");
        var name = $(form).find("input[name='taskName']").val();
        var description = $(form).find("input[name='taskDescription']").val();
        var us_id = $(form).find("input[name='userStoryId']").val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var data = {taskName: name, taskDescription: description, userStoryId : us_id};
        var url = "tasks/edit/" + id;
        $.ajax({
            type: "POST",
            beforeSend: function (request) {
                request.setRequestHeader(header, token);
            },
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (response) {
                $("#" + id).find("h3").text(name);
                $("#" + id).find("p").text(description);
                $(".modal-footer").find(".exit").trigger("click");
            }
        })


    });
});