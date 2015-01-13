<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Rooms" />
</jsp:include>




<div id="dateSelection">
  <form id="dateSelectionForm" method="GET" action="<c:url value='/getRoomsForDate' />" >
    <input type="text" id="selectedDate" name="selectedDate" value="${selectedDate}" />
    <input type="submit" id="submitTimeBtn" class="btn btn-default" name="submitTimeBtn" value="Zeit auswaehlen"/>
  </form>
</div>

<script>
  $(function() {
    $( "#selectedDate" ).datepicker({
      dateFormat: "dd.mm.yy"
    });
  });
</script>

<form id="reservationForm" method="POST" action="<c:url value='/reservations/reserve' />" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="btn-group">
  <input type="submit" id="reserve-room" class="btn btn-default" name="reserve-room" value="Reservieren"/>

 <!-- <button id="reserve-room" type="button" class="btn btn-default dropdown-toggle disabled" data-toggle="dropdown">
    Reservieren <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li><a href="#">F&uuml;r bestehenden Kunden</a></li>
    <li><a href="#">F&uuml;r neuen Kunden</a></li>
  </ul> -->
</div>

<div id="roomgrid">

  <table class="overviewTable">
      <tr>
        <td>
        </td>
        <c:forEach items="${roomOverview.headerList}" var="day" varStatus="loop">
          <td class="<c:if test="${loop.index==7}">today</c:if> dayHeaderTd">
            <div class="dayHeaderDiv">
            ${day.dateString}
            </div>
          </td>
        </c:forEach>
      </tr>

      <c:forEach items="${roomOverview.rooms}" var="room">
        <tr data-rar-room-id="${room.id}">
          <td class="roomColumn">
            <button type="button" class="btn btn-success edit-room">
              ${room.roomNbr}
            </button>
          </td>
            <c:forEach items="${room.dayOverview}" var="day" varStatus="loop">
            <td <c:if test="${loop.index==7}">class="today"</c:if>>
              <c:if test="${!day.isReserved}">
                <c:if test="${!day.isWeekend}">
                  <input type="checkbox" class="roomCheckbox" name="roomCheckbox" id="${room.roomNbr}_${day.selectBoxId}" value="${room.roomNbr}_${day.selectBoxId}">
                  <label class="roomCheckbox available" for="${room.roomNbr}_${day.selectBoxId}"></label>
                </c:if>
                <c:if test="${day.isWeekend}">
                  <input type="checkbox" class="roomCheckbox" name="roomCheckbox" id="${room.roomNbr}_${day.selectBoxId}" value="${room.roomNbr}_${day.selectBoxId}">
                  <label class="roomCheckbox available weekend" for="${room.roomNbr}_${day.selectBoxId}"></label>
                </c:if>
              </c:if>
              <c:if test="${day.isReserved}">
                <input type="checkbox" class="roomCheckbox" disabled id="${room.roomNbr}_${day.selectBoxId}" name="roomCheckbox" value="${room.roomNbr}_${day.selectBoxId}">
                <label class="roomCheckbox reserved" for="${room.roomNbr}_${day.selectBoxId}"></label>
              </c:if>
            </td>
            </c:forEach>
        </tr>
      </c:forEach>
  </table>
</div>
</form>

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

<!-- Modals -->
<div class="modal fade" id="edit-rooms" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Zimmer editieren</h4>
      </div>
      <form id="edit-room-form" role="form" action="<c:url value="${base}/rooms/" />" method="POST" class="form-horizontal">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div class="modal-body">
          <div class="form-group">
            <label class="col-sm-3 control-label">Raumnummer</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" disabled="disabled" name="roomNbr" value="" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Max. Personen</label>
            <div class="col-sm-8">
              <input type="number" class="form-control" name="maxPersons" value="" min="0" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Preis EZ</label>
            <div class="col-sm-8">
              <input type="number" class="form-control" name="price_singleRoom" value="" min="0" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Preis EZ mit Kind</label>
            <div class="col-sm-8">
              <input type="number" class="form-control" name="price_singleRoomOneChild" value="" min="0" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Preis EZ mit 2 Kindern</label>
            <div class="col-sm-8">
              <input type="number" class="form-control" name="price_singleRoomTwoChildren" value="" min="0" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Preis DZ</label>
            <div class="col-sm-8">
              <input type="number" class="form-control" name="price_doubleRoom" value="" min="0" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Preis DZ mit Kind</label>
            <div class="col-sm-8">
              <input type="number" class="form-control" name="price_doubleRoomOneChild" value="" min="0" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Preis Dreifachbelegung</label>
            <div class="col-sm-8">
              <input type="number" class="form-control" name="price_threePersons" value="" min="0" />
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
          <button type="submit" class="btn btn-primary">Speichern</button>
        </div>
      </form>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp" />