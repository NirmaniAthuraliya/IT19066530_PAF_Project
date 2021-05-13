$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
 	{
 		$("#alertSuccess").hide();
 	}
 	$("#alertError").hide();
});

// CLIENT-MODEL================================================================
function validateFundForm() {
    // CODE -------------------------------
    if ($("#fundCode").val().trim() == "") {
        return "Insert Fund Code.";
    }
    // NAME -------------------------------
    if ($("#fundName").val().trim() == "") {
        return "Insert Fund Name.";
    }
    // EMAIL-------------------------------
    if ($("#fundEmail").val().trim() == "") {
        return "Insert Fund Email.";
    }
    // ADDRESS -----------------------------
    if ($("#fundAddress").val().trim() == "") {
        return "Insert Fund Address.";
    }
    // CONTACT -----------------------------
    if ($("#fundContact").val().trim() == "") {
        return "Insert Fund Contact Number.";
    }
    // AMOUNT -------------------------------
    if ($("#fundAmount").val().trim() == "") {
        return "Insert Fund Amount.";
    }
    // is numerical value
    var fundPrice = $("#fundAmount").val().trim();
    if (!$.isNumeric(fundPrice)) {
        return "Insert a numerical value for Fund Amount.";
    }
    // convert to decimal price
    $("#fundAmount").val(parseFloat(fundPrice).toFixed(2));
    
    return true;
}


// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
 	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();

	// Form validation-------------------
	var status = validateFundForm();
	if (status != true)
 	{
 		$("#alertError").text(status);
 		$("#alertError").show();
 		return;
 	}

	// If valid------------------------
 	var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT";
 	$.ajax(
	{
 		url : "fundAPI",
 		type : type,
 		data : $("#formFund").serialize(),
 		dataType : "text",
 		complete : function(response, status)
 		{
 			onFundSaveComplete(response.responseText, status);
 		}
	});
	
});

function onFundSaveComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
			 $("#alertSuccess").text("Successfully saved.");
			 $("#alertSuccess").show();
			 $("#divFundGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
	} else if (status == "error")
	{
		 $("#alertError").text("Error while saving.");
		 $("#alertError").show();
	} else
	{
		 $("#alertError").text("Unknown error while saving..");
		 $("#alertError").show();
	}
	$("#hidFundIDSave").val("");
 	$("#formFund")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 	$("#hidFundIDSave").val($(this).closest("tr").find('#hidFundIDUpdate').val());
 	$("#fundCode").val($(this).closest("tr").find('td:eq(0)').text());
 	$("#fundName").val($(this).closest("tr").find('td:eq(1)').text());
 	$("#fundEmail").val($(this).closest("tr").find('td:eq(2)').text());
 	$("#fundAddress").val($(this).closest("tr").find('td:eq(3)').text());
	$("#fundContact").val($(this).closest("tr").find('td:eq(4)').text());
	$("#fundAmount").val($(this).closest("tr").find('td:eq(5)').text());
});

// DELETE===========================================
$(document).on("click", ".btnRemove", function(event)
{
 	$.ajax(
 	{
 		url : "fundAPI",
 		type : "DELETE",
 		data : "fundID =" + $(this).data("id"),
 		dataType : "text",
 		complete : function(response, status)
	 	{
 			onFundDeleteComplete(response.responseText, status);
 		}
 	});
});

function onFundDeleteComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
		 {
		 	$("#alertSuccess").text("Successfully deleted.");
		 	$("#alertSuccess").show();
		 	$("#divFundGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
			$("#alertError").text(resultSet.data);
		 	$("#alertError").show();
		 }
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

