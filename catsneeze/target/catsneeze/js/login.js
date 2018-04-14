$(document).ready(function () {
    $("#submit").click(function () {
        var email = $("input[id='email']").val();
        var password = $("input[id='password']").val();
        var data = {"email":email, "password":password, "captcha":grecaptcha.getResponse()};
        $.ajax({
            type: "POST",
            url: "../login",
            data: data,
            dataType: "json",
            success: function (responseJson, status, xhr) {
                if (responseJson.status == 0) {
                    alert(responseJson.msg);
                    window.location.href = "../index.jsp";
                }else {
                    $("#loginInfo").text(responseJson.msg);
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                alert("bad add " + XMLHttpRequest.status + " " + textStatus + " " + errorThrown);
                alert("bad minus " + responseJson + " " + status + " " + xhr);
            }

        });
    });

    //judge if login
    $.ajax({
        type: "GET",
        url: "judge",
        success: function (responseJson) {
            if (responseJson.status == "0") {
                var fullName = responseJson.data;
                $(".loginState").append($("<a class=\"nav-link username\" href=\"javascript:void(0)\" onclick=\"refresh()\">"));
                $(".username").text("Hello, " + fullName);
            } else {
                $(".loginState").append($("<a class=\"nav-link username\" href=\"./page/login.jsp\">"));
                $(".username").text("Sign In");
            }
        },
        error: function (responseJson) {
            alert(responseJson.toString())
        }
    });

});


