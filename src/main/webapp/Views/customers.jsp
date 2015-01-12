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
  <jsp:param name="page" value="Customerlist" />
</jsp:include>

<div class="list-group">
  <c:forEach items="${customers}" var="customer">
    <a href="/customer/${customer.id}" class="list-group-item">${customer.firstName} <b>${customer.lastName}</b></a>
  </c:forEach>
</div>

<jsp:include page="footer.jsp" />