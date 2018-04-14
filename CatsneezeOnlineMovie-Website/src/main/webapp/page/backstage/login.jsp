<%--
  Created by IntelliJ IDEA.
  User: alleninwood
  Date: 2/19/18
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Backstage login</title>
    <%--<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <%--<script src='https://www.google.com/recaptcha/api.js'></script>--%>
    <script src="../../js/backstage/login.js"></script>
    <link href="../../css/login.css" rel="stylesheet">
</head>
<body class="text-center">
<form class="form-signin">
    <img class="mb-4" src="../../img/logo.png" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Admin sign in</h1>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <small id="bloginInfo" class="form-text text-muted" style="color: red;"></small>
    <%--<div class="g-recaptcha" data-sitekey="6LfTIEcUAAAAAAkLfsZnZMANu3SH7TibuUAruTBy"></div>--%>
    <hr>
    <button class="btn btn-lg btn-primary btn-block" id="bsubmit" type="button">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
</form>

</body>
</html>
