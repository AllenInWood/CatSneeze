$(document).ready(function () {

    //get metadata of database
    $.ajax({
        type: "GET",
        url: "../../admin/meta",
        success: function (responseJson) {
            if (responseJson.status == 10) {
                alert(responseJson.msg);
                window.location.href = "./login.jsp";
            } else {
                var str = '';
                $.each(responseJson.data, function (index, meta) {
                    if (index == 0) {
                        str += '<h4>creditcards</h4>';
                        str += '<table class="table table-striped table-sm"><thead><tr><th>#</th><th>Attribute</th><th>category</th></tr></thead><tbody>';
                        for (var i in meta) {
                            str += '<tr><td>';
                            str += i;
                            str += '</td><td>';
                            str += meta[i].attribute;
                            str += '</td><td>';
                            str += meta[i].category;
                            str += '</td></tr>';
                        }
                        str += '</tbody></table>';
                    } else if (index == 1) {
                        str += '<h4>customers</h4>';
                        str += '<table class="table table-striped table-sm"><thead><tr><th>#</th><th>Attribute</th><th>category</th></tr></thead><tbody>';
                        for (var i in meta) {
                            str += '<tr><td>';
                            str += i;
                            str += '</td><td>'
                            str += meta[i].attribute;
                            str += '</td><td>';
                            str += meta[i].category;
                            str += '</td></tr>';
                        }
                        str += '</tbody></table>';
                    } else if (index == 2) {
                        str += '<h4>employees</h4>';
                        str += '<table class="table table-striped table-sm"><thead><tr><th>#</th><th>Attribute</th><th>category</th></tr></thead><tbody>';
                        for (var i in meta) {
                            str += '<tr><td>';
                            str += i;
                            str += '</td><td>'
                            str += meta[i].attribute;
                            str += '</td><td>';
                            str += meta[i].category;
                            str += '</td></tr>';
                        }
                        str += '</tbody></table>';
                    } else if (index == 3) {
                        str += '<h4>genres</h4>';
                        str += '<table class="table table-striped table-sm"><thead><tr><th>#</th><th>Attribute</th><th>category</th></tr></thead><tbody>';
                        for (var i in meta) {
                            str += '<tr><td>';
                            str += i;
                            str += '</td><td>'
                            str += meta[i].attribute;
                            str += '</td><td>';
                            str += meta[i].category;
                            str += '</td></tr>';
                        }
                        str += '</tbody></table>';
                    } else if (index == 4) {
                        str += '<h4>genres_in_movies</h4>';
                        str += '<table class="table table-striped table-sm"><thead><tr><th>#</th><th>Attribute</th><th>category</th></tr></thead><tbody>';
                        for (var i in meta) {
                            str += '<tr><td>';
                            str += i;
                            str += '</td><td>'
                            str += meta[i].attribute;
                            str += '</td><td>';
                            str += meta[i].category;
                            str += '</td></tr>';
                        }
                        str += '</tbody></table>';
                    } else if (index == 5) {
                        str += '<h4>movies</h4>';
                        str += '<table class="table table-striped table-sm"><thead><tr><th>#</th><th>Attribute</th><th>category</th></tr></thead><tbody>';
                        for (var i in meta) {
                            str += '<tr><td>';
                            str += i;
                            str += '</td><td>'
                            str += meta[i].attribute;
                            str += '</td><td>';
                            str += meta[i].category;
                            str += '</td></tr>';
                        }
                        str += '</tbody></table>';
                    } else if (index == 6) {
                        str += '<h4>ratings</h4>';
                        str += '<table class="table table-striped table-sm"><thead><tr><th>#</th><th>Attribute</th><th>category</th></tr></thead><tbody>';
                        for (var i in meta) {
                            str += '<tr><td>';
                            str += i;
                            str += '</td><td>'
                            str += meta[i].attribute;
                            str += '</td><td>';
                            str += meta[i].category;
                            str += '</td></tr>';
                        }
                        str += '</tbody></table>';
                    } else if (index == 7) {
                        str += '<h4>sales</h4>';
                        str += '<table class="table table-striped table-sm"><thead><tr><th>#</th><th>Attribute</th><th>category</th></tr></thead><tbody>';
                        for (var i in meta) {
                            str += '<tr><td>';
                            str += i;
                            str += '</td><td>'
                            str += meta[i].attribute;
                            str += '</td><td>';
                            str += meta[i].category;
                            str += '</td></tr>';
                        }
                        str += '</tbody></table>';
                    } else if (index == 8) {
                        str += '<h4>stars</h4>';
                        str += '<table class="table table-striped table-sm"><thead><tr><th>#</th><th>Attribute</th><th>category</th></tr></thead><tbody>';
                        for (var i in meta) {
                            str += '<tr><td>';
                            str += i;
                            str += '</td><td>'
                            str += meta[i].attribute;
                            str += '</td><td>';
                            str += meta[i].category;
                            str += '</td></tr>';
                        }
                        str += '</tbody></table>';
                    } else if (index == 9) {
                        str += '<h4>stars_in_movies</h4>';
                        str += '<table class="table table-striped table-sm"><thead><tr><th>#</th><th>Attribute</th><th>category</th></tr></thead><tbody>';
                        for (var i in meta) {
                            str += '<tr><td>';
                            str += i;
                            str += '</td><td>'
                            str += meta[i].attribute;
                            str += '</td><td>';
                            str += meta[i].category;
                            str += '</td></tr>';
                        }
                        str += '</tbody></table>';
                    }
                    //alert(str);
                });
                $(".metadata").append(str);
            }
        },
        error: function (responseJson) {
            alert(responseJson);
        }
    });


    //add_movie stored procedure call
    $(".insertMovieInfo").click(function () {
        var title = $("#title").val();
        var genreName = $("#genreName").val();
        var starName = $("#starName").val();
        var birthYear = $("#birthYear").val();
        if (title == "" || (genreName == "" && starName == "") || (starName != "" && birthYear != "" && isNaN(birthYear))) {
            if (title == "") {
                $("#title").focus();
                $("#insertInfo").text("movie name is required");
            } else if (genreName == "" && starName == "") {
                $("#genreName").focus();
                $("#insertInfo").text("genre and star should not be null both");
            } else if (starName != "" && birthYear != "" && isNaN(birthYear)) {
                $("#birthYear").focus();
                $("#insertInfo").text("birth year should be numeric");
            }
            alert("parameter wrong");
        } else {
            var birthyearinput = birthYear == "" ? "-1" : birthYear;
            var genreNameinput = genreName == "" ? "NONE" : genreName;
            var starNameinput = starName == "" ? "NONE" : starName;
            var data = {
                "title": title,
                "genreName": genreNameinput,
                "starName": starNameinput,
                "birthYear": birthyearinput
            };
            alert("title:"+ title + " genreName:"+ genreName + " starName:" + starName + " birthYear:" + birthyearinput);

            $.ajax({
                type: "GET",
                url: "../../admin/addMovieInfo",
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
                error: function (responseJson) {
                    alert(responseJson);
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



    //draw chart from sales DB
    var cur_date = new Date();
    var curr_date = cur_date.getDate();
    var curr_month = cur_date.getMonth() + 1;//Months are zero based
    var curr_year = cur_date.getFullYear();

    var days = new Array();

    for (i = 0; i <= 6; i++) {
        var d = curr_date - i;
        var cur = curr_year + "-" + curr_month + "-" + d;
        days[i] = cur;
    }
    alert(curr_year + "-" + curr_month + "-" + curr_date);

    $.ajax({
        type: "GET",
        url: "../../admin/listSaleNum",
        data: {"curDate": curr_year + "-" + curr_month + "-" + curr_date},
        dataType: "json",
        success: function (responseJson) {
            // alert("data:" + responseJson.data)
            // alert("res:" + res[index]);
            // alert(responseJson.data[0]);

            var ctx = document.getElementById("myChart");
            var myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: [days[6], days[5], days[4], days[3], days[2], days[1], days[0]],
                    datasets: [{
                        data: [responseJson.data[6], responseJson.data[5], responseJson.data[4], responseJson.data[3], responseJson.data[2], responseJson.data[1], responseJson.data[0]],
                        lineTension: 0,
                        backgroundColor: 'transparent',
                        borderColor: '#007bff',
                        borderWidth: 4,
                        pointBackgroundColor: '#007bff'
                    }]
                },
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: false
                            }
                        }]
                    },
                    legend: {
                        display: false,
                    }
                }
            });

        },
        error: function (responseJson) {
            alert(responseJson);
        }
    });



});