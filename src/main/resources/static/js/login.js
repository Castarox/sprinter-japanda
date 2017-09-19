$(document).ready(function () {

    $("#email").on("keyup", function () {
        var email = $(this).val();
        if (!isEmail(email)) {
            $("#email").parents("form").find("p").text("");
        }
    });


    $("#email").on("input", function () {
        var email = $(this).val();
        // console.log(e);
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
                } else {
                    $("#email").parents("form").find("p").text("");
                }
            }
            })
        }
    });

    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }

    // $(".editProject .toValid").click(function (e) {
    //     e.preventDefault();
    //     var form = $(".editProject");
    //     var email = $(form).find("input[name='email']").val();
    //     var start = $(form).find("input[name='startDate']").val();
    //     var end = $(form).find("input[name='endDate']").val();
    //     var token = $("meta[name='_csrf']").attr("content");
    //     var header = $("meta[name='_csrf_header']").attr("content");
    //     var data = {projectName: email};
    //     var url = "/registration/email";
    //     $.ajax({
    //         type: "POST",
    //         beforeSend: function (request) {
    //             request.setRequestHeader(header, token);
    //         },
    //         url: url,
    //         data: JSON.stringify(data),
    //         contentType: "application/json",
    //         dataType: "json",
    //         success: function (response) {
    //             console.log("Hurra");
    //             $("#" + id).find("h3").text(name);
    //             $("#" + id).find("p").data("project-start", start);
    //             $("#" + id).find("p").data("project-end", end);
    //             $("#" + id).find("p").text('Deadline ' + end);
    //             $(".modal-footer").find(".exit").trigger("click");
    //
    //         }
    //     })
    //
    //
    // })
});

