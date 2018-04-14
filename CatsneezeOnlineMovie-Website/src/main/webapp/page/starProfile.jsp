<%@ page import="com.cs122b.catsneeze.vo.CustomerVo" %>
<%@ page import="com.cs122b.catsneeze.vo.StarVo" %>
<%@ page import="com.cs122b.catsneeze.common.Const" %><%--
  Created by IntelliJ IDEA.
  User: alleninwood
  Date: 2/9/18
  Time: 12:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Star</title>
    <%--<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/search.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <!-- include jquery autocomplete JS  -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.devbridge-autocomplete/1.4.7/jquery.autocomplete.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../js/starProfile.js"></script>
    <script src="../js/autocompleteAndFulltext.js"></script>
</head>
<body>
<%--navigation bar--%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="./">CatSneeze</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="./">Home <span class="sr-only">(current)</span></a>
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
            <button class="btn btn-outline-success" id="fulltext" type="submit">Search</button>
        </form>
    </div>
</nav>

<div class="album py-5 bg-light">
    <div class="container">
        <div class="row" style="margin-bottom: 30px;">
            <div class="bg1" style="background: #333 url('img/background.jpg') 50% 50% repeat-y fixed;width: 100%;height: 75%;margin: 0 auto;position: absolute;background-size: 160%;-webkit-backface-visibility: visible;left: 3px;top: 100px;"></div>
            <div class="col-md-1"></div>
            <div class="col-md-5">
                <%
                    StarVo starVo = (StarVo) request.getAttribute(Const.CURRENT_STAR);
                %>
                <div class="card">
                    <img class="card-img-top" src="img/starPhoto.jpg" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title"><%=starVo.getName()%></h5>
                        <h6 class="card-subtitle mb-2 text-muted">id&nbsp;&nbsp;&nbsp;&nbsp;<%=starVo.getId()%><b style="float: right"><%=starVo.getBirthYear()==0 ? "unknown" : starVo.getBirthYear()%></b></h6>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        <%
                            for (int i = 0; i < starVo.moviesList().size(); i++) {
                        %>
                        <a href="./movie?id=<%=starVo.moviesList().get(i).getId()%>" class="card-link"><%=starVo.moviesList().get(i).getTitle()%></a>
                        <%
                            }
                        %>
                    </div>
                </div>

            </div>
            <div class="col-md-6">
            </div>
        </div>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <h3 style="text-align: center; margin-top: 70px; margin-bottom: 70px;">
                    Why So Serious?
                </h3>
            </div>
            <div class="col-md-4"></div>
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
