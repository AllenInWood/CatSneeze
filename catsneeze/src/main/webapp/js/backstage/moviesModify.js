$(document).ready(function () {
    //insert new movie using stored procedure
    $(".insertMovie").click(function () {
        var title = $("#title").val();
        var year = $("#year").val();
        var director = $("#director").val();
        var genreName = $("#genreName").val();
        var starName = $("#starName").val();
        var birthYear = $("#birthYear").val();

        if (title == "" || year == "" || isNaN(year) || director == "" || genreName == "" || starName == "") {
            if (title == "") {
                $("#title").focus();
            } else if (year == "" || isNaN(year)) {
                $("#year").focus();
            } else if (director == "") {
                $("#director").focus();
            } else if (genreName == "") {
                $("#genreName").focus();
            } else if (starName == "") {
                $("#starName").focus();
            }
            $("#insertInfo").text("parameter wrong");
        } else {
            var data = {
                "title": title,
                "year": year,
                "director": director,
                "genreName": genreName,
                "starName": starName,
                "birthYear": birthYear
            };
            $.ajax({
                type: "GET",
                url: "../../admin/addMovie",
                data: data,
                dataType: "json",
                success: function (responseJson) {
                    if (responseJson.status == 10) {
                        alert(responseJson.msg);
                        window.location.href = "./login.jsp";
                    } else if (responseJson.status == 0) {
                        var response = responseJson.msg.split(" ");
                        for (index = response.length - 1; index >= 0; index--) {
                            alert(response[index]);
                        }
                        window.location.reload();
                    } else {
                        alert(responseJson.msg);
                        $("#insertInfo").text(responseJson.msg);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("bad add " + XMLHttpRequest.status + " " + textStatus + " " + errorThrown);
                }
            });
        }
    });

    //list top N id movies
    $.ajax({
        type: "GET",
        url: "../../admin/listMovieInfo",
        data: {"NumofMovie" : "3"},
        dataType: "json",
        success: function (responseJson) {
            if (responseJson.status == 10) {
                alert(responseJson.msg);
                window.location.href = "./login.jsp";
            } else if (responseJson.status == 0) {
                movieList = responseJson.data;
                $.each(movieList, function (index, movie) {
                    $(".movie-title-" + index).append($("<div>")).text(movie.title).append($("<b class=\"movie-ratings-" + index + "\" style=\"float: right;\">"));
                    $(".movie-ratings-" + index).text(movie.ratings.rating);
                    $(".movie-director-" + index).append($("<div>")).text(movie.director).append($("<div class=\"movie-year-" + index + "\" style=\"float: right;\">"));
                    $(".movie-year-" + index).text(movie.year);
                    for (var id in movie.genresList) {
                        $("<div>").appendTo($(".movie-genres-" + index)).append($("<div>").text(movie.genresList[id].name));
                    }
                    for (var id in movie.starsList) {
                        $("<li>").appendTo($(".movie-stars-" + index)).text(movie.starsList[id].name);
                    }
                });
            }
        },
        error: function (responseJson) {
            alert(responseJson);
        }
    });
});