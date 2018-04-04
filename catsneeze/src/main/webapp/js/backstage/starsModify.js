$(document).ready(function () {
//get id top 20 stars
    $.ajax({
        type: "GET",
        url: "../../admin/starsList",
        success: function (responseJson) {
            if (responseJson.status == 10) {
                alert(responseJson.msg);
                window.location.href = "./login.jsp";
            } else {
                var str = '';
                $.each(responseJson.data, function (index, star) {
                    str += '<tr><td>';
                    str += star.id;
                    str += '</td><td>';
                    str += star.name;
                    str += '</td><td>';
                    str += star.birthYear == 0 ? "NULL" : star.birthYear;
                    str += '</td></tr>';
                    //alert(str);
                });
                $("#starsList").append(str);
            }
        },
        error: function (responseJson) {
            alert(responseJson);
        }
    });

    $(".insertStar").click(function () {
        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var birthYear = $("#birthYear").val();
        var data;
        if (birthYear == "") {
            data = {"firstName": firstName, "lastName": lastName};
        } else {
            data = {"firstName": firstName, "lastName": lastName, "birthYear": birthYear};
        }
        $.ajax({
            type: "GET",
            url: "../../admin/addStar",
            data: data,
            dataType: "json",
            success: function (responseJson) {
                if (responseJson.status == 0) {
                    alert(responseJson.msg);
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
    });
});