<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <%--팝업--%>
<%--    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">--%>

    <style>
        #map {
            height: 400px;
            width: 100%;
        }
    </style>
</head>

<body id="page-top">
<!-- Navigation -->
<a class="menu-toggle rounded" href="#">
    <i class="fa fa-bars"></i>
</a>
<nav id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <a class="js-scroll-trigger" href="#page-top">오늘 뭐하지?</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#page-top">Home</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#best">Best</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#about">About</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#choice">Choice</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#mylocation">MyLocation</a>
        </li>
    </ul>
</nav>

<!-- Header -->
<header class="masthead d-flex">
    <div class="container text-center my-auto">
        <h1 class="mb-1">오늘 뭐하지?</h1>
        <h3 class="mb-5">무료한 하루를 채워줄 반짝이는 기획</h3>
        <a class="btn btn-primary btn-xl js-scroll-trigger" href="#best">Best Item</a>
    </div>
    <div class="overlay"></div>
</header>

<!-- Best -->
<section class="content-section bg-primary text-white text-center" id="best">
    <div class="container">
        <div class="content-section-heading">
            <h3 class="text-secondary mb-0">날씨와 미세먼지를 고려하여 추천합니다!</h3>
            <h2 class="mb-5">Best Plan</h2>
        </div>
        <div class="row">
            <div class="col-lg-2 col-md-6 mb-5 mb-lg-0">
           <%-- <span class="service-icon rounded-circle mx-auto mb-3">--%>
              <div id = "wheather"></div>
<%--            </span>--%>
                <h4>
                    <strong>Wheather</strong>
                </h4>
                <h4 id="again">
                    <%--<strong>${weather}</strong>--%>
                </h4>
                <script>
                    if("${weather}"=="눈") {
                        document.getElementById('wheather').innerHTML="<img  src=\"../resources/img/snow.png\" width=\"100\" height=\"100\" alt=\"\">";
                        document.getElementById('snow').innerHTML="<strong>rain</strong>";
                    }
                    else if("${weather}"=="비"){
                        document.getElementById('wheather').innerHTML="<img  src=\"../resources/img/rain.png\" width=\"100\" height=\"100\" alt=\"\">";
                        document.getElementById('again').innerHTML="<strong>rain</strong>";
                    }
                    else {
                        document.getElementById('wheather').innerHTML="<img  src=\"../resources/img/good.png\" width=\"100\" height=\"100\" alt=\"\">";
                        document.getElementById('again').innerHTML="<strong>OK</strong>";
                    }
                </script>
            </div>
            <div class="col-lg-2 col-md-6 mb-5 mb-lg-0">
              <img  src="../resources/img/plus.png" width="100" height="100" alt="">
            </div>
            <div class="col-lg-2 col-md-6 mb-5 mb-lg-0">
              <div id = "dust"></div>
                <h4>
                <strong>Dust</strong>
            </h4>
                <h4 id ="again2">
                    <%--<strong>${dust}</strong>--%>
                </h4>
                <script>
                    if(("${dust}"=="나쁨" )||("${dust}"=="매우나쁨")) {
                        document.getElementById('dust').innerHTML="<img  src=\"../resources/img/Thumbs%20down.png\" width=\"100\" height=\"100\" alt=\"\">";
                        document.getElementById('again2').innerHTML="<strong>Bad</strong>";
                    }
                    else{
                        document.getElementById('dust').innerHTML="<img  src=\"../resources/img/Thumbs%20up.png\" width=\"100\" height=\"100\" alt=\"\">";
                        document.getElementById('again2').innerHTML="<strong>Good</strong>";
                    }
                </script>
            </div>
            <div class="col-lg-2 col-md-6 mb-5 mb-lg-0">
              <img  src="../resources/img/equal.png" width="100" height="100" alt="">
            </div>
            <div class="col-lg-4 col-md-6 mb-5 mb-md-0">
                <%--<img  src="../resources/img/book.png" width="200" height=auto alt="">--%>
                <div id = "recommend"></div>
                <h4>
                    <strong>${bestType}</strong>
                    <script>
                        var recommend = "${bestType}";
                        console.log(typeof(recommend));
                        if(recommend=="book") document.getElementById('recommend').innerHTML="<img  src=\"../resources/img/book.png\" width=\"200\" height=auto alt=\"\">";
                        else if(recommend=="festival") document.getElementById('recommend').innerHTML="<img  src=\"../resources/img/festival.png\" width=\"200\" height=auto alt=\"\">";
                        else if(recommend=="movie") document.getElementById('recommend').innerHTML="<img  src=\"../resources/img/movie.png\" width=\"200\" height=auto alt=\"\">";
                    </script>
                </h4>
            </div>
        </div>
    </div>
