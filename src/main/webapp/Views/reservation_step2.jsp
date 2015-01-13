<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Rooms" />
</jsp:include>



<form id="chooseRoom" method="POST" action="<c:url value='/reservations/reserve/step2/${progressId}' />" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>



    <div class="btn-group">
      <input type="submit" id="back" class="btn btn-default" name="back" value="Zurueck"/>
      <input type="submit" id="next" class="btn btn-default" name="next" value="Weiter"/>
    </div>
</form>


<jsp:include page="footer.jsp" />