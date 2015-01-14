<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:useBean id="now" class="java.util.Date" />
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Customer" />
</jsp:include>

<c:choose>
  <c:when test="${customer == null}">
    <h1>Kunde nicht gefunden!</h1>
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
                <c:set var="avatarUrl" value='${base}/resources/img/default-avatar.gif' />
              </c:when>
              <c:otherwise>
                <c:set var="avatarUrl" value='${customer.avatarUrl}' />
              </c:otherwise>
            </c:choose>
            <a href="${avatarUrl}"><img class="avatar" src="<c:url value='${avatarUrl}' />"></a>
          </div>
          <div class="col-md-4">
            <c:if test="${customer.companyName != null}"><p><b>${customer.companyName}</b></p></c:if>
            <c:if test="${customer.address != null}"><p><span class="glyphicon glyphicon-map-marker"></span> ${customer.address}</p></c:if>
            <c:if test="${customer.phone != null}"><p>${customer.phone}</p></c:if>
            <c:if test="${customer.fax != null}"><p>${customer.fax}</p></c:if>
            <c:if test="${customer.mail != null}"><p><a href="mailto:${customer.mail}">${customer.mail}</a></p></c:if>
            <c:if test="${customer.homepage != null}"><p><a href="${customer.homepage}" target="_blank">${customer.homepage}</a></p></c:if>
          </div>
          <div class="col-md-4">
            <c:if test="${customer.notes != null}"><div class="well"><i>${customer.notes}</i></div ></c:if>
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
          <tr data-rar-reservation-id="${reservation.id}">
            <td>${i}</td>
            <td>${reservation.dateFromAsString()}</td>
            <td>${reservation.dateToAsString()}</td>
            <td>
              <c:forEach items="${reservation.roomList}" var="room">
                ${room.roomNbr},
              </c:forEach>
            </td>
            <td>${reservation.roomPrice} &euro;</td>
            <td><c:choose><c:when test="${reservation.discount != null}">${reservation.discount} %</c:when><c:otherwise>-</c:otherwise></c:choose></td>
            <td width="1%"><button class="btn btn-xs btn-default cancel-reservation <c:if test="${reservation.dateFrom <= now.time}">disabled</c:if>" type="button">Stornieren</button></td>
            <td width="1%"><button class="btn btn-xs btn-default checkout-reservation <c:if test="${reservation.dateFrom > now.time}">disabled</c:if>" type="button">Checkout</button></td>
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
          <th># Zimmer</th>
          <th># Tage</th>
          <th>Notizen</th>
        </thead>
        <tbody>
        <c:set var="i" value="1"></c:set>
        <c:forEach items="${invoices}" var="invoice">
          <tr>
            <td>${i}</td>
            <td>${invoice.invoiceDateAsString()}</td>
            <td>${invoice.price} &euro;</td>
            <td>${invoice.reservation.roomList.size()}</td>
            <td>${(invoice.reservation.dateTo - invoice.reservation.dateFrom)/86400000}</td>
            <td>${invoice.notes}</td>
          </tr>
          <c:set var="i" value="${i + 1}"></c:set>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </c:otherwise>
</c:choose>

<!-- Modals -->
<div class="modal fade" id="really-proceed-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Wirklich fortfahren?</h4>
      </div>
      <form role="form" action="<c:url value="${base}/" />" method="POST" class="form-horizontal">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Abbrechen</button>
          <button type="submit" class="btn btn-primary"></button>
        </div>
      </form>
    </div>
  </div>
</div>

<div class="modal fade" id="edit-customer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Kunden editieren</h4>
      </div>
      <form id="edit-customer-form" role="form" action="<c:url value="${base}/customer/${customer.id}" />" method="POST" class="form-horizontal">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div class="modal-body">
          <div class="form-group">
            <label class="col-sm-3 control-label">Vorname</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" name="firstName" value="${customer.firstName}" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Nachname</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" name="lastName" value="${customer.lastName}" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Firma</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" name="companyName" value="${customer.companyName}" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Adresse</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" name="address" value="${customer.address}" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Telefon</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" name="phone" value="${customer.phone}" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Fax</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" name="fax" value="${customer.fax}" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">E-Mail</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" name="mail" value="${customer.mail}" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Homepage</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" name="homepage" value="${customer.homepage}" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Link zu Avatar</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" name="avatarUrl" value="${customer.avatarUrl}" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">Notizen</label>
            <div class="col-sm-8">
              <textarea class="form-control" name="notes" rows="3"><c:out value="${customer.notes}"></c:out></textarea>
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