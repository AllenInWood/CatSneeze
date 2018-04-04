<%--
  Created by IntelliJ IDEA.
  User: alleninwood
  Date: 2/20/18
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Backstage DashBoard</title>
    <%--<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../js/backstage/dashboard.js"></script>
    <link href="../../css/dashboard.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">CatSneeze</a>
    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">Sign out</a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">
                            <span data-feather="home"></span>
                            Dashboard <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./starsModify.jsp">
                            <span data-feather="users"></span>
                            Stars
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./moviesModify.jsp">
                            <span data-feather="bar-chart-2"></span>
                            Movies
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file"></span>
                            Orders
                        </a>
                    </li>
                </ul>

                <%--<h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">--%>
                    <%--<span>Saved reports</span>--%>
                    <%--<a class="d-flex align-items-center text-muted" href="#">--%>
                        <%--<span data-feather="plus-circle"></span>--%>
                    <%--</a>--%>
                <%--</h6>--%>
                <%--<ul class="nav flex-column mb-2">--%>
                    <%--<li class="nav-item">--%>
                        <%--<a class="nav-link" href="#">--%>
                            <%--<span data-feather="file-text"></span>--%>
                            <%--Current month--%>
                        <%--</a>--%>
                    <%--</li>--%>
                    <%--<li class="nav-item">--%>
                        <%--<a class="nav-link" href="#">--%>
                            <%--<span data-feather="file-text"></span>--%>
                            <%--Last quarter--%>
                        <%--</a>--%>
                    <%--</li>--%>
                    <%--<li class="nav-item">--%>
                        <%--<a class="nav-link" href="#">--%>
                            <%--<span data-feather="file-text"></span>--%>
                            <%--Social engagement--%>
                        <%--</a>--%>
                    <%--</li>--%>
                    <%--<li class="nav-item">--%>
                        <%--<a class="nav-link" href="#">--%>
                            <%--<span data-feather="file-text"></span>--%>
                            <%--Year-end sale--%>
                        <%--</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2">Dashboard</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <button class="btn btn-sm btn-outline-secondary">Share</button>
                        <button class="btn btn-sm btn-outline-secondary">Export</button>
                    </div>
                    <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <span data-feather="calendar"></span>
                        This week
                    </button>
                </div>
            </div>

            <canvas class="my-4" id="myChart" width="900" height="380"></canvas>

            <%--todo: add star/genre to current movie--%>
            <form class="">
                <h2>Insert Related Info to Current Movie</h2>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="title">Title</label>
                        <input type="text" class="form-control" id="title" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Valid first name is required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3"></div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="genreName">Genre Name</label>
                        <input type="text" class="form-control" id="genreName" placeholder="" value="">
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3"></div>

                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="starName">Star Name</label>
                        <input type="text" class="form-control" id="starName" placeholder="" value="">
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="birthYear">BirthYear</label>
                        <input type="text" class="form-control" id="birthYear" placeholder="XXXX" value="">
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <button class="btn btn-primary btn-lg btn-block insertMovieInfo" type="button">Insert</button>
                        <small id="insertInfo" class="form-text text-muted" style="color: red;"></small>
                    </div>
                    <div class="col-md-6"></div>
                </div>
            </form>

            <div class="row">
                <%for (int i = 0; i < 3; i++) {%>

                <div class="col-xs-12 col-sm-6 col-md-4">
                    <div class="card mb-4 box-shadow">
                        <div class="card-body">
                            <h5 class="card-title movie-title-<%=i%>"></h5>
                            <h6 class="card-subtitle mb-2 text-muted movie-director-<%=i%>"></h6>
                            <small class="text-muted movie-genres-<%=i%>"></small>
                            <ul class="movie-stars-<%=i%>"></ul>
                        </div>
                    </div>
                </div>

                <%}%>
            </div>

            <h2>Metadata</h2>
            <div class="table-responsive metadata">
            </div>
        </main>
    </div>
</div>


<%--<!-- Icons -->--%>
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>
<!-- Graphs -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
</body>
</html>
