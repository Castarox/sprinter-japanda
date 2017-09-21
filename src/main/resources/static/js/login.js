$(document).ready(function () {

    $(".modal").modal();

    $("#email").on("keyup", function () {
        var email = $(this).val();
        if (!isEmail(email)) {
            $("#email").parents("form").find("p").text("");
            $("#submit-email").attr("disabled", true);
        }
    });


    $("#email").on("input", function () {
        var email = $(this).val();
        if (isEmail(email)) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            var data = {email: email};
            var url = "/registration/email";
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
                console.log(response['isEmail']);
                if (response['isEmail']) {
                    $("#email").parents("form").find("p").text("Such a user already exists");
                    $("#submit-email").attr("disabled", true);
                } else {
                    $("#email").parents("form").find("p").text("");
                    $("#submit-email").attr("disabled", false);
                }
            }
            })
        }
    });

    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }

    $("#submit-email").click(function (e) {
        e.preventDefault();
        var email = $("#email").val();
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var url = "/mailing";
        var data = {email : email};

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
                console.log(response["success"]);
                $("#submit-email").hide(1000);
                $("#email").parents(".input-field").hide(1000);
                $("#email").parents("form").find("p").toggleClass("text-danger");
                $("#email").parents("form").find("p").text("An activation link has been sent to your email");
            }
        })
    });
});
