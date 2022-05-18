$(function () {
    $("#pass2-input").blur(function () {
        let value = $(this).val();
        if (value != $("#pass-input").val()) {
            $("#pass-input").css("border-color", "red");
            $(this).css("border-color", "red");
        } else {
            $("#pass-input").css("border", "");
            $(this).css("border", "");
        }
    });
    $("#email-input").blur(function () {
        if(!isEmail($("#email-input").val())) {
            $("#email-input").css("border-color", "red");
        } else {
            $("#email-input").css("border", "");
        }
    })
    $("#register-submit").click(function () {
        let loginOk = false;
        let passOk = false;
        let firstNameOk = false;
        let lastNameOk = false;
        let emailOk = false;
        if (!$("#login-input").val()) {
            $("#login-input").css("border-color", "red");
        } else {
            $("#login-input").css("border", "");
            loginOk = true;
        }
        if(($("#pass-input").val() != $("#pass2-input").val()) || !($("#pass-input").val())) {
            $("#pass-input").css("border-color", "red");
            $("#pass2-input").css("border-color", "red");
        } else {
            $("#pass-input").css("border", "");
            $("#pass2-input").css("border", "");
            passOk = true;
        }
        if (!$("#firstName-input").val()) {
            $("#firstName-input").css("border-color", "red");
        } else {
            $("#firstName-input").css("border", "");
            firstNameOk = true;
        }
        if (!$("#lastName-input").val()) {
            $("#lastName-input").css("border-color", "red");
        } else {
            $("#lastName-input").css("border", "");
            lastNameOk = true;
        }
        if(!isEmail($("#email-input").val())) {
            $("#email-input").css("border-color", "red");
        } else {
            $("#email-input").css("border", "");
            emailOk = true;
        }
        if(loginOk && passOk && firstNameOk && lastNameOk && emailOk) {
            $("#register-form").submit();
        }
    });
    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }
})