</section>
<!-- About best choice-->
<section class="content-section bg-light" id="about">
    <div class="container text-center">
        <div class="row">
            <div class="col-lg-10 mx-auto">
                <h2>${bestType}</h2>
                <p class="lead mb-5" id="bestContents">

                </p>
                <script>
                    var bestType = "${bestType}";


                    if(bestType=="book") document.getElementById('bestContents').innerHTML="${bookName}\n" +
                        "                    author : ${bookAuthor}\n" +
                        "                    contents : ${bookDescription}\n" +
                        "                    price : ${bookPrice}";
                    else if(bestType=="movie") document.getElementById('bestContents').innerHTML="${movieName}\n" +
                        "                    개봉일 : ${openDt}";
                    else if(bestType=="festival") {
                        var path2 = "${path}";
                        var path1 = path2.substring(1,path2.length-1);
                        var path = path1.split("/, ");

                        document.getElementById('bestContents').innerHTML="${festivalTitle}\n" +
                            "                    address : ${festivalAddress}\n<br/>" +
                            "                    <strong>경로</strong><br/><br/>";
                        for(var i=0;i < path.length;i++){
                            document.getElementById('bestContents').innerHTML+=i+" : " + path[i]+"<br/>";
                        }
                    }
                </script>
            </div>
        </div>
    </div>
</section>
<!-- Another Choice -->
<section class="content-section" id="choice">
    <div class="container">
        <div class="content-section-heading text-center">
            <h3 class="text-secondary mb-0">PLAN</h3>
            <h2 class="mb-5">당신의 선택은?</h2>
        </div>
        <div class="row no-gutters">
            <div class="col-lg-6">
                <a class="portfolio-item" href="#about">
              <span class="caption">
                <span class="caption-content">
                  <h2>BEST PLAN</h2>
                  <p class="mb-0">날씨와 미세먼지, 경로를 파악한 최고의 추천 일정입니다.</p>
                </span>
              </span>
                    <img class="img-fluid" src="../resources/img/best.jpg" alt="">
                </a>
            </div>
            <div class="col-lg-6">
                <a class="portfolio-item"  data-toggle="modal" data-target="#myModal2">
                    <!-- 모달 팝업 -->
                    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" >
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">
                                        <span class="sr-only">Close</span>
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel2" >박스오피스</h4>
                                </div>
                                <div class="modal-body" id="movielist"></div>
                                <script>
                                    var num = Number(${total})
                                    var recommendMovie2 = "${recommendMovie}";
                                    if(recommendMovie2 == "") document.getElementById('movielist').innerHTML="";
                                    else {
                                        var recommendMovie1 = recommendMovie2.substring(1,recommendMovie2.length-1);
                                        var recommendMovie = recommendMovie1.split(',');
                                        var recommendMovieOpenDt2 = "${recommendMovieOpenDt}";
                                        var recommendMovieOpenDt1 = recommendMovieOpenDt2.substring(1,recommendMovieOpenDt2.length-1);
                                        var recommendMovieOpenDt = recommendMovieOpenDt1.split(',');
                                        for (var i =0;i<num;i++) {
                                            document.getElementById('movielist').innerHTML+=recommendMovie[i]+"<br/>\n" +
                                                recommendMovieOpenDt[i]+"<br/><br/>";
                                        }
                                    }

                                </script>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
              <span class="caption">
                <span class="caption-content">
                  <h2>MOVIE</h2>
                    <p class="mb-0">현재 상영하는 박스오피스 입니다.</p>
                </span>
              </span>
                    <img class="img-fluid" src="../resources/img/movie.jpg" alt="">
                </a>
            </div>
            <div class="col-lg-6">
                <a class="portfolio-item" data-toggle="modal" data-target="#myModal3">
                    <!-- 모달 팝업 -->
                    <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3" aria-hidden="true" >
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">
                                        <span class="sr-only">Close</span>
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel3" >베스트셀러</h4>
                                </div>
                                <div class="modal-body" id="booklist" ></div>
                                <script>
                                    var num = Number(${total})
                                    var recommendBook2 = "${recommendBook}";

                                    if(recommendBook2 == "") document.getElementById('booklist').innerHTML="";
                                    else{
                                    var recommendBook1 = recommendBook2.substring(1,recommendBook2.length-1);
                                    var recommendBook = recommendBook1.split(',');
                                    var recommendBookAuthor2 = "${recommendBookAuthor}";
                                    var recommendBookAuthor1 =recommendBookAuthor2.substring(1,recommendBookAuthor2.length-1)
                                    var recommendBookAuthor = recommendBookAuthor1.split(',');
                                    for (var i =0;i<num;i++) {
                                        document.getElementById('booklist').innerHTML+=recommendBook[i]+"<br/>\n" +
                                            recommendBookAuthor[i]+"<br/><br/>";
                                    }
                                    }
                                </script>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
              <span class="caption">
                <span class="caption-content">
                  <h2>BOOK</h2>
                    <p class="mb-0">베스트 셀러 리스트</p>
                </span>
              </span>
                    <img class="img-fluid" src="../resources/img/book.jpg" alt="">
                </a>
            </div>
            <div class="col-lg-6">
                <a class="portfolio-item" data-toggle="modal" data-target="#myModal4">
                    <!-- 모달 팝업 -->
                    <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel4" aria-hidden="true" >
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">
                                        <span class="sr-only">Close</span>
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel4" >축제 리스트</h4>
                                </div>
                                <div class="modal-body" id = "festivalList">
                                </div>
                                <script>
                                    var num = Number(${total})
                                    console.log(num);
                                    var recommendFestival2 = "${recommendFestival}";
                                    var recommendFestival1 = recommendFestival2.substring(1,recommendFestival2.length-1);
                                    var recommendFestival = recommendFestival1.split(',');
                                    var recommendFestivalAddress2 = "${recommendFestivalAddress}";
                                    var recommendFestivalAddress1 = recommendFestivalAddress2.substring(1,recommendFestivalAddress2.length-1) ;
                                    var recommendFestivalAddress= recommendFestivalAddress1.split(',');
                                    for (var i =0;i<num;i++) {
                                        document.getElementById('festivalList').innerHTML+=recommendFestival[i]+"<br/>\n" +
                                            recommendFestivalAddress[i]+"<br/><br/>";
                                    }
                                </script>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
              <span class="caption">
                <span class="caption-content">
                  <h2>FESTIVAL</h2>
                  <p class="mb-0">현재 열리고 있는 축제 리스트입니다.</p>
                </span>
              </span>
                    <img class="img-fluid" src="../resources/img/festival.jpg" alt="">
                </a>
            </div>
        </div>
    </div>
