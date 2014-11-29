<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp">
    <jsp:param name="noheading" value="true" />
</jsp:include>

<div class="jumbotron">
    <div class="container">
        <h1>Rent a Room</h1>
        <p>

        </p>
    </div>
</div>

<form name="loginForm" action="<c:url value='j_spring_security_check' />" method="POST" accept-charset="UTF-8">
    <div class="form-group">

        <input type="text" name="username" class="form-control" id="inputUsername" placeholder="Benutzername">
    </div>
    <div class="form-group">

        <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Passwort">
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
</form>

<jsp:include page="footer.jsp" />
