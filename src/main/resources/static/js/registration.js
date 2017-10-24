$(document).ready()
{
    $("#name, #surname").on("keyup", function () {
        nameValidate($(this));
    });

    $("#password, #confirm").on("keyup", function () {
        passValidate($(this));
    });

    $("#email").on("keyup", function () {
        var email = $(this);
        if (!isEmail(email.val()) && email.val().length > 0) {
            email.css("border-color", "red");
        } else {
            email.css("border-color", "#ccc");
        }
    });

    function nameValidate(name) {
        if (name.val().length < 3 && name.val().length > 0) {
            name.css("border-color", "red");
        } else {
            name.css("border-color", "#ccc");
        }
    }

    function passValidate(pass) {
        if (pass.val().length < 6 && pass.val().length > 0) {
            pass.css("border-color", "red");
        } else {
            pass.css("border-color", "#ccc");
        }
    }

    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }
}