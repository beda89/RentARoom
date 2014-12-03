<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />
<!DOCTYPE html>
<head>
    <title>Rent a Room</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value='${base}//resources/outlook_calendar_day.ico' />">

    <!-- jQuery -->
    <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.11.0.min.js' />"></script>
    <!--<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>-->

    <!-- Bootstrap -->
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap/bootstrap-theme.min.css' />">
    <script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/autocomplete/jquery.autocomplete.min.js' />"></script>
    <!--
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    -->

    <!-- Custom CSS -->
    <link rel="stylesheet" href="<c:url value='${base}/resources/css/rar/rar.style.css' />">

    <!-- Custom JavaScript -->
    <script type="text/javascript" src="<c:url value='/resources/js/rar/rar.init.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/rar/rar.header.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/rar/rar.rooms.js' />"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">

    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${base}/">Rent a Room</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <form class="navbar-form navbar-left" role="search">
                <input type="text" name="userSearch" id="autocomplete"/>

                <!--<div class="form-group">
                    <div class="input-group">
                        <input id="customer-search" class="form-control" type="text" placeholder="Kundenname">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
                    </div> -->
                <!--</div>-->
            </form>
        <script>

        $('#autocomplete').autocomplete({
            serviceUrl: '/autocomplete/names',
            onSelect: function (suggestion) {
                alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
            }
        });

        </script>

            <ul class="nav navbar-nav">
                <c:choose>
                <c:when test="${param.page == 'Rooms'}">
                <li class="active">
                    </c:when>
                    <c:otherwise>
                <li>
                    </c:otherwise>
                    </c:choose>
                    <a href="<c:url value="${base}/rooms" />">Zimmer</a></li>
            </ul>
        </sec:authorize>

        <ul class="nav navbar-nav">
            <c:choose>
            <c:when test="${param.page == 'About'}">
            <li class="active">
                </c:when>
                <c:otherwise>
            <li>
                </c:otherwise>
                </c:choose>
                <a href="<c:url value="${base}/about" />">&Uuml;ber</a></li>
        </ul>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <form class="navbar-form navbar-right" role="search" accept-charset="UTF-8"
                  action="<c:url value="${base}/logout" />">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Ausloggen</button>
                </div>
            </form>
        </sec:authorize>

    </div>
</nav>

<div id="customer-search-result">
    <ul>
        <li><a href="#">Max Mustermann</a></li>
        <li><a href="#">Max Mtest</a></li>
        <li><a href="#">Max Mtest</a></li>
        <li><a href="#">Max Mtest</a></li>
        <li><a href="#">Max Mtest</a></li>
        <li><a href="#">Max Mtest</a></li>
    </ul>
</div>

<div class="container">