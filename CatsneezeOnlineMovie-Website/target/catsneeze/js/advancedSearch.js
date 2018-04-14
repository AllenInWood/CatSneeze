$(document).ready(function () {
    $("#search").click(function () {
        var title = $("#inputTitle").val();
        var year = $("#inputYear").val();
        var director = $("#inputDirector").val();
        var firstName = $("#inputFirstName").val();
        var lastName = $("#inputLastName").val();
        var star = firstName+" "+lastName;

        // $.ajax({
        //     type: "GET",
        //     url: "../search",
        //     data: {"title": title, "year": year, "star": star, "director": director},
        //     dataType: "json",
        //     success: function (responseJson) {
        //         if (responseJson.status == "10") {
        //             alert(responseJson.msg);
        //             window.location = "./login.jsp";
        //         }
        //     },
        //     error: function (responseJson, status, xhr) {
        //         alert(responseJson + " " + status + " " + xhr);
        //     }
        // });
        var url = "../search?title=" + title + "&&year=" + year + "&&star=" + star + "&&director=" + director;
        window.location.href=url;
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