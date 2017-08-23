$(document).ready()
{
    $(function () {
        var buttonpressed;
        $('.submit').click(function () {
            buttonpressed = $(this).attr('name');
        });
        $('form').submit(function (e) {
            if (buttonpressed === "reset"){
                e.preventDefault();
                window.location.href ="/user";
                return (false);
            }
            buttonpressed = '';
            return (true);
        })
    })
}