<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Rooms" />
</jsp:include>



<div id="dateSelection">
  <input type="text" id="date" name="date">
</div>

<div class="btn-group">
  <button id="reserve-room" type="button" class="btn btn-default dropdown-toggle disabled" data-toggle="dropdown">
    Reservieren <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li><a href="#">F&uuml;r bestehenden Kunden</a></li>
    <li><a href="#">F&uuml;r neuen Kunden</a></li>
  </ul>
</div>

<div id="roomgrid">
  <table class="overviewTable">
      <tr>
        <td>
        </td>
        <c:forEach items="${roomOverview.headerList}" var="day">
          <td class="dayHeaderTd">
            <div class="dayHeaderDiv">
            ${day.dateString}
            </div>
          </td>
        </c:forEach>
      </tr>

      <c:forEach items="${roomOverview.rooms}" var="room">
        <tr>
          <td class="roomColumn">
            <button class="btn btn-success">
              ${room.roomNbr}
            </button>
          </td>
            <c:forEach items="${room.dayOverview}" var="day">
            <td>
              <c:if test="${!day.isReserved}">
                <c:if test="${!day.isWeekend}">
                  <input type="checkbox" class="roomCheckbox" name="${room.roomNbr}_${day.selectBoxId}" id="${room.roomNbr}_${day.selectBoxId}">
                  <label class="roomCheckbox available" for="${room.roomNbr}_${day.selectBoxId}"></label>
                </c:if>
                <c:if test="${day.isWeekend}">
                  <input type="checkbox" class="roomCheckbox" name="${room.roomNbr}_${day.selectBoxId}" id="${room.roomNbr}_${day.selectBoxId}">
                  <label class="roomCheckbox available weekend" for="${room.roomNbr}_${day.selectBoxId}"></label>
                </c:if>
              </c:if>
              <c:if test="${day.isReserved}">
                <input type="checkbox" class="roomCheckbox" disabled id="${room.roomNbr}_${day.selectBoxId}" name="${room.roomNbr}_${day.selectBoxId}">
                <label class="roomCheckbox reserved" for="${room.roomNbr}_${day.selectBoxId}"></label>
              </c:if>
            </td>
            </c:forEach>
        </tr>
      </c:forEach>
  </table>
</div>

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

<jsp:include page="footer.jsp" />