</section>

<!-- Map -->
<section class="content-section bg-light" id="mylocation">
    <div class="container text-center">
        <div class="content-section-heading text-center">
            <h3 class="text-secondary mb-0">현재 위치</h3>
            <h2 class="mb-5">${address}</h2>
        </div>
        <div class="row no-gutters">
            <small>
                <div id = "map"/>
            </small>
        </div>
    </div>
</section>
<!-- Last jsp 파일 보기 -->
<section class="callout">
    <div class="container text-center">
        <h2 class="mx-auto mb-5"><em>JSON 확인하기</em></h2>
        <%--<a class="btn btn-primary btn-xl" href="/">JSON</a>--%>
        <button type="button" class="btn btn-primary btn-xl" data-toggle="modal" data-target="#myModal" > JSON </button>

        <!-- 모달 팝업 -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel" >JSON 읽어보기</h4>
                    </div>
                    <div class="modal-body" > ${result}</div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Footer -->
<footer class="footer text-center">
    <div class="container">
        <p class="text-muted small mb-0">Copyright &copy; Today PLAN 2017</p>
    </div>
</footer>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded js-scroll-trigger" href="#page-top">
    <i class="fa fa-angle-up"></i>
</a>

<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAY8nrL5q2WEN5L1mr6nyeC1NwJ5Va0W2Q&callback=showMap">
</script>
<!--map -->
<script>
    function showMap() {
        var latitude  = ${lat};
        var longitude = ${lon};

        var uluru = {lat: latitude, lng: longitude};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 14,
            center: uluru
        });
        var marker = new google.maps.Marker({
            position: uluru,
            map: map
        });
    };
</script>
<!-- Bootstrap core JavaScript -->
<script src="../resources/vendor/jquery/jquery.min.js"></script>
<script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for this template -->
<script src="../resources/js/stylish-portfolio.min.js"></script>

</body>
</html>
