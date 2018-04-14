<%@ page import="com.cs122b.catsneeze.vo.CustomerVo" %><%--
=======
<%@ page import="com.cs122b.movieList.pojo.Genres" %>
<%@ page import="java.util.List" %><%--
>>>>>>> SearchAndBrowseFrontAndBack
  Created by IntelliJ IDEA.
  User: alleninwood
  Date: 1/19/18
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>CatSneeze</title>
    <%--<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/search.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <!-- include jquery autocomplete JS  -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.devbridge-autocomplete/1.4.7/jquery.autocomplete.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="./js/index.js"></script>
    <script src="./js/autocompleteAndFulltext.js"></script>
    <script>
        function refresh() {
            window.location.reload();
        }
    </script>
</head>
<body>
<%--navigation bar--%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="javascript:void(0)" onclick="refresh()">CatSneeze</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="javascript:void(0)" onclick="refresh()">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./page/advancedSearch.jsp">Advanced Search</a>
            </li>
            <li class="nav-item loginState">
                <%--<%--%>
                    <%--if (session.getAttribute("currentCustomer") != null) {--%>
                        <%--String fullName = ((CustomerVo) session.getAttribute("currentCustomer")).getFirstName() + " " + ((CustomerVo)session.getAttribute("currentCustomer")).getLastName();--%>
                <%--%>--%>
                <%--<a class="nav-link username" href="javascript:void(0)" onclick="refresh()">hello,<%=fullName%></a>--%>
                <%--<%--%>
                <%--} else {--%>
                <%--%>--%>
                <%--<a class="nav-link username" href="./page/login.jsp">Sign in</a>--%>
                <%--<%--%>
                    <%--}--%>
                <%--%>--%>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    User center
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">My Cart</a>
                    <a class="dropdown-item" href="#">User Info</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Advance</a>
                </div>
            </li>
            <li>
                <a class="nav-link" href="./page/cart.jsp">Check out</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" id="autocomplete" type="Search" placeholder="Search Movies" aria-label="Search">
            <button class="btn btn-outline-success" id="fulltext" type="button">Search</button>
        </form>
    </div>
</nav>

<%--Big Slide Image--%>
<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img class="d-block w-100" src="./img/bigImg.jpg" alt="First slide">
            <div class="carousel-caption d-none d-md-block">
                <h5>Immersion</h5>
                <p>Completely feelings</p>
            </div>
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="./img/bigImg.jpg" alt="Second slide">
            <div class="carousel-caption d-none d-md-block">
                <h5>Creativity</h5>
                <p>Novel experience</p>
            </div>
        </div>
        <div class="carousel-item">
            <img class="d-block w-100" src="./img/bigImg.jpg" alt="Third slide">
            <div class="carousel-caption d-none d-md-block">
                <h5>Maverick</h5>
                <p>Be different</p>
            </div>
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<%--Movies--%>
<div class="album py-5 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4" style="text-align: center; margin-bottom: 30px;">
                <h3>Top 3 Ratings <span class="badge badge-secondary">Hot</span></h3>
            </div>
            <div class="col-md-4"></div>
        </div>

        <div class="row">
            <%for (int i = 0; i < 3; i++) {%>

            <div class="col-xs-12 col-sm-6 col-md-4">
                <div class="card mb-4 box-shadow">
                    <a class="movie-detail-<%=i%>">
                        <img class="card-img-top" data-src="holder.js/100px225?theme=thumb&amp;bg=55595c&amp;fg=eceeef&amp;text=Thumbnail" alt="Thumbnail [100%x225]" style="height: 225px; width: 100%; display: block;" src="img/background.jpg" data-holder-rendered="true">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title movie-title-<%=i%>"></h5>
                        <h6 class="card-subtitle mb-2 text-muted movie-director-<%=i%>"></h6>
                        <small class="text-muted movie-genres-<%=i%>"></small>
                        <ul class="movie-stars-<%=i%>"></ul>

                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary addCart<%=i%>">Add to Cart</button>
                            </div>
                            <small class="text-muted">9 mins</small>
                        </div>
                    </div>
                </div>
            </div>

            <%}%>
        </div>

        <div class="row">
            <div class="col-md-6">
                <b style="text-align: center">Genres</b>
            </div>
            <div class="col-md-6">
                <b style="text-align: center">Titles</b>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <ul class="genres-list-1">
                </ul>
            </div>
            <div class="col-md-2">
                <ul class="genres-list-2">
                </ul>
            </div>
            <div class="col-md-2">
                <ul class="genres-list-3">
                </ul>
            </div>


            <div class="col-md-1">
                <ul class="number-list-1">
                </ul>
            </div>
            <div class="col-md-1">
                <ul class="number-list-2">
                </ul>
            </div>
            <div class="col-md-1">
                <ul class="letter-list-1">
                </ul>
            </div>
            <div class="col-md-1">
                <ul class="letter-list-2">
                </ul>
            </div>
            <div class="col-md-1">
                <ul class="letter-list-3">
                </ul>
            </div>
            <div class="col-md-1">
                <ul class="letter-list-4">
                </ul>
            </div>
        </div>
    </div>


    <footer class="footer">
        <div class="container">
            <div class="row footer-top">
                <div class="col-xs-12 col-sm-4 col-md-4 slogan">
                    <h4><img src="img/logo.png" style="width: 85px;">&nbsp;&nbsp;&nbsp;CatSneeze</h4>
                    <p>&nbsp;&nbsp;Enjoy Movies at Any Time</p>
                </div>
                <div class="clearfix visible-xs-block"></div>
                <div class="col-xs-12 col-sm-8 col-md-7 col-md-offset-1">
                    <div class="row about">
                        <div class="col-xs-3 col-md-4">
                            <h4>About Us</h4>
                            <ul class="list-unstyle">
                                <li>About us</li>
                                <li>Service link</li>
                                <li>Ads</li>
                                <li>FAQ</li>
                            </ul>
                        </div>
                        <div class="col-xs-3 col-md-4">
                            <h4>Help center</h4>
                            <ul class="list-unstyle">
                                <li>About us</li>
                                <li>Service link</li>
                            </ul>
                        </div>
                        <div class="col-xs-3 col-md-4">
                            <h4>Corporate Information</h4>
                            <ul class="list-unstyle">
                                <li>About us</li>
                                <li>Service link</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <div class="row footer-bottom" style="width: 100%;">
                <ul class="list-inline text-center" style="margin: 0 auto">
                    <li target="_blank">1204-2018 CatSneeze, Inc.</li>
                    <li>CatSneeze.com &nbsp;&nbsp;&nbsp;&nbsp; Irvine, CA</li>
                </ul>
            </div>
        </div>
    </footer>

</div>
</div>

</body>
</html>

