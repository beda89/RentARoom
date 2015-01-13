<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Rooms" />
</jsp:include>



<form id="overview" method="POST" action="<c:url value='/reservations/reserve/confirm/${progressId}' />" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

  Kunde: ${reservationInProgress.customer.firstName} ${reservationInProgress.customer.lastName} <br/> <br/>
  Von:  ${formattedDateFrom}  <br/>   Bis: ${formattedDateTo} <br/> <br/>


  <br/>

  <c:forEach items="${reservationInProgress.roomList}" var="room">
  Zimmer: ${room.roomNbr} als
    <c:if test="${room.bookedRoomTyp=='SINGLE_ROOM'}" >
      Einzelzimmer
    </c:if>
    <c:if test="${room.bookedRoomTyp=='SINGLE_ROOM_ONE_CHILD'}" >
      Einzelzimmer mit Kind
    </c:if>
    <c:if test="${room.bookedRoomTyp=='SINGLE_ROOM_TWO_CHILDREN'}" >
      Doppelzimmer
    </c:if>
    <c:if test="${room.bookedRoomTyp=='DOUBLE_ROOM'}" >
      Doppelzimmer mit Kind
    </c:if>
    <c:if test="${room.bookedRoomTyp=='DOUBLE_ROOM_ONE_CHILD'}" >
      Doppelzimmer mit 2 Kindern
    </c:if>
    <c:if test="${room.bookedRoomTyp=='THREE_PERSONS'}" >
      Dreifachbelegung
    </c:if>
    <br/>
  </c:forEach>

  <br/>
  <br/>
  Gesamtpreis:  ${reservationInProgress.roomPrice} <br/>



  <div class="btn-group">
    <input type="submit" id="back" class="btn btn-default" name="back" value="Zurueck"/>
    <input type="submit" id="cancel" class="btn btn-danger" name="cancel" value="Abbrechen"/>
    <input type="submit" id="confirm" class="btn btn-success" name="confirm" value="Bestaetigen"/>
  </div>
</form>


<jsp:include page="footer.jsp" />