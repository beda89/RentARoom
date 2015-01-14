<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Rooms" />
</jsp:include>

<h2>Zusammenfassung</h2>

<div class="panel panel-default">
  <div class="panel-heading">
    Reservierung
  </div>
  <div class="panel-body">
    <form id="overview" method="POST" action="<c:url value='/reservations/reserve/confirm/${progressId}' />" >
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

      <div class="row">
        <div class="form-group">
          <label class="col-sm-3 control-label">Kunde</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" name="firstName" value="${reservationInProgress.customer.firstName} ${reservationInProgress.customer.lastName}"/>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="form-group">
          <label class="col-sm-3 control-label">Datum von</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" name="firstName" value="${formattedDateFrom}"/>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="form-group">
          <label class="col-sm-3 control-label">Datum bis</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" name="firstName" value="${formattedDateTo}"/>
          </div>
        </div>
      </div>

      <div class="row">
        <table class="table table-striped table-hover table-condensed">
          <thead>
            <th>#</th>
            <th>Zimmernummer</th>
            <th>Belegungsart</th>
          </thead>
          <tbody>
            <c:set var="i" value="1"></c:set>
            <c:forEach items="${reservationInProgress.roomList}" var="room">
              <tr>
                <td>${i}</td>
                <td>${room.roomNbr}</td>
                <td>
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
                </td>
              </tr>
              <c:set var="i" value="${i + 1}"></c:set>
            </c:forEach>
          </tbody>
        </table>
      </div>

      <div class="row">
        <div class="form-group">
          <label class="col-sm-3 control-label">Gesamtpreis</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" name="firstName" value="${reservationInProgress.roomPrice} &euro;"/>
          </div>
        </div>
      </div>

      <br/>


      <a href="<c:url value='/reservations/reserve/step3/${progressId}' />">
      <input type="button" id="back" class="btn btn-default" name="back" value="Zur&uuml;ck"/></a>
      <a href="${base}/rooms"><input type="button" id="cancel" class="btn btn-danger" name="cancel" value="Abbrechen"/></a>
      <input type="submit" id="confirm" class="btn btn-success" name="confirm" value="Best&auml;tigen"/>
    </form>

  </div>
</div>

<jsp:include page="footer.jsp" />