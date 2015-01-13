<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp">
  <jsp:param name="page" value="Rooms" />
</jsp:include>

<h2>Kundenauswahl</h2>

<div class="panel panel-default">
  <div class="panel-heading">
    <div class="radio">
      <label>
        <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
        Bestehender Kunde
      </label>
    </div>
   </div>
  <div class="panel-body">
    <form id="existingCustomer" method="POST" action="${base}/reservations/reserve/step3_1/${progressId}">
      <input type="text" name="userSelection" id="autocomplete_reservation" title="Kundenauswahl" class="form-control" />
      <input type="hidden" id="selectedCustomerId" name="selectedCustomerId" />
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
  </div>
</div>

<h4>oder</h4>

<div class="panel panel-default">
  <div class="panel-heading">
    <div class="radio">
      <label>
        <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
        Neuer Kunde
      </label>
    </div>
  </div>
  <div class="panel-body">
    <form id="createCustomer" method="POST" action="${base}/reservations/reserve/step3_2/${progressId}">
      <jsp:include page="newCustomerFormElems.jsp"></jsp:include>
    </form>
  </div>
</div>

<div class="btn-group">
  <input type="submit" id="back" class="btn btn-default" name="back" value="Zur&uuml;ck"/>
  <input type="submit" id="next" class="btn btn-default" name="back" value="Weiter"/>
</div>

<script>
  $('#autocomplete_reservation').autocomplete({
    serviceUrl: '${base}/autocomplete/names',
    onSelect: function(suggestion) {
        $('#selectedCustomerId').val(suggestion.data);
    }
  });
</script>


<jsp:include page="footer.jsp" />