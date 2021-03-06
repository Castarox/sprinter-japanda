$(document).ready(function () {
    var id;

    $('.modal').modal();

    function validate(form, nameCorrect) {
        var isNameCorrect = nameCorrect;

        console.log(form);
        $(form).on('input', function () {

            var input = $(form).find("input[name='taskName']");
            var name = input.val();

            if (name.length < 3) {
                input.css("border-bottom", "1px solid red");
                $(form).find(".taskNameError").text("Name is too short");
                isNameCorrect = false;

            } else {
                input.css("border-bottom", "1px solid #ccc");
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
        $(form).find("input[name='taskName']").val("").css("border-bottom", "1px solid #ccc");
        $(form).find("input[name='taskDescription']").val("").css("border-bottom", "1px solid #ccc");
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
        var name = $(this).parents('li').find(".title").text();
        var description = $(this).parents('li').find(".description").text();
        id = $(this).parents("li").attr("id");
        console.log(id);
        $(form).find("label[for='taskNameEdit']").addClass("active");
        $(form).find("label[for='taskDescriptionEdit']").addClass("active");
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
        console.log(url);
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
                $("#" + id).find(".title").text(name);
                $("#" + id).find(".description").text(description);
                $(".modal").modal('close');
            }
        })


    });
    
    $('.state-img').click(function (e) {
        e.preventDefault();
        var id = $(this).parents("li").attr("id");
        var state = $(this).find('img').data('id');
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var data = {taskId : id, state:state};
        var url = "tasks/edit/" + id + "/state";

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
                console.log("hura");
                switch (state){
                    case 'new':
                        $('#'+ id).find('.state-img').html($('.inProgress-h').clone());
                        break;
                    case 'inProgress':
                        $('#'+ id).find('.state-img').html($('.done-h').clone());
                        break;
                    case 'done':
                        $('#'+ id).find('.state-img').html($('.inProgress-h').clone());
                        break;
                }
            }
        })

    })
});