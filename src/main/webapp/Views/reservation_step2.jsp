<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Rooms" />
</jsp:include>

<form id="chooseRoom" method="POST" action="<c:url value='/reservations/reserve/step2/${progressId}' />" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <h2>Zimmerbelegung</h2>

    <c:forEach items="${reservationInProgress.roomList}" var="room">
      <div class="panel panel-default">
        <div class="panel-heading">
          Zimmer <b>${room.roomNbr}</b>
        </div>
        <div class="panel-body">
          <select name="roomSelection" class="form-control">
            <option value="0" <c:if test="${room.bookedRoomTyp=='SINGLE_ROOM'}"> selected </c:if> >Einzelzimmer</option>
            <option value="1" <c:if test="${room.bookedRoomTyp=='SINGLE_ROOM_ONE_CHILD'}"> selected </c:if>>Einzelzimmer mit Kind</option>
            <option value="2" <c:if test="${room.bookedRoomTyp=='SINGLE_ROOM_TWO_CHILDREN'}"> selected </c:if>>Einzelzimmer mit zwei Kindern</option>
            <option value="3" <c:if test="${room.bookedRoomTyp=='DOUBLE_ROOM'}"> selected </c:if>>Doppelzimmer</option>
            <option value="4" <c:if test="${room.bookedRoomTyp=='DOUBLE_ROOM_ONE_CHILD'}"> selected </c:if>>Doppelzimmer mit Kind</option>
            <option value="5" <c:if test="${room.bookedRoomTyp=='THREE_PERSONS'}"> selected </c:if>>Dreifachbelegung</option>
          </select>
        </div>
      </div>
    </c:forEach>


    <a href="${base}/rooms"><input type="button" id="back" class="btn btn-default" name="back" value="Zur&uuml;ck"/></a>
    <input type="submit" id="next" class="btn btn-default" name="next" value="Weiter"/>

</form>

<jsp:include page="footer.jsp" />