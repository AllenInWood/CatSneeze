$(document).ready(function () {
    $(".addCart").click(function(){
        var href = $(this).attr("href");
        // alert(href);
        var attr1 = href.split(",")[0];
        var attr2 = href.split(",")[1];
        $.ajax({
            type: "GET",
            url: "../cartAdd",
            data: {"movieId": attr1, "number": attr2},
            dataType: "json",
            success: function (responseJson) {
                alert(responseJson.msg);
                window.location.reload();
            },
            error: function (responseJson, status, xhr) {
                alert("bad add " + responseJson + " " + status + " " + xhr);
            }
        });

        return false;
    });


    $(".minusCart").click(function(){
        var href = $(this).attr("href");
        // alert(href);
        var attr1 = href.split(",")[0];
        var attr2 = href.split(",")[1];
        $.ajax({
            type: "GET",
            url: "../cartDelete",
            data: {"movieId": attr1, "number": attr2},
            dataType: "json",
            success: function (responseJson) {
                alert(responseJson.msg);
                window.location.reload();
            },
            error: function (responseJson, status, xhr) {
                alert("bad minus " + responseJson + " " + status + " " + xhr);
            }
        });

        return false;
    });


    $(".delCart").click(function(){
        var href = $(this).attr("href");
        // alert(href);
        var attr1 = href.split(",")[0];
        var attr2 = href.split(",")[1];
        $.ajax({
            type: "GET",
            url: "../cartDelete",
            data: {"movieId": attr1, "number": attr2},
            dataType: "json",
            success: function (responseJson) {
                alert(responseJson.msg);
                window.location.reload();
            },
            error: function (responseJson, status, xhr) {
                alert("bad add " + responseJson + " " + status + " " + xhr);
            }
        });

        return false;
    });

    $(".checkout").click(function () {
        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var creditId = $("#creditId").val();
        var expiration = $("#expiration").val();
        //var data = {"firstName": firstName, "lastName": lastName, "cardId": creditId, "expiration": expiration};
        $.ajax({
            type: "GET",
            url: "../check",
            data: {"firstName": firstName, "lastName": lastName, "cardId": creditId, "expiration": expiration},
            dataType: "json",
            success: function (responseJson) {
                if (responseJson.status == 0) {
                    // alert(responseJson.msg);
                    window.location.reload();
                } else {
                    $(".message").text(responseJson.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("bad add " + XMLHttpRequest + " " + textStatus + " " + errorThrown);
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

