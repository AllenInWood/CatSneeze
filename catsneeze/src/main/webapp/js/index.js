$(document).ready(function () {
    //get top three movies
    $.ajax({
        type: "GET",
        url: "index",
        data: {"NumofMovie" : "3"},
        dataType: "json",
        success: function (responseJson) {
            // alert("index:" + responseJson.status);
            movieList = responseJson.data;
            if (responseJson.status == "0") {
                $.each(movieList, function (index, movie) {
                    $(".movie-detail-" + index).attr("href", "./movie?id=" + movie.id);
                    $(".movie-title-" + index).append($("<div>")).text(movie.title).append($("<b class=\"movie-ratings-" + index + "\" style=\"float: right;\">"));
                    $(".movie-ratings-" + index).text(movie.ratings.rating);
                    $(".movie-director-" + index).append($("<div>")).text(movie.director).append($("<div class=\"movie-year-" + index + "\" style=\"float: right;\">"));
                    $(".movie-year-" + index).text(movie.year);
                    for (var id in movie.genresList) {
                        $("<div>").appendTo($(".movie-genres-" + index)).append($("<div>").text(movie.genresList[id].name));
                    }
                    for (var id in movie.starsList) {
                        if (id < 4) {
                            var starId = movie.starsList[id].id;
                            $("<li>").appendTo($(".movie-stars-" + index)).append($("<a href='./star?id=" + starId + "'>").text(movie.starsList[id].name));
                        }
                    }
                    $("<li>").appendTo($(".movie-stars-" + index)).append($("<div>").text("etc."));
                });
            } else if (responseJson.status == "10") {
                $.each(movieList, function (index, movie) {
                    $(".movie-detail-" + index).attr("href", "#");
                    $(".movie-detail-" + index).click(function () {
                        alert(responseJson.msg);
                        window.location = "./page/login.jsp";
                    });
                    $(".movie-title-" + index).append($("<div>")).text(movie.title).append($("<b class=\"movie-ratings-" + index + "\" style=\"float: right;\">"));
                    $(".movie-ratings-" + index).text(movie.ratings.rating);
                    $(".movie-director-" + index).append($("<div>")).text(movie.director).append($("<div class=\"movie-year-" + index + "\" style=\"float: right;\">"));
                    $(".movie-year-" + index).text(movie.year);
                    for (var id in movie.genresList) {
                        $("<div>").appendTo($(".movie-genres-" + index)).append($("<div>").text(movie.genresList[id].name));
                    }
                    for (var id in movie.starsList) {
                        if (id < 4) {
                            var starId = movie.starsList[id].id;
                            $("<li>").appendTo($(".movie-stars-" + index)).append($("<a class='star-" + starId + "' href='#'>").text(movie.starsList[id].name));
                            $(".star-" + starId).click(function () {
                                alert(responseJson.msg);
                                window.location = "./page/login.jsp";
                            });
                        }
                    }
                    $("<li>").appendTo($(".movie-stars-" + index)).append($("<div>").text("etc."));
                });
            }
        },
        error: function (responseJson) {
            alert("ERROR:" + responseJson.status);
        }
    });

    //get all genres
    $.ajax({
        type: "GET",
        url: "genres",
        success: function (responseJson) {
            // alert("genres:" + responseJson.status);
            if (responseJson.status == "0") {
                genresList = responseJson.data;
                var str1 = '', str2 = '', str3 = '';
                $.each(genresList, function (index, genre) {
                    var className = "genre" + index;
                    var url = './browse?genreBase=genre&&genre=' + genre.name;
                    if (index < 8) {
                        str1 += '<li><a class=' + className + ' href="' + url + '">';
                        str1 += genre.name;
                        str1 += '</a></li>';
                    } else if (index < 16) {
                        str2 += '<li><a class=' + className + ' href="' + url + '">';
                        str2 += genre.name;
                        str2 += '</a></li>';
                    } else {
                        str3 += '<li><a class=' + className + ' href="' + url + '">';
                        str3 += genre.name;
                        str3 += '</a></li>';
                    }
                });
                $(".genres-list-1").append(str1);
                $(".genres-list-2").append(str2);
                $(".genres-list-3").append(str3);


                //browse
                var numStr1 = "", numStr2 = "";
                var letterStr1 = "", letterStr2 = "", letterStr3 = "", letterStr4 = "";
                for (var i = 0; i <= 9; i++) {
                    var url = './browse?genreBase=title&&headLetter=' + i;
                    var className = "num" + i;
                    if (i <= 4) {
                        numStr1 += '<li><a class=' + className + ' href="' + url + '">';
                        numStr1 += i;
                        numStr1 += '</a></li>';
                    } else {
                        numStr2 += '<li><a class=' + className + ' href="' + url + '">';
                        numStr2 += i;
                        numStr2 += '</a></li>';
                    }
                }
                $(".number-list-1").append(numStr1);
                $(".number-list-2").append(numStr2);

                for (var c = 65; c < 91; c++) {
                    var url = './browse?genreBase=title&&headLetter=' + String.fromCharCode(c);
                    var className = "letter" + String.fromCharCode(c);
                    if (c <= 71) {
                        letterStr1 += '<li><a class=' + className + ' href="' + url + '">';
                        letterStr1 += String.fromCharCode(c);
                        letterStr1 += '</a></li>';
                    } else if (c <= 78) {
                        letterStr2 += '<li><a class=' + className + ' href="' + url + '">';
                        letterStr2 += String.fromCharCode(c);
                        letterStr2 += '</a></li>';
                    } else if (c <= 84) {
                        letterStr3 += '<li><a class=' + className + ' href="' + url + '">';
                        letterStr3 += String.fromCharCode(c);
                        letterStr3 += '</a></li>';
                    } else {
                        letterStr4 += '<li><a class=' + className + ' href="' + url + '">';
                        letterStr4 += String.fromCharCode(c);
                        letterStr4 += '</a></li>';
                    }
                }
                $(".letter-list-1").append(letterStr1);
                $(".letter-list-2").append(letterStr2);
                $(".letter-list-3").append(letterStr3);
                $(".letter-list-4").append(letterStr4);

            } else if (responseJson.status == "10") {
                genresList = responseJson.data;
                var str1 = '', str2 = '', str3 = '';
                $.each(genresList, function (index, genre) {
                    var className = "genre" + index;
                    if (index < 8) {
                        str1 += '<li><a class=' + className + ' href="#">';
                        str1 += genre.name;
                        str1 += '</a></li>';
                    } else if (index < 16) {
                        str2 += '<li><a class=' + className + ' href="#">';
                        str2 += genre.name;
                        str2 += '</a></li>';
                    } else {
                        str3 += '<li><a class=' + className + ' href="#">';
                        str3 += genre.name;
                        str3 += '</a></li>';
                    }
                });
                $(".genres-list-1").append(str1);
                $(".genres-list-2").append(str2);
                $(".genres-list-3").append(str3);

                $.each(genresList, function (index, genre) {
                    var className = "genre" + index;
                    $("." + className).click(function () {
                        alert(responseJson.msg);
                        window.location = "./page/login.jsp";
                    });
                });

                //browse
                var numStr1 = "", numStr2 = "";
                var letterStr1 = "", letterStr2 = "", letterStr3 = "", letterStr4 = "";
                for (var i = 0; i <= 9; i++) {
                    var className = "num" + i;
                    if (i <= 4) {
                        numStr1 += '<li><a class=' + className + ' href="#">';
                        numStr1 += i;
                        numStr1 += '</a></li>';
                    } else {
                        numStr2 += '<li><a class=' + className + ' href="#">';
                        numStr2 += i;
                        numStr2 += '</a></li>';
                    }
                }
                $(".number-list-1").append(numStr1);
                $(".number-list-2").append(numStr2);
                for (var i = 0; i <= 9; i++) {
                    var className = "num" + i;
                    $("." + className).click(function () {
                        alert(responseJson.msg);
                        window.location = "./page/login.jsp";
                    });
                }

                for (var c = 65; c < 91; c++) {
                    var className = "letter" + String.fromCharCode(c);
                    if (c <= 71) {
                        letterStr1 += '<li><a class=' + className + ' href="#">';
                        letterStr1 += String.fromCharCode(c);
                        letterStr1 += '</a></li>';
                    } else if (c <= 78) {
                        letterStr2 += '<li><a class=' + className + ' href="#">';
                        letterStr2 += String.fromCharCode(c);
                        letterStr2 += '</a></li>';
                    } else if (c <= 84) {
                        letterStr3 += '<li><a class=' + className + ' href="#">';
                        letterStr3 += String.fromCharCode(c);
                        letterStr3 += '</a></li>';
                    } else {
                        letterStr4 += '<li><a class=' + className + ' href="#">';
                        letterStr4 += String.fromCharCode(c);
                        letterStr4 += '</a></li>';
                    }
                }
                $(".letter-list-1").append(letterStr1);
                $(".letter-list-2").append(letterStr2);
                $(".letter-list-3").append(letterStr3);
                $(".letter-list-4").append(letterStr4);
                for (var c = 65; c < 91; c++) {
                    var className = "letter" + String.fromCharCode(c);
                    $("." + className).click(function () {
                        alert(responseJson.msg);
                        window.location = "./page/login.jsp";
                    });
                }
            }
        },
        error: function (responseJson) {
            alert(responseJson.status);
        }
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
