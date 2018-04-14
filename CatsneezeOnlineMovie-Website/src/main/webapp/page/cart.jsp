<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.cs122b.catsneeze.vo.CustomerVo" %>
<%@ page import="com.cs122b.catsneeze.vo.ShoppingItemVo" %>
<%@ page import="com.cs122b.catsneeze.common.Const" %>
<%--
  Created by IntelliJ IDEA.
  User: alleninwood
  Date: 2/7/18
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="../js/cart.js"></script>
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
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <h4 class="d-flex justify-content-between align-items-center">
                        <span class="text-muted">Your cart</span>
                        <%
                            Map<String, ShoppingItemVo> shoppingCart = (HashMap<String, ShoppingItemVo>) session.getAttribute(Const.SHOPPING_CART);
                            int sumCount = 0;
                            if (shoppingCart != null) {
                                for (String key : shoppingCart.keySet()) {
                                    sumCount += shoppingCart.get(key).getQuantity();
                                }
                            }
                        %>
                        <span class="badge badge-secondary badge-pill"><%=sumCount%></span>
                    </h4>
                    <ul class="list-group">
                    <%
                        if (shoppingCart != null) {
                            for (String key : shoppingCart.keySet()) {
                    %>
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div>
                                <h6 class="my-0"><%=shoppingCart.get(key).getMovieVo().getTitle()%></h6>
                                <small class="text-muted"><%=shoppingCart.get(key).getMovieVo().getId()%></small>
                            </div>
                            <button type="button" class="btn btn-sm btn-outline-secondary addCart"><a class="addCart" href="<%=shoppingCart.get(key).getMovieVo().getId()%>,1">Add one</a></button>
                            <button type="button" class="btn btn-sm btn-outline-secondary minusCart"><a class="minusCart" href="<%=shoppingCart.get(key).getMovieVo().getId()%>,1">Minus one</a></button>
                            <%--<a href="../cartAdd?number=1&&movieId=<%=shoppingCart.get(key).getMovieVo().getId()%>">Add one</a>--%>
                            <%--<a href="../cartDelete?number=1&&movieId=<%=shoppingCart.get(key).getMovieVo().getId()%>">Minus one</a>--%>
                            <span class="text-muted">$<%=shoppingCart.get(key).getQuantity() * 1%></span>
                            <button type="button" class="btn btn-sm btn-outline-secondary delCart"><a class="delCart" href="<%=shoppingCart.get(key).getMovieVo().getId()%>,<%=shoppingCart.get(key).getQuantity()%>">Delete</a></button>
                            <%--<a href="../cartDelete?number=<%=shoppingCart.get(key).getQuantity()%>&&movieId=<%=shoppingCart.get(key).getMovieVo().getId()%>">Delete</a>--%>
                        </li>
                    <%
                            }
                        }
                    %>
                        <li class="list-group-item d-flex justify-content-between">
                            <span>Total (USD)</span>
                            <strong>$<%=sumCount%></strong>
                        </li>
                    </ul>
                </div>
                <div class="col-md-1"></div>
            </div>

            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                        <hr class="mb-4">

                        <h4 class="mb-3">Payment</h4>

                        <div class="d-block my-3">
                            <div class="custom-control custom-radio">
                                <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked required>
                                <label class="custom-control-label" for="credit">Credit card</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required>
                                <label class="custom-control-label" for="debit">Debit card</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required>
                                <label class="custom-control-label" for="paypal">Paypal</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstName">First Name</label>
                                <input type="text" class="form-control" id="firstName" placeholder="First Name" required>
                                <div class="invalid-feedback">
                                    Name on card is required
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="lastName">Last Name</label>
                                <input type="text" class="form-control" id="lastName" placeholder="First Name" required>
                                <div class="invalid-feedback">
                                    Name on card is required
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="creditId">Credit card number</label>
                                <input type="text" class="form-control" id="creditId" placeholder="XXXX-XXXX-XXXX-XXXX" required>
                                <div class="invalid-feedback">
                                    Credit card number is required
                                </div>
                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="expiration">Expiration</label>
                                <input type="text" class="form-control" id="expiration" placeholder="XXXX-XX-XX" required>
                                <div class="invalid-feedback">
                                    Expiration date required
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <small class="form-text text-muted message" style="color: red;"></small>
                            </div>
                            <div class="col-md-3 mb-3">
                            </div>
                        </div>
                        <hr class="mb-4">
                        <button class="btn btn-primary btn-lg btn-block checkout" type="submit">Continue to checkout</button>
                    </form>
                </div>
                <div class="col-md-1"></div>
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
