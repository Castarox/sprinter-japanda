$(document).ready(function () {
    function validate(form) {
        var isNameCorrect = false;
        var isDateCorrect = false;
        $(form).find("input[name='projectName']").change(function () {
            var input = $(form).find("input[name='projectName']");
            var name = input.val();

            if (name.length < 3) {
                input.css("border", "1px solid red");
                $(form).find(".projectNameError").text("Name is too short");
                isNameCorrect = false;
            } else {
                input.css("border", "1px solid #ccc");
                $(form).find(".projectNameError").text("");
                isNameCorrect = true;
            }

        });
        $(".addProject").change(function () {
            var input = $(form).find("input[name='startDate']");
            if (input.val().length <= 0) {
                input.css("border", "1px solid red");
                $(form).find(".startDateError").text("Date can not be empty");
                isDateCorrect = false;
            } else {
                input.css("border", "1px solid #ccc");
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
        $(form).find("input[name='projectName']").val("").css("border", "1px solid #ccc");
        $(form).find("input[name='startDate']").val("").css("border", "1px solid #ccc");
        $(form).find("input[name='endDate']").val("").css("border", "1px solid #ccc");
        $(form).find(".projectNameError").text("");
        $(form).find(".startDateError").text("");
    }
    $(".add-button").click(function () {
        var form = $(".addProject");
        clear(form);
        validate(form);
    });

    $(".edit-button").click(function () {
        var form = $(".editProject");
        clear(form);
        validate(form);
        var name = $(this).parent().find("h3").text();
        var startDate = $(this).parent().find("p").data("project-start");
        var endDate = $(this).parent().find("p").data("project-end");

        $(form).find("input[name='projectName']").val(name);
        $(form).find("input[name='startDate']").val(startDate);
        $(form).find("input[name='endDate']").val(endDate);

    });
});
