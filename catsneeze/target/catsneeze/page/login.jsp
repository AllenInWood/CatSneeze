<%@ page import="com.cs122b.catsneeze.vo.CustomerVo" %><%--
  Created by IntelliJ IDEA.
  User: alleninwood
  Date: 2/7/18
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>login</title>
    <%--<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/search.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <!-- include jquery autocomplete JS  -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.devbridge-autocomplete/1.4.7/jquery.autocomplete.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script src="../js/login.js"></script>
    <script src="../js/autocompleteAndFulltext.js"></script>
</head>
<body>
    <%--navigation bar--%>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="../">CatSneeze</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="../">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./advancedSearch.jsp">Advanced Search</a>
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
                <%--<a class="nav-link username" href="./login.jsp">Sign in</a>--%>
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
                <a class="nav-link" href="./cart.jsp">Check out</a>
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
            <div class="row">
                <div class="col-md-8"></div>
                <div class="col-md-4"><h4>Login</h4></div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <img src="../img/loginshow.png" style="width: 100%;">
                </div>
                <div class="col-md-4">
                    <form>
                        <div class="form-group">
                            <label for="email">Email address</label>
                            <input type="email" class="form-control" id="email" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" placeholder="Password">
                        </div>
                        <small id="loginInfo" class="form-text text-muted" style="color: red;"></small>
                        <button type="button" class="btn btn-primary" id="submit">Submit</button>
                        <div class="g-recaptcha" data-sitekey="6LfTIEcUAAAAAAkLfsZnZMANu3SH7TibuUAruTBy"></div>
                    </form>
                </div>
            </div>

            <footer class="footer">
                <div class="container">
                    <div class="row footer-top">
                        <div class="col-xs-12 col-sm-4 col-md-4 slogan">
                            <h4><img src="../img/logo.png" style="width: 85px;">&nbsp;&nbsp;&nbsp;CatSneeze</h4>
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
