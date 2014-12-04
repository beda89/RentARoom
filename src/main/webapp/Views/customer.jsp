<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Customer" />
</jsp:include>

<c:choose>
  <c:when test="${customer == null}">
    <h1>Customer not found!</h1>
  </c:when>
  <c:otherwise>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h1 class="panel-title">${customer.getFirstName()} <b>${customer.getLastName()}</b></h1>
      </div>
      <div class="panel-body">
        <div class="row">
          <div class="col-md-4">
            <c:choose>
              <c:when test="${customer.avatarUrl == null || customer.avatarUrl.isEmpty()}">
                <c:set var="avatarUrl" value='/resources/img/default-avatar.gif' />
              </c:when>
              <c:otherwise>
                <c:set var="avatarUrl" value='${customer.avatarUrl}' />
              </c:otherwise>
            </c:choose>
            <a href="${avatarUrl}"><img class="avatar" src="<c:url value='${avatarUrl}' />"></a>
          </div>
          <div class="col-md-4">
            <p>${customer.companyName}</p>
            <p><span class="glyphicon glyphicon-map-marker"></span> ${customer.address}</p>
            <p>${customer.phone}</p>
            <p>${customer.fax}</p>
            <p><a href="mailto:${customer.mail}">${customer.mail}</a></p>
            <p>${customer.homepage}</p>
          </div>
          <div class="col-md-4">
            <div class="well"><i>${customer.notes}</i></div >
          </div>
        </div>
      </div>
    </div>

    <div class="panel panel-default">
      <div class="panel-heading">Reservierungen</div>
      <table class="table table-striped table-hover table-condensed">
        <thead>
          <th>#</th>
          <th>Datum von</th>
          <th>Datum bis</th>
          <th>R&auml;ume</th>
          <th>Preis</th>
          <th>Rabatt</th>
          <th width="1%"></th>
          <th width="1%"></th>
        </thead>
        <tbody>
        <c:set var="i" value="1"></c:set>
        <c:forEach items="${reservations}" var="reservation">
          <tr>
            <td>${i}</td>
            <td>${reservation.dateFromAsString()}</td>
            <td>${reservation.dateToAsString()}</td>
            <td>
              <c:forEach items="${reservation.roomList}" var="room">
                ${room.roomNbr},
              </c:forEach>
            </td>
            <td>${reservation.roomPrice/100} &euro;</td>
            <td>${reservation.discount} %</td>
            <td width="1%"><button class="btn btn-xs btn-default" type="button">Stornieren</button></td>
            <td width="1%"><button class="btn btn-xs btn-default" type="button">Checkout</button></td>
          </tr>
          <c:set var="i" value="${i + 1}"></c:set>
        </c:forEach>
        </tbody>
      </table>
    </div>

    <div class="panel panel-default">
    <div class="panel-heading">Rechnungen</div>
      <table class="table table-striped table-hover table-condensed">
        <thead>
        <th>#</th>
        <th>Datum</th>
        <th>Betrag</th>
        <th>Bezahlt</th>
        <th width="1%"></th>
        </thead>
        <tbody>
        <c:set var="i" value="1"></c:set>
        <c:forEach items="${invoices}" var="invoice">
          <tr>
            <td>${i}</td>
            <td>${invoice.invoiceDateAsString()}</td>
            <td>${invoice.price/100} &euro;</td>
            <td>
            <c:choose>
              <c:when test="${invoice.payed}">
                <c:set var="payedClass" value='glyphicon-ok' />
              </c:when>
              <c:otherwise>
                <c:set var="payedClass" value='glyphicon-remove' />
              </c:otherwise>
            </c:choose>
              <span class="glyphicon ${payedClass}"></span>
            </td>
            <td width="1%">
              <c:if test="${!invoice.payed}">
              <button class="btn btn-xs btn-default" type="button">Bezahlen</button>
              </c:if>
            </td>
          </tr>
          <c:set var="i" value="${i + 1}"></c:set>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp" />