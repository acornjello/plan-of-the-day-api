<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% String cp = request.getContextPath(); %> <%--ContextPath 선언 --%>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>오늘 뭐하지?</title>

    <!-- Bootstrap Core CSS -->
    <link href="../resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="../resources/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../resources/css/stylish-portfolio.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Header -->
<header class="masthead d-flex">
    <div class="container text-center my-auto">
        <h1 class="mb-1">오늘 뭐하지?</h1>
        <h3 class="mb-5">
            <em>무료한 하루를 채워줄 반짝이는 기획</em>
        </h3>
        <form:form modelAttribute="today">
            <form:hidden path="id"/>
            location(주소 혹은 좌표): <form:input  path="location"/><br/>
            search number(책,영화,축제 검색 갯수, default : 3, 최대 10개): <form:input  path="total"/><br/>
            festival search Type (소요시간(default) : 0, 도착시간 : 1): <form:input  path="festSortType"/><br/>
            inside activity (책과 영화 검색(default) : 0, 영화검색: 1,책검색 : 2): <form:input  path="activityType"/><br/>
            <input type="submit" value="Go"/>
<%--            <a class="btn btn-primary btn-xl js-scroll-trigger" type="submit" value="View">Go!</a>--%>
        </form:form>
    </div>
    <div class="overlay"></div>
</header>

<!-- Footer -->
<footer class="footer text-center">
    <div class="container">
        <p class="text-muted small mb-0">Copyright &copy; Today PLAN 2017</p>
    </div>
</footer>

<!-- Bootstrap core JavaScript -->
<script src="../resources/vendor/jquery/jquery.min.js"></script>
<script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for this template -->
<script src="../resources/js/stylish-portfolio.min.js"></script>
</body>
</html>
