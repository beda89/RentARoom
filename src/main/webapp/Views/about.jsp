<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
  <jsp:param name="page" value="About" />
</jsp:include>

<img src="<c:url value='/resources/img/tulogo.jpg'/>" class="img-responsive" />

<h3>Rent a Room - Reservierungen</h3>

<p class="subtitle"><i>183.173 Seminar aus Software Engineeringng WS 2014/2015</i></p>

<div class="row">
  <div class="col-md-2">Peter Eder</div>
  <div class="col-md-1"><a href="mailto:petereder89@gmail.com">petereder89@gmail.com</a></div>
</div>
<div class="row">
  <div class="col-md-2">Christopher Simerle</div>
  <div class="col-md-1"><a href="mailto:simerle.c@gmail.com">simerle.c@gmail.com</a></div>
</div>
<div class="row">
  <div class="col-md-2">Christian Detamble</div>
  <div class="col-md-1"><a href="mailto:christian.detamble@student.tuwien.ac.at">christian.detamble@student.tuwien.ac.at</a></div>
</div>

<jsp:include page="footer.jsp" />
