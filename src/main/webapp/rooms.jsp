<jsp:include page="header.jsp">
  <jsp:param name="page" value="Notifications" />
  <jsp:param name="noheading" value="true" />
</jsp:include>

<div id="roomgrid">
  <div class="row">
    <div class="col-xs-2">
      <button class="btn btn-danger">
      <p><b>E01</b></p>14€
      </button>
    </div>
  </div><br>
</div>

<div class="btn-group">
  <button id="edit-room" type="button" class="btn btn-default disabled">Bearbeiten</button>
  <button id="reserve-room" type="button" class="btn btn-default dropdown-toggle disabled" data-toggle="dropdown">
    Reservieren <span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li><a href="#">Für bestehenden Kunden</a></li>
    <li><a href="#">Für neuen Kunden</a></li>
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
