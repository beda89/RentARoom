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
    <h1>${customer.getFirstName()} ${customer.getLastName()}</h1>

    <table class="table table-striped table-hover table-condensed">
      <thead>
        <th>#</th>
        <th>Datum von</th>
        <th>Datum bis</th>
        <th>R&auml;ume</th>
        <th>Preis</th>
        <th>Rabatt</th>
      </thead>
      <tbody>
        <td>1</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
      </tbody>
    </table>
  </c:otherwise>
</c:choose>

  <jsp:include page="footer.jsp" />