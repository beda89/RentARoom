<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
    <jsp:param name="page" value="Login" />
</jsp:include>

<div class="jumbotron">
    <div class="container">
        <h1>Willkommen!</h1>
        <p>Mit dieser Plattform lassen sich Reservierungen in Hotels oder Ferienwohnungen kinderleicht verwalten.</p>
    </div>
</div>

<c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert"><b>Error!</b> ${error}</div>
</c:if>

<c:if test="${not empty msg}">
    <div class="alert alert-info" role="alert"><b>Info!</b> ${msg}</div>
</c:if>

<form name="loginForm" action="<c:url value='/login' />" method="POST" accept-charset="utf-8">
    <div class="form-group">
        <input type="text" name="username" class="form-control" id="inputUsername" placeholder="Benutzername">
    </div>
    <div class="form-group">
        <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Passwort">
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary">Einloggen</button>
</form>

<jsp:include page="footer.jsp" />
