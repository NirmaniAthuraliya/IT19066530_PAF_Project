<%@ page import = "model.PAF.Fund" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Fund Management</title>
	
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/funds.js"></script>
</head>
<body>

	<form id="formFund" name="formFund" method="post" action="Fund.jsp">
	
	<h1> Fund Management </h1>
	<br/>
		
 		FundBody Code:
		<input id="fundCode" name="fundCode" type="text" class="form-control form-control-sm">
		<br/> 
		FundBody Name:
		<input id="fundName" name="fundName" type="text" class="form-control form-control-sm">
		<br/>
		FundBody Email:
		<input id="fundEmail" name="fundEmail" type="text" class="form-control form-control-sm">
		<br/> 
		FundBody Address:
		<input id="fundAddress" name="fundAddress" type="text" class="form-control form-control-sm">
		<br/>
		FundBody Contact No:
		<input id="fundContact" name="fundContact" type="text" class="form-control form-control-sm">
		<br/> 
		FundBody Amount:
		<input id="fundAmount" name="fundAmount" type="text" class="form-control form-control-sm">
		<br/>
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidFundIDSave" name="hidFundIDSave" value="">
		
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	
	<div id="divFundGrid">
		<%
			Fund fundObj = new Fund();
			out.print(fundObj.readFunds());
		%>
	</div>
	
</body>
</html>