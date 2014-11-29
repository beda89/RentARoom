<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<head>
    <title>Rent a Room</title>
    <meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">

    <!-- jQuery -->
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

    <!-- Custom CSS -->
    <link rel="stylesheet" src="<c:url value='/css/rar/rar.style.css'/>">

    <!-- Custom JavaScript -->
    <script type="text/javascript" src="<c:url value='/js/rar/rar.init.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/rar/rar.header.js'/>"></script>

    <script>
    /*
       $(document).ready(function() {

       $('#roomgrid button.btn').click(function() {
       $(this).toggleClass('active');
       var countSelectedRooms = $('#roomgrid button.btn.active').length;
       if (countSelectedRooms != 1) {
       $editRoom.addClass('disabled');
       } else {
       $editRoom.removeClass('disabled');
       }
       if (countSelectedRooms > 0) {
       $reserveRoom.removeClass('disabled');
       } else {
       $reserveRoom.addClass('disabled');
       }
       });
       });*/
    </script>
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
    <a class="navbar-brand" href="#">Rent a Room</a>
  </div>

  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

    <form class="navbar-form navbar-left" role="search">
      <div class="form-group">
        <div class="input-group">
          <input id="customer-search" class="form-control" type="text" placeholder="Kundenname">
          <span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
        </div>
      </div>
    </form>

    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Zimmer</a></li>
      <li class=""><a href="#">Über</a></li>
    </ul>

    <form class="navbar-form navbar-right">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button type="submit" class="btn btn-primary">Ausloggen</button>
        </sec:authorize>
    </form>
  </div>
</nav>

<div class="container">