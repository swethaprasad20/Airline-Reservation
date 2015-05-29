<jsp:include page="Header.jsp"></jsp:include>

<div class="row" style="margin-left: 10px; margin-right: 10px;">
	<div class="col-md-6">
		<div class="jumbotron j_group group1">
			<label class="title">Fetch Flight and Weekdays</label>
			<div class="alert alert-danger" id="error" role="alert"
				style="display: none;">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">Error:</span> <span
					id="errorMsg"></span>
			</div>
			<div class="alert alert-success" role="alert"
				style="width: 150px; margin-left: 430px;">Showing 0 results</div>
			<form class="form-inline">
				<div class="form-group">
					<input class="form-control group1" id="departureAirportCode"
						placeholder="Departure Airport Code">

				</div>
				<div class="form-group">
					<input class="form-control group1" id="arrivalAirportCode"
						placeholder="Arrival Airport Code">
				</div>
				<div class="input-group">
					<select onchange="sethops()" id="maxHops">
						<option selected value="">Max Legs</option>
						<option value="0">1</option>
						<option value="1">2</option>
						<option value="2">3</option>
					</select>

				</div>
				<button type="button" class="btn btn-primary"
					onclick="getFlightWeekDays()">Search</button>
			</form>
		</div>
	</div>
	<div class="col-md-6">
		<div class="jumbotron j_group group2">
			<label class="title">Fetch Seat Availibility</label>
			<div class="alert alert-danger" id="error" role="alert"
				style="display: none;">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">Error:</span> <span
					id="errorMsg"></span>
			</div>
			<div class="alert alert-success" role="alert"
				style="width: 150px; margin-left: 430px;">Showing 0 results</div>
			<form class="form-inline">
				<div class="form-group">
					<input class="form-control group1" id="FlightNumberSeat"
						placeholder="Flight Number">
				</div>
				<div class="form-group">
					<input class="form-control group1" id="date"
						placeholder="Date (YYYY-MM-DD)">
				</div>
				<button type="button" class="btn btn-primary"
					onclick="getSeatAvailibility()">Search</button>
			</form>
		</div>


	</div>
</div>
<div class="row" style="margin-left: 10px; margin-right: 10px;">
	<!--	<div class="col-md-6">
		<div class="jumbotron j_group group3">

			<label class="title">Fetch Flight Leg Information</label>
			<div class="alert alert-danger" id="error" role="alert"
				style="display: none;">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">Error:</span> <span
					id="errorMsg"></span>
			</div>
			<div class="alert alert-success" role="alert" style="width:150px;margin-left:430px;">Showing 0 results</div>
			<form class="form-inline">
				<div class="form-group">
					<input class="form-control group1" id="flightNumberLeg"
						placeholder="Flight Number">
				</div>
				<button type="button" class="btn btn-primary"
					onclick="getFlightLeg()">Search</button>
			</form>
		</div>

	</div>-->
	<div class="col-md-6">
		<div class="jumbotron j_group group4">
			<label class="title">Fetch Fare Information</label>
			<div class="alert alert-danger" id="error" role="alert"
				style="display: none;">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">Error:</span> <span
					id="errorMsg"></span>
			</div>
			<div class="alert alert-success" role="alert"
				style="width: 150px; margin-left: 430px;">Showing 0 results</div>
			<form class="form-inline">
				<div class="form-group">
					<input class="form-control group1" id="flightNumberFare"
						placeholder="Flight Number">
				</div>

				<button type="button" class="btn btn-primary"
					onclick="getFareInfo()">Search</button>
			</form>
		</div>
	</div>
	<div class="col-md-6">
		<div class="jumbotron j_group group5">
			<label class="title">Fetch Customer Information</label>
			<div class="alert alert-danger" id="error" role="alert"
				style="display: none;">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">Error:</span> <span
					id="errorMsg"></span>
			</div>
			<div class="alert alert-success" role="alert"
				style="width: 150px; margin-left: 430px;">Showing 0 results</div>
			<form class="form-inline">
				<div class="form-group">
					<input class="form-control group1" id="flightNumberCust"
						placeholder="Flight Number">

				</div>
				<div class="form-group">
					<input class="form-control group1" id="custDate"
						placeholder="Date (YYYY-MM-DD)">
				</div>
				<button type="button" class="btn btn-primary"
					onclick="getCustomerInfo()">Search</button>
			</form>
		</div>

	</div>
</div>

<div class="row" style="margin-left: 10px; margin-right: 10px;">

	<div class="col-md-6">
		<div class="jumbotron j_group group6">
			<label class="title">Fetch Flight Information of a Customer</label>
			<div class="alert alert-danger" id="error" role="alert"
				style="display: none;">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">Error:</span> <span
					id="errorMsg"></span>
			</div>
			<div class="alert alert-success" id="abc" role="alert"
				style="width: 150px; margin-left: 430px;">Showing 0 results</div>
			<form class="form-inline">
				<div class="form-group">
					<input class="form-control group1" id="CustomerName"
						placeholder="Customer Name">
				</div>

				<button type="button" class="btn btn-primary"
					onclick="getFlightInfoOfACustomer()">Search</button>
			</form>
		</div>
	</div>

