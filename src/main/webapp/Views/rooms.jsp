<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Rooms" />
</jsp:include>

<div id="roomgrid">
  <div class="row">
    <c:forEach items="${roomList}" var="room">
      <div class="col-xs-2">
        <c:if test="${room.isReserved}">
          <button class="btn btn-danger">
            <p><b>${room.roomNbr}</b></p>
          </button>
        </c:if>


        <c:if test="${!room.isReserved}">
          <button class="btn btn-success">
            <p><b>${room.roomNbr}</b></p>
          </button>
        </c:if>
      </div>
    </c:forEach>

  </div><br>
</div>

<div class="btn-group">
  <button id="edit-room" type="button" class="btn btn-default disabled">Bearbeiten</button>
  <button id="reserve-room" type="button" class="btn btn-default dropdown-toggle disabled" data-toggle="dropdown">
    Reservieren <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li><a href="#">F&uuml;r bestehenden Kunden</a></li>
    <li><a href="#">F&uuml;r neuen Kunden</a></li>
  </ul>
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