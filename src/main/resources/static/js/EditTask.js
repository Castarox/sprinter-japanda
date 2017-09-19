$(document).ready(function () {
    var id;

    function validate(form, nameCorrect) {
        var isNameCorrect = nameCorrect;


        $(form).find("input[name='taskName']").change(function () {

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
        $(form).find(".taskNameError").text("");
        $(form).find(".valid").prop("disabled", true);

    }

    $(".add-button").click(function () {
        var form = $(".addTask");
        clear(form);
        validate(form, false);
    });
});

