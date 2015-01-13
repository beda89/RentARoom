<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Rooms" />
</jsp:include>

<div class="btn-group">
  <input type="submit" id="back" class="btn btn-default" name="back" value="Zur&uuml;ck"/>
</div>

<form id="chooseCustomer" method="POST" action="<c:url value='/reservations/reserve/step3_1/${progressId}' />" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>


  <div class="existing_customer">
    Bestehender Kunde:
    <input type="text" name="userSelection" id="autocomplete_reservation" title="Kundenauswahl"/>
  </div>

  <input type="submit" class="btn btn-default" name="next" value="Weiter"/>
  <input type="hidden" id="selectedCustomerId" name="selectedCustomerId"  />
</form>

<form id="createCustomer" method="POST" action="<c:url value='/reservations/reserve/step3_2/${progressId}' />" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

  TODO XXXX


  <input type="submit" class="btn btn-default" name="next" value="Weiter"/>
</form>

<script>
  $('#autocomplete_reservation').autocomplete({
    serviceUrl: '${base}/autocomplete/names',
    onSelect:function(suggestion){
        $('#selectedCustomerId').val(suggestion.data);
    }
  });
</script>


<jsp:include page="footer.jsp" />