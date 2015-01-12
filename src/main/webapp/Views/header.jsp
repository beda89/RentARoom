<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />
<!DOCTYPE html>
<html lang="de">
<head>
    <title>Rent a Room</title>
    <meta charset="utf-8" />
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value='${base}/resources/outlook_calendar_day.ico' />">

    <!-- jQuery -->
    <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.11.0.min.js' />"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap/bootstrap-theme.min.css' />">
    <script type="text/javascript" src="<c:url value='/resources/js/bootstrap/bootstrap.min.js' />"></script>

    <!-- Plugin: Bootstrap Validator -->
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap/bootstrapValidator.css' />">
    <script type="text/javascript" src="<c:url value='/resources/js/bootstrap/validator/bootstrapValidator.min.js' />"></script>

    <!-- jquery UI google hosted -->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

    <!-- Plugin: jQuery Auto-Complete -->
    <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery.autocomplete.min.js' />"></script>

    <!-- Custom CSS -->
    <link rel="stylesheet" href="<c:url value='${base}/resources/css/rar/rar.style.css' />">

    <!-- Custom JavaScript -->
    <script type="text/javascript" src="<c:url value='/resources/js/rar/rar.init.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/rar/rar.header.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/rar/rar.rooms.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/rar/rar.customer.js' />"></script>
    <script>

        $('#autocomplete').autocomplete({
            serviceUrl: '${base}/autocomplete/names',
            onSelect: function (suggestion) {
                window.location.href = "${base}/customer/"+suggestion.data;
            }
        });

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
        <a class="navbar-brand" href="${base}/">Rent a Room</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <form class="navbar-form navbar-left" role="search">
                <input type="text" name="userSearch" id="autocomplete" title="Kundensuche"/>
            </form>

            <ul class="nav navbar-nav">
                <!-- Kunden -->
                <c:choose>
                <c:when test="${param.page eq 'Customer' || param.page eq 'Customerlist'}">
                <li class="active">
                    </c:when>
                    <c:otherwise>
                <li>
                    </c:otherwise>
                    </c:choose>
                    <a href="${base}/customers" />Kunden</a></li>

                <!-- Zimmer -->
                <c:choose>
                    <c:when test="${param.page eq 'Rooms'}">
                        <li class="active">
                    </c:when>
                    <c:otherwise>
                         <li>
                    </c:otherwise>
                </c:choose>
                <a href="${base}/rooms" />Zimmer</a></li>

            </ul>
        </sec:authorize>

        <ul class="nav navbar-nav">
            <c:choose>
                <c:when test="${param.page eq 'About'}">
                    <li class="active">
                </c:when>
                <c:otherwise>
                    <li>
                </c:otherwise>
            </c:choose>
                <a href="${base}/about" />&Uuml;ber</a>
            </li>
        </ul>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <form class="navbar-form navbar-right" role="search" accept-charset="UTF-8" action="${base}/logout" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Ausloggen</button>
                </div>
            </form>
            <form class="navbar-form navbar-right">
                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#add-customer"><span class="glyphicon glyphicon-plus"></span> Neuer Kunde</button>
            </form>
            <c:if test="${param.page eq 'Customer'}">
                <form class="navbar-form navbar-right">
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#edit-customer"><span class="glyphicon glyphicon-pencil"></span> Editieren</button>
                </form>
            </c:if>
        </sec:authorize>

    </div>
</nav>

<!-- Modals -->
<div class="modal fade" id="add-customer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Neuen Kunden anlegen</h4>
            </div>
            <form id="add-customer-form" role="form" action="${base}/customer" method="POST" class="form-horizontal">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Vorname</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="firstName" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Nachname</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="lastName" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Firma</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="companyName" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Adresse</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="address" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Telefon</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="phone" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Fax</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="fax" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">E-Mail</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="mail" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Homepage</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="homepage" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Link zu Avatar</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="avatarUrl" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Notizen</label>
                        <div class="col-sm-8">
                            <textarea class="form-control" name="notes" rows="3"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
                    <button type="submit" class="btn btn-primary">Anlegen</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="container">