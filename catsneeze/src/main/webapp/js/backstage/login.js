$(document).ready(function () {
    $("#bsubmit").click(function () {
        var email = $("input[id='inputEmail']").val();
        var password = $("input[id='inputPassword']").val();
        alert(email + " " + password)
        // var data = {"email":email, "password":password, "captcha":grecaptcha.getResponse()};
        var data = {"email":email, "password":password};
        $.ajax({
            type: "POST",
            url: "../../admin/login",
            data: data,
            dataType: "json",
            success: function (responseJson) {
                if (responseJson.status == 0) {
                    alert(responseJson.msg);
                    window.location.href = "./dashboard.jsp";
                }else {
                    $("#bloginInfo").text(responseJson.msg);
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.readyState + " " + XMLHttpRequest.status + " " + XMLHttpRequest.responseText);
                alert(textStatus)
            }
        });
    });


});


