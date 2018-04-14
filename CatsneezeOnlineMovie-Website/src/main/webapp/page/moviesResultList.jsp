<%@ page import="java.util.List" %>
<%@ page import="com.cs122b.catsneeze.vo.CustomerVo" %>
<%@ page import="com.cs122b.catsneeze.vo.MovieVo" %>
<%@ page import="com.cs122b.catsneeze.pojo.Genres" %>
<%@ page import="com.cs122b.catsneeze.pojo.Stars" %>
<%--
  Created by IntelliJ IDEA.
  User: alleninwood
  Date: 2/8/18
  Time: 9:43 PM
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
    <script src="../js/moviesResultList.js"></script>
    <script src="../js/autocompleteAndFulltext.js"></script>
    <script>
        function refresh() {
            window.location.reload();
        }
    </script>
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

        <div style="text-align: center; margin-bottom: 50px;">
            <%
                String genreBase = (String) request.getAttribute("genreBase");
                if (genreBase.equals("genre")) {
                    String genre = (String) request.getAttribute("genre");
                    String base = (String) request.getAttribute("base");
                    String ascOrDes = (String) request.getAttribute("ascOrDes");
                    String limit = (String) request.getAttribute("limit");
                    String offset = (String) request.getAttribute("offset");

                    String url = "./browse?genreBase=genre&&base=title&&genre=" + genre + "&&limit=" + limit + "&&offset=" + offset;
                    String oppUrl = "./browse?genreBase=genre&&base=title&&genre=" + genre + "&&limit=" + limit + "&&offset=" + offset + "&&ascOrDes=desc";
                    String year = "./browse?genreBase=genre&&base=year&&genre=" + genre + "&&limit=" + limit + "&&offset=" + offset;
                    String oppYear =  "./browse?genreBase=genre&&base=year&&genre=" + genre + "&&limit=" + limit + "&&offset=" + offset + "&&ascOrDes=desc";


                    String pre5Movie = "./browse?genreBase=genre&&base=" + base + "&&genre=" + genre + "&&limit=5" + "&&ascOrDes=" + ascOrDes + "&&limit=" + limit + "&&offset=" + offset;
                    String pre10Movie = "./browse?genreBase=genre&&base=" + base + "&&genre=" + genre + "&&limit=10" + "&&ascOrDes=" + ascOrDes + "&&limit=" + limit + "&&offset=" + offset;
                    String pre20Movie = "./browse?genreBase=genre&&base=" + base + "&&genre=" + genre + "&&limit=20" + "&&ascOrDes=" + ascOrDes + "&&limit=" + limit + "&&offset=" + offset;
            %>
            <h4>
                Sort By : <a href="<%=url%>">Title(asc)</a> &nbsp;&nbsp; <a href="<%=oppUrl%>">Title (desc)</a>&nbsp;| &nbsp; <a href="<%=year%>">Year(asc)</a> &nbsp;&nbsp; <a href="<%=oppYear%>">Year(desc)</a>
            </h4>
            <span><a href="<%=pre5Movie%>">5</a>&nbsp;&nbsp;<a href="<%=pre10Movie%>">10</a>&nbsp;&nbsp;<a href="<%=pre20Movie%>">20</a> Per Page</span>
            <%
                }
                else {
                    String headLetter = (String) request.getAttribute("headLetter");
                    String base = (String) request.getAttribute("base");
                    String ascOrDes = (String) request.getAttribute("ascOrDes");
                    String limit = (String) request.getAttribute("limit");
                    String offset = (String) request.getAttribute("offset");
                    String url = "./browse?genreBase=letter&&base=title&&headLetter=" + headLetter + "&&limit=" + limit + "&&offset=" + offset;
                    String oppUrl = "./browse?genreBase=letter&&base=title&&headLetter=" + headLetter + "&&limit=" + limit + "&&offset=" + offset + "&&ascOrDes=desc";
                    String year = "./browse?genreBase=letter&&base=year&&headLetter=" + headLetter;
                    String oppYear =  "./browse?genreBase=letter&&base=year&&headLetter=" + headLetter + "&&ascOrDes=desc";

                    String pre5Movie = "./browse?genreBase=letter&&base=" + base + "&&headLetter=" + headLetter + "&&limit=5" + "&&ascOrDes=" + ascOrDes + "&&offset=" + offset;
                    String pre10Movie = "./browse?genreBase=letter&&base=" + base + "&&headLetter=" + headLetter + "&&limit=10" + "&&ascOrDes=" + ascOrDes + "&&offset=" + offset;
                    String pre20Movie = "./browse?genreBase=letter&&base=" + base + "&&headLetter=" + headLetter + "&&limit=20" + "&&ascOrDes=" + ascOrDes + "&&offset=" + offset;
            %>
            <h4>
                Sort By : <a href="<%=url%>">Title(asc)</a> &nbsp;&nbsp; <a href="<%=oppUrl%>">Title (desc)</a>&nbsp;| &nbsp; <a href="<%=year%>">Year(asc)</a> &nbsp;&nbsp; <a href="<%=oppYear%>">Year(desc)</a>
            </h4>
            <span><a href="<%=pre5Movie%>">5</a>&nbsp;&nbsp;<a href="<%=pre10Movie%>">10</a>&nbsp;&nbsp;<a href="<%=pre20Movie%>">20</a> Per Page</span>
            <%
                }
            %>
        </div>


        <div class="row">
            <%
                List<MovieVo> movieVoList = (List<MovieVo>) request.getAttribute("moviesResult");
                for (MovieVo movieVo : movieVoList) {%>
            <div class="col-md-2"></div>

            <div class="col-md-8">
                <div class="card mb-4 box-shadow">
                    <a href="./movie?id=<%=movieVo.getId()%>">
                        <img class="card-img-top" data-src="holder.js/100px225?theme=thumb&amp;bg=55595c&amp;fg=eceeef&amp;text=Thumbnail" alt="Thumbnail [100%x225]" style="height: 225px; width: 100%; display: block;" src="img/background.jpg" data-holder-rendered="true">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title"><%=movieVo.getTitle()%></h5>
                        <h6 class="card-subtitle mb-2 text-muted"><%=movieVo.getDirector()%><b style="float: right"><%=movieVo.getYear()%></b></h6>
                        <%
                            String genresString = "";
                            for (Genres genres : movieVo.getGenresList()) {
                                genresString += genres.getName() + " ";
                            }
                        %>
                        <small class="text-muted"><%=genresString%></small>
                        <ul>
                            <%
                                for (Stars stars : movieVo.getStarsList()) {
                            %>
                            <li><a href="./star?id=<%=stars.getId()%>">
                                <%=stars.getName()%>
                            </a></li>

                            <%
                                }
                            %>
                        </ul>

                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary addCart"><a class="addCart" href="<%=movieVo.getId()%>,1">Add to Cart</a></button>
                            </div>
                            <small class="text-muted">9 mins</small>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-2"></div>

            <%}%>
        </div>

        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                    <%
                        int offset = Integer.parseInt((String)request.getAttribute("offset"));
                        int limit = Integer.parseInt((String)request.getAttribute("limit"));
                        int cur = offset/limit + 1;
                        int pre = cur - 1;
                        int post = cur + 1;
                        int ppost = cur + 2;
                        int preOffset = (cur - 2) * limit;
                        int postOffset = (cur) * limit;
                        int ppostOffset = (cur + 1) * limit;
                        if (genreBase.equals("genre")) {
                            String genre = (String) request.getAttribute("genre");
                            String prePage = "./browse?genreBase=genre&&genre=" + genre + "&&limit=" + limit + "&&offset=" + preOffset;
                            String postPage = "./browse?genreBase=genre&&genre=" + genre + "&&limit=" + limit + "&&offset=" + postOffset;
                            String ppostPage = "./browse?genreBase=genre&&genre=" + genre + "&&limit=" + limit + "&&offset=" + ppostOffset;
                    %>
                    <%
                        if (cur == 1) {
                    %>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#"><%=cur%></a></li>
                    <li class="page-item"><a class="page-link" href="<%=postPage%>"><%=post%></a></li>
                    <li class="page-item"><a class="page-link" href="<%=ppostPage%>"><%=ppost%></a></li>
                    <%
                        } else {
                    %>
                    <li class="page-item">
                        <a class="page-link" href="<%=prePage%>">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="<%=prePage%>"><%=pre%></a></li>
                    <li class="page-item"><a class="page-link" href="#"><%=cur%></a></li>
                    <li class="page-item"><a class="page-link" href="<%=postPage%>"><%=post%></a></li>
                    <%
                        }
                    %>

                    <li class="page-item">
                        <a class="page-link" href="<%=postPage%>">Next</a>
                    </li>
                    <%
                        } else {
                            String headLetter = (String) request.getAttribute("headLetter");
                            String prePage = "./browse?genreBase=letter&&headLetter=" + headLetter + "&&offset=" + preOffset;
                            String postPage = "./browse?genreBase=letter&&headLetter=" + headLetter + "&&offset=" + postOffset;
                            String ppostPage = "./browse?genreBase=letter&&headLetter=" + headLetter + "&&offset=" + ppostOffset;
                    %>

                    <%
                        if (cur == 1) {
                    %>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#"><%=cur%></a></li>
                    <li class="page-item"><a class="page-link" href="<%=postPage%>"><%=post%></a></li>
                    <li class="page-item"><a class="page-link" href="<%=ppostPage%>"><%=ppost%></a></li>
                    <%
                    } else {
                    %>
                    <li class="page-item">
                        <a class="page-link" href="<%=prePage%>">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="<%=prePage%>"><%=pre%></a></li>
                    <li class="page-item"><a class="page-link" href="#"><%=cur%></a></li>
                    <li class="page-item"><a class="page-link" href="<%=postPage%>"><%=post%></a></li>
                    <%
                        }
                    %>

                    <li class="page-item">
                        <a class="page-link" href="<%=postPage%>">Next</a>
                    </li>

                    <%

                        }
                    %>

            </ul>
        </nav>


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
