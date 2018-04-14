$(document).ready(function () {
    //get all genres
    $.ajax({
        type: "GET",
        url: "./genres",
        success: function (responseJson) {
            var str1 = '', str2 = '', str3 = '';
            $.each(responseJson.data, function(index, genre){
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
                // alert(item.id+","+item.name);
            });
            $(".genres-list-1").append(str1);
            $(".genres-list-2").append(str2);
            $(".genres-list-3").append(str3);

        },
        error: function (responseJson) {
            alert(responseJson);
        }
    });


    $(".addCart").click(function(){
        var href = $(this).attr("href");
        // alert(href);
        var attr1 = href.split(",")[0];
        var attr2 = href.split(",")[1];
        $.ajax({
            type: "GET",
            url: "./cartAdd",
            data: {"movieId": attr1, "number": attr2},
            dataType: "json",
            success: function (responseJson) {
                alert("add to cart successfully");
            },
            error: function (responseJson, status, xhr) {
                alert("bad add " + responseJson + " " + status + " " + xhr);
            }
        });

        return false;
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
        } else if (c <= 78){
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
});