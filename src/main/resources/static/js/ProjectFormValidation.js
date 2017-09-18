$(document).ready(function () {
    var isNameCorrect = false;
    var isDateCorrect = false;
    $("input[name='projectName']").change(function () {
        var input = $("input[name='projectName']");
        var name = input.val();

        if (name.length < 3) {
            input.css("border", "1px solid red");
            $(".projectNameError").text("Name is too short");
            isNameCorrect = false;
        } else {
            input.css("border", "1px solid #ccc");
            $(".projectNameError").text("");
            isNameCorrect = true;
        }

    });
    $(".addProject").change(function () {
        var input = $("input[name='startDate']");
        if (input.val().length <= 0) {
            input.css("border", "1px solid red");
            $(".startDateError").text("Date can not be empty");
            isDateCorrect = false;
        } else {
            input.css("border", "1px solid #ccc");
            $(".startDateError").text("");
            isDateCorrect = true;
        }
        if (isNameCorrect && isDateCorrect) {
            $("#btn").prop("disabled", false);
        } else {
            $("#btn").prop("disabled", true);
        }
    });
    });


    
