$(document).ready(function () {

    $('.modal').modal();

    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15, // Creates a dropdown of 15 years to control year,
        today: 'Today',
        clear: 'Clear',
        close: 'Ok',
        closeOnSelect: false // Close upon selecting a date,
    });

    var id;
    function validate(form, nameCorrect, dateCorrect) {
        var isNameCorrect = nameCorrect;
        var isDateCorrect = dateCorrect;

        $(form).find("input[name='projectName']").change(function () {

            var input = $(form).find("input[name='projectName']");
            var name = input.val();

            if (name.length < 3) {
                input.css("border-bottom", "1px solid red");
                $(form).find(".projectNameError").text("Name is too short");
                isNameCorrect = false;
            } else {
                input.css("border-bottom", "1px solid #ccc");
                $(form).find(".projectNameError").text("");
                isNameCorrect = true;
            }

        });
        $(form).change(function () {
            var input = $(form).find("input[name='startDate']");
            if (input.val().length <= 0) {
                input.css("border-bottom", "1px solid red");
                $(form).find(".startDateError").text("Date can not be empty");
                isDateCorrect = false;
            } else {
                input.css("border-bottom", "1px solid #ccc");
                $(form).find(".startDateError").text("");
                isDateCorrect = true;
            }
            if (isNameCorrect && isDateCorrect) {
                $(form).find(".toValid").prop("disabled", false);
            } else {
                $(form).find(".toValid").prop("disabled", true);
            }
        });
    };
    function clear(form) {
        $(form).find("input[name='projectName']").val("").css("border-bottom", "1px solid #ccc");
        $(form).find("input[name='startDate']").val("").css("border-bottom", "1px solid #ccc");
        $(form).find("input[name='endDate']").val("").css("border-bottom", "1px solid #ccc");
        $(form).find(".projectNameError").text("");
        $(form).find(".startDateError").text("");
        $(form).find(".toValid").prop("disabled", true);

    }
    $(".add-button").click(function () {
        var form = $(".addProject");
        clear(form);
        validate(form, false, false);
    });

    $(".edit-button").click(function () {
        var form = $(".editProject");
        clear(form);
        validate(form, true, true);
        var name = $(this).parents(".m4").find("h3").text();
        var startDate = $(this).parents(".m4").find("p").data("project-start");
        var endDate = $(this).parents(".m4").find("p").data("project-end");
        id = $(this).parents(".m4").attr("id");

        $(form).find("input[name='projectName']").val(name);
        $(form).find("input[name='startDate']").val(startDate);
        $(form).find("input[name='endDate']").val(endDate);
        $(form).find("label[for='projectNameEdit']").addClass("active");
        $(form).find("label[for='startDateEdit']").addClass("active");
        $(form).find("label[for='endDateEdit']").addClass("active")
    });
    
    $(".editProject .toValid").click(function (e) {
        e.preventDefault();
        var form = $(".editProject");
        var name = $(form).find("input[name='projectName']").val();
        var start = $(form).find("input[name='startDate']").val();
        var end = $(form).find("input[name='endDate']").val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var data = {projectName : name, startDate : start, endDate : end};
        var url = "/projects/edit/" + id;
        console.log(url);
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
                console.log("hura");
                $("#"+ id).find("h3").text(name);
                $("#"+ id).find("p").data("project-start", start);
                $("#"+ id).find("p").data("project-end", end);
                $("#"+ id).find("p").text('Deadline ' + end);
                $('#modal2').modal('close');
            }
        })
    })
});
