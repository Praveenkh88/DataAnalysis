<!DOCTYPE html>
<html lang="en">
<head>
  <title>Data Analysis</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/pie.js"></script>
<script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
<link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
<style>
#chartdiv {
  width: 100%;
  height: 500px;
}
</style>

</head>
<body>
<input type="hidden" name="jsonStoreResult" id ="jsonStoreResult" value='${sessionScope.jsonStoreResult}'>
<br/>
<br/>

<div class="container">  
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#dataSummary">Data Summary</a></li>
    <li><a data-toggle="tab" href="#reports">Reports</a></li>
    <li><a data-toggle="tab" href="#graphs">Graphs</a></li>
  </ul>

  <div class="tab-content">
    <div id="dataSummary" class="tab-pane fade in active">
      <br/><br/>
    <table style="width: 200px;" border = "2px" class="table-responsive col-md-4">
    <thead>
      <tr>
        <th colspan="2"><center>Gender</center></th>
      </tr>
      <tr>
        <th>Description</th>
        <th>Count</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Male</td>
        <td>${sessionScope.maleGenCount}</td>
      </tr>
      <tr>
        <td>Female</td>
        <td>${sessionScope.femaleGenCount}</td>
      </tr>
      <tr>
        <td>Unknown</td>
        <td>${sessionScope.NAGen}</td>
      </tr>
      <tr>
        <td>NA</td>
        <td>${sessionScope.UnknownGen}</td>
      </tr>
      <tr>
        <td>Total</td>
        <td>${sessionScope.totalGen}</td>
      </tr>
    </tbody>
  </table>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <table style="width: 200px;" border = "2px" class="table-responsive col-md-4">
    <thead>
      <tr>
        <th colspan="2"><center>Marital Status</center></th>
      </tr>
      <tr>
        <th>Description</th>
        <th>Count</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Married</td>
        <td>${sessionScope.marriedCount}</td>
      </tr>
      <tr>
        <td>Single</td>
        <td>${sessionScope.singleCount}</td>
      </tr>
      <tr>
        <td>Unknown</td>
        <td>${sessionScope.NAMS}</td>
      </tr>
      <tr>
        <td>NA</td>
        <td>${sessionScope.UnknownMS}</td>
      </tr>
      <tr>
        <td>Total</td>
        <td>${sessionScope.totalMS}</td>
      </tr>
    </tbody>
  </table>
  
  <br/><br/><br/><br/><br/><br/><br/><br/>
  
  <table style="width: 800px;" border = "2px">
    <thead>
      <tr>
        <th></th>
        <th>Record Count</th>
        <th>NULL Count </th>
        <th>Zero Count </th>
        <th>Mean</th>
        <th>Median</th>
        <th>Standard Deviation</th>
      </tr>
    <tbody>
      <tr>
        <td>UNITS_SOLD</td>
        <td>${sessionScope.totalRecordCount}</td>
        <td>${sessionScope.unitsSoldNullCount}</td>
        <td>${sessionScope.unitsSoldZeroCount}</td>
        <td>${sessionScope.unitsSoldMean}</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>SALES_VALUE</td>
        <td>${sessionScope.totalRecordCount}</td>
        <td>${sessionScope.salesValueNullCount}</td>
        <td>${sessionScope.salesValueZeroCount}</td>
        <td>${sessionScope.salesValueMean}</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>DISCOUNT</td>
        <td>${sessionScope.totalRecordCount}</td>
        <td>${sessionScope.discountNullCount}</td>
        <td>${sessionScope.discountZeroCount}</td>
        <td>${sessionScope.discountMean}</td>
        <td></td>
        <td></td>
      </tr>
    </tbody>
  </table>
            
    </div>
    <div id="reports" class="tab-pane fade">
     
    </div>
    <div id="graphs" class="tab-pane fade">
    <div id="chartdiv"></div>
    <script>
    var chart = AmCharts.makeChart( "chartdiv", {
    	  "type": "pie",
    	  "theme": "light",
    	  "dataProvider": [ {
    	    "country": "Dover Mall",
    	    "litres": 0.19
    	  }, {
    	    "country": "Eastland Mall",
    	    "litres": 0.26
    	  }, {
    	    "country": "Brookwood",
    	    "litres": 1.25
    	  }, {
    	    "country": "Westfield North County",
    	    "litres": 4.84
    	  }, {
    	    "country": "Elements.com",
    	    "litres": 13.08
    	  }, {
    	    "country": "German Town",
    	    "litres": 5.77
    	  }, {
    	    "country": "Greenwood Park Mall:",
    	    "litres": 3.05
    	  }, {
    	    "country": "Evansville",
    	    "litres": 1.75
    	  }, {
    	    "country": "German Town",
    	    "litres": 2.07
    	  } ],
    	  "valueField": "litres",
    	  "titleField": "country",
    	   "balloon":{
    	   "fixedPosition":true
    	  },
    	  "export": {
    	    "enabled": true
    	  }
    	} );
    </script>
         
    </div>
  </div>
</div>

</body>
</html>