</div>
<div style="max-height: 240px; overflow-y: auto">
	<table id="resultTable" class="table table-bordered"
		style="width: 96%; margin-left: 24px;">
	</table>
</div>
<div class="modal fade" id="myModal">
	<div class="modal-dialog" style="width: 60%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="resultHeader">Result</h4>
			</div>
			<div class="modal-body" style="max- height: 400px; overflow-y: auto">
				<table id="resultTable1" class=" table table-bordered"
					style="width: 100%"></table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

</body>
<script type="text/javascript">
	var hops="";

	function sethops() {
		
		hops = $("#maxHops").val();
		
	}
	
	$(document).ready(function() {
		$('.alert-success').css("display", "none");
	});
	function getFlightWeekDays() {
		var deptCode = $('#departureAirportCode').val();
		var arrCode = $('#arrivalAirportCode').val();
	
		if ($.trim(deptCode) == "" || $.trim(arrCode) == "" || hops =="") {
			$('.group1 #errorMsg').html(
					"Max hops, arrival and departure airport codes are mandatory.");
			$('.group1 #error').css("display", "");
			$(".group1 #error").fadeOut(3000, function() {
			});
		} else {

			$("#resultHeader").html(
					"Information of flights departing from " + deptCode
							+ " and arriving at " + arrCode);

			$
					.ajax({
						url : "getFlightWeekdaysInformation.json?arrAirportCode="
								+ arrCode + "&deptAirportCode=" + deptCode+"&hops="+hops,
						success : function(result) {
							$('.alert-success').css("display", "none");
							
							$('.group1 .alert-success').css("display", "");
							var tableData = "<tr class='active'><th>Flight Number</th><th>Weekdays</th></tr>";
							var i = 0;
							for ( var flight in result) {
								if($.trim(result[flight]["weekdays"])!=""){
								// alert(result[flight]["flightNumber"]);
								if (i % 2 == 0) {
									tableData += "<tr>";

								} else {
									tableData += "<tr class='info'>";

								}
								tableData += "<td>"
										+ result[flight]["flightNumber"]
										+ "</td><td>"
										+ result[flight]["weekdays"]
										+ "</td></tr>";
								i++;

							}
						}
							$('.group1 .alert-success').html(
									"Showing " + (i) + " result(s)");
							$("#resultTable").html("");
							$("#resultTable").html(tableData);

						}
					});

		}
	}

	function getSeatAvailibility() {
		var flightNumber = $('#FlightNumberSeat').val();
		var date = $('#date').val();
		if ($.trim(flightNumber) == "" || $.trim(date) == "") {
			$('.group2 #errorMsg')
					.html("Flight number and date are mandatory.");
			$('.group2 #error').css("display", "");
			$(".group2 #error").fadeOut(3000, function() {
			});
		} else {
			$("#resultHeader")
					.html("Leg information of flight " + flightNumber);

			$
					.ajax({
						url : "getSeatAvailibility.json?flightNumber="
								+ flightNumber + "&date=" + date,
						success : function(result) {
							$('.alert-success').css("display", "none");
							$('.group2 .alert-success').html("Showing result");
							$('.group2 .alert-success').css("display", "");
							var tableData = "<tr class='active'><th>Number of Available Seats</th></tr>";

							if (result == "-1") {
								tableData += "<tr><td> Flight not scheduled</td></tr>";
							} else {
								tableData += "<tr><td>" + result + "</td></tr>";
							}

							$("#resultTable").html("");
							$("#resultTable").html(tableData);

						}
					});
		}

	}

	function getFlightLeg() {

		var flightNumber = $('#flightNumberLeg').val();
		if ($.trim(flightNumber) == "") {
			$('.group3 #errorMsg').html("Flight number is mandatory.");
			$('.group3 #error').css("display", "");
			$(".group3 #error").fadeOut(3000, function() {
			});
		} else {
			$("#resultHeader")
					.html("Leg information of flight " + flightNumber);

			$
					.ajax({
						url : "getFlightLegInfo.json?flightNumber="
								+ flightNumber,
						success : function(result) {
							$('.alert-success').css("display", "none");
							$('.group3 .alert-success').html(
									"Showing " + result.length + " result(s)");
							$('.group3 .alert-success').css("display", "");
							var tableData = "<tr class='active'><th>Leg Number</th><th>Flight Date</th><th>Number of available seats </th><th>Airplane ID</th>"
									+ "<th>Departure Airport Code</th><th>Departure Time</th><th>Arrival Airport Code</th><th>Arrival Time</th></tr>";
							var i = 0;
							for ( var flight in result) {
								// alert(result[flight]["flightNumber"]);
								if (i % 2 == 0) {
									tableData += "<tr>";
								} else {
									tableData += "<tr class='info'>";

								}
								tableData += "<td>"
										+ result[flight]["legNumber"]
										+ "</td><td>"
										+ result[flight]["date"]
										+ "</td><td>"
										+ result[flight]["numberOfAvailiableSeats"]
										+ "</td><td>"
										+ result[flight]["airplaneId"]
										+ "</td>"
										+ "<td>"
										+ result[flight]["departureAirportCode"]
										+ "</td><td>"
										+ result[flight]["deptTime"]
										+ "</td><td>"
										+ result[flight]["arrivalAirportCode"]
										+ "</td><td>"
										+ result[flight]["arrivalTime"]
										+ "</td></tr>";
								i++;

							}
							$("#resultTable").html("");
							$("#resultTable").html(tableData);

						}
					});

		}

	}

	function getFareInfo() {

		var flightNumber = $('#flightNumberFare').val();
		if ($.trim(flightNumber) == "") {
			$('.group4 #errorMsg').html("Flight number is mandatory.");
			$('.group4 #error').css("display", "");
			$(".group4 #error").fadeOut(3000, function() {
			});
		} else {
			$("#resultHeader").html(
					"Fare information of flight " + flightNumber);
			$
					.ajax({
						url : "getFareInformation.json?flightNumber="
								+ flightNumber,
						success : function(result) {
							$('.alert-success').css("display", "none");
							$('.group4 .alert-success').html(
									"Showing " + result.length + " result(s)");
							$('.group4 .alert-success').css("display", "");
							var tableData = "<tr class='active'><th>Fare code</th><th>Amount</th><th>Restriction </th></tr>";
							var i = 0;
							for ( var flight in result) {
								// alert(result[flight]["flightNumber"]);
								if (i % 2 == 0) {
									tableData += "<tr>";
								} else {
									tableData += "<tr class='info'>";

								}
								tableData += "<td>"
										+ result[flight]["fareCode"]
										+ "</td><td>$"
										+ result[flight]["amount"]
										+ "</td><td>"
										+ result[flight]["restirction"]
										+ "</td></tr>";
								i++;

							}
							$("#resultTable").html("");
							$("#resultTable").html(tableData);

						}
					});
		}

	}

	function getCustomerInfo() {
		var flightNumber = $('#flightNumberCust').val();
		var flightDate=$('#custDate').val();
		if ($.trim(flightNumber) == "" || $.trim(flightDate)=="") {
			$('.group5 #errorMsg').html("Flight number & date are mandatory.");
			$('.group5 #error').css("display", "");

			$(".group5 #error").fadeOut(3000, function() {
			});
		} else {
			$("#resultHeader").html(
					"Passengers travelling in flight " + flightNumber);
			$
					.ajax({
						url : "getCustomerInformation.json?flightNumber="
								+ flightNumber+"&flightDate="+flightDate,
						success : function(result) {
							$('.alert-success').css("display", "none");
							$('.group5 .alert-success').html(
									"Showing " + result.length + " result(s)");
							$('.group5 .alert-success').css("display", "");
							var tableData = "<tr class='active'><th>Passenger Name</th><th>Phone Number</th><th>Seat Number</th></tr>";
							var i = 0;
							for ( var flight in result) {
								// alert(result[flight]["flightNumber"]);
								if (i % 2 == 0) {
									tableData += "<tr>";
								} else {
									tableData += "<tr class='info'>";

								}
								tableData += "<td>"
										+ result[flight]["passengerName"]
										+ "</td><td>"
										+ result[flight]["contactInfo"]
										+ "</td><td>"
										+ result[flight]["seatNum"]
										+ "</td></tr>";
								i++;

							}
							$("#resultTable").html("");
							$("#resultTable").html(tableData);

						}
					});
		}

	}

	function getFlightInfoOfACustomer() {
		var custName = $('#CustomerName').val();
		if ($.trim(custName) == "") {
			$('.group6 #errorMsg').html("Customer name is mandatory.");
			$('.group6 #error').css("display", "");
			$(".group6 #error").fadeOut(3000, function() {
			});
		} else {
			$("#resultHeader").html(
					"Flight information of passenger  " + custName);
			$
					.ajax({
						url : "getFlightInfoOfCust.json?custName=" + custName,
						success : function(result) {
							$('.alert-success').css("display", "none");
							$('.group6 .alert-success').html(
									"Showing " + result.length + " result(s)");

							$('.group6 .alert-success').css("display", "");
							var tableData = "<tr class='active'><th>Flight Number</th><th>Date</th><th>Seat Number</th><th>Airplane Id</th><th>Departure Time</th><th>Arrival Time</th></tr>";
							var i = 0;
							for ( var flight in result) {
								// alert(result[flight]["flightNumber"]);
								if (i % 2 == 0) {
									tableData += "<tr>";
								} else {
									tableData += "<tr class='info'>";

								}
								tableData += "<td>"
										+ result[flight]["flightNumber"]
										+ "</td>	<td>" + result[flight]["date"]
										+ "</td><td>"
										+ result[flight]["seatNumber"]
										+ "</td><td>"
										+ result[flight]["airplaneId"]
										+ "</td><td>"
										+ result[flight]["deptTime"]
										+ "</td><td>"
										+ result[flight]["arrivalTime"]
										+ "</td></tr>";
								i++;

							}
							$("#resultTable").html("");
							$("#resultTable").html(tableData);

						}
					});
		}

	}
</script>
</html>