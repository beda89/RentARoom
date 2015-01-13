<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Rooms" />
</jsp:include>



<form id="chooseRoom" method="POST" action="<c:url value='/reservations/reserve/step2/${progressId}' />" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <h2>Zimmerbelegung</h2>

    <c:forEach items="${reservationInProgress.roomList}" var="room">
      ${room.roomNbr} :
        <select name="_selection">
          <option value="0">Einzelzimmer</option>
          <option value="1">Einzelzimmer mit Kind</option>
          <option value="2">Einzelzimmer mit zwei Kindern</option>
          <option value="3">Doppelzimmer</option>
          <option value="4">Doppelzimmer mit Kind</option>
          <option value="5">Dreifachbelegung</option>
        </select>
      <br/>
      <br/>
    </c:forEach>


    <div class="btn-group">
      <input type="submit" id="back" class="btn btn-default" name="back" value="Zurueck"/>
      <input type="submit" id="next" class="btn btn-default" name="next" value="Weiter"/>
    </div>
</form>


<jsp:include page="footer.jsp" />