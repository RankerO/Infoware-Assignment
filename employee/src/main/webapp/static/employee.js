
function getEmployeeUrl(){
//	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return "/employee/api/employee";
}
var name="*";
var backupPage=1;
var totalPages=1;

//BUTTON ACTIONS
function addEmployee(){
console.log("Checking");
	//Set the values to update
	var $form = $("#employee-form");
	var json = toJson($form);
	console.log(json);
	var url = getEmployeeUrl();

	$.ajax({
	   url: url,
	   type: 'POST',
	   data: json,
	   headers: {
       	'Content-Type': 'application/json'
       },	   
	   success: function(response) {
	   successMessage("1 record added");
	   $('#add-employee-modal').modal('toggle');
	   getEmployeeList();
	   },
	   error: function(jqXHR, textStatus, errorThrown) {
                            handleAjaxError(jqXHR, textStatus, errorThrown);
                    }
	});

	return false;
}

function updateEmployee(event){
    console.log("check1");
    event.preventDefault();
	$('#edit-employee-modal').modal('toggle');
	//Get the ID

	var id = $("#edit-employee-form input[name=id]").val();

	console.log("id"+id);
	var url = getEmployeeUrl() + "/" + id;

    console.log("check2");
	//Set the values to update
	var $form = $("#edit-employee-form");
	var json = toJson($form);
    console.log("check3");
	$.ajax({
	   url: url,
	   type: 'PUT',
	   data: json,
	   headers: {
       	'Content-Type': 'application/json'
       },
	   success: function(response) {
	   		getLimitedEmployeeList(backupPage);
	   		successMessage("1 record updated");
	   },
	   error: handleAjaxError
	});

	return false;
}


function getEmployeeList(){
	var url = getEmployeeUrl();

	$.ajax({

	   url: url,
	   type: 'GET',
	   success: function(data) {
	   		displayEmployeeList(data);  
	   },
	   error: function(jqXHR, textStatus, errorThrown) {
                            handleAjaxError(jqXHR, textStatus, errorThrown);
                    }
	});
}

function getLimitedEmployeeList(pageNo){

    getTotalPages();
	var url = getEmployeeUrl()+"/"+pageNo+"/getLimited/"+name;

	$.ajax({

	   url: url,
	   type: 'GET',
	   success: function(data) {
	   		displayEmployeeList(data);
	   },
	   error: function(jqXHR, textStatus, errorThrown) {
                            handleAjaxError(jqXHR, textStatus, errorThrown);
                    }
	});
}

function getTotalPages(){

    var url = getEmployeeUrl()+"/total";
    	$.ajax({
    	   url: url,
    	   type: 'GET',
    	   success: function(data) {

    	        totalPages=Math.ceil(data/10);
    	   		document.getElementById("inputTotalPages").value=totalPages;
    	   		check();
    	   },
    	   error: handleAjaxError
    	});
}

function deleteEmployee(id){
	var url = getEmployeeUrl() + "/" + id;

	$.ajax({
	   url: url,
	   type: 'DELETE',
	   success: function(data) {
	        getTotalPages();
	        if(backupPage>totalPages)
	            backupPage=totalPages;
	   		getLimitedEmployeeList(backupPage);
	   		successMessage("1 record deleted")
	   },
	   error: function(jqXHR, textStatus, errorThrown) {
                            handleAjaxError(jqXHR, textStatus, errorThrown);
                    }
	});
}

//UI DISPLAY METHODS

function displayEmployeeList(data){
	var $tbody = $('#employee-table').find('tbody');
	$tbody.empty();
	for(var i in data){
		var e = data[i];
		var buttonHtml = '<button class="deleteButtons" style="margin-bottom:8px" onclick="confirmDeleteEmployee(' + e.id + ')">Delete</button>'
		buttonHtml += ' <button  class="editButtons"onclick="displayEditEmployee(' + e.id + ')">Edit</button>'
		var row = '<tr>'
		+ '<td>' + e.name + '</td>'
		+ '<td>'  + e.job_title + '</td>'
		+ '<td>' + e.phone_number + '</td>'
		+ '<td>' + e.email + '</td>'
		+ '<td>' + e.address + '</td>'
		+ '<td>' + e.city + '</td>'
		+ '<td>' + e.state + '</td>'
		+ '<td>' + e.primary_emergency_contact + '</td>'
		+ '<td>' + e.primary_phone_number + '</td>'
		+ '<td>' + e.primary_relationship + '</td>'
		+ '<td>' + e.secondary_emergency_contact + '</td>'
        + '<td>' + e.secondary_phone_number + '</td>'
        + '<td>' + e.secondary_relationship + '</td>'
		+ '<td>' + buttonHtml + '</td>'
		+ '</tr>';
        $tbody.append(row);
	}
}

function confirmDeleteEmployee(id) {
  if (confirm("Are you sure you want to delete this Employee Data?")) {
    deleteEmployee(id);
  }
  }

function displayEditEmployee(id){
	var url = getEmployeeUrl() + "/" + id;
	$.ajax({
	   url: url,
	   type: 'GET',
	   success: function(data) {
	   		displayEmployee(data);   
	   },
	   error: function(jqXHR, textStatus, errorThrown) {
                            handleAjaxError(jqXHR, textStatus, errorThrown);
                    }
	});	
}


function displayEmployee(data){
	$("#edit-employee-form input[name=id]").val(data.id);

	$("#edit-employee-form input[name=name]").val(data.name);
	$("#edit-employee-form input[name=job_title]").val(data.job_title);
	$("#edit-employee-form input[name=phone_number]").val(data.phone_number);
	$("#edit-employee-form input[name=email]").val(data.email);
	$("#edit-employee-form input[name=address]").val(data.address);
	$("#edit-employee-form input[name=city]").val(data.city);
	$("#edit-employee-form input[name=state]").val(data.state);
	$("#edit-employee-form input[name=primary_emergency_contact]").val(data.primary_emergency_contact);
	$("#edit-employee-form input[name=primary_phone_number]").val(data.primary_phone_number);
	$("#edit-employee-form input[name=primary_relationship]").val(data.primary_relationship);
	$("#edit-employee-form input[name=secondary_emergency_contact]").val(data.secondary_emergency_contact);
	$("#edit-employee-form input[name=secondary_phone_number]").val(data.secondary_phone_number);
	$("#edit-employee-form input[name=secondary_relationship]").val(data.secondary_relationship);

	$('#edit-employee-modal').modal('toggle');
}

function addEmployeeModal(){
    $('#add-employee-modal').modal('toggle');

}

function searchByName(){
    var updatedName = document.getElementById("searchName").value;
    if(updatedName=="")
        name="*";
    else
        name=updatedName;

    getLimitedEmployeeList(backupPage);
}

  function displayPage(){
        getTotalPages();
      var page = document.getElementById("inputPage").value;
      if(page==""){
          var p=backupPage;
      }
      else if(page>totalPages){
          var p = totalPages;

          document.getElementById("inputPage").value=p;
      }
       else if(page=="0"){
            p=1;
           document.getElementById("inputPage").value=p;
       }

      else{
          var p =page;
      }

      getLimitedEmployeeList(p);
      backupPage=p;

  }

  function check(){
      var page = document.getElementById("inputPage").value;
      var previousBtn=document.getElementById("previous-page");
      var nextBtn=document.getElementById("next-page");

      var maxPage= document.getElementById("inputTotalPages").value;

          if(page=="1"){
              previousBtn.disabled=true;
              nextBtn.disabled=false;
          }

          else if(page==totalPages){
              previousBtn.disabled=false;
              nextBtn.disabled=true;
          }
          else{
              previousBtn.disabled=false;
              nextBtn.disabled=false;
          }

          if(maxPage=="1" || page==""){
                  previousBtn.disabled=true;
                  nextBtn.disabled=true;
              }
  }


  function nextPage(){

      var previousValue=document.getElementById("inputPage").value;
      var p=parseInt(previousValue);

      document.getElementById("inputPage").value = p + 1;

      displayPage();

  }

  function previousPage(){

      var previousValue=document.getElementById("inputPage").value;
      var p=parseInt(previousValue);

      document.getElementById("inputPage").value=p-1;
      displayPage();
  }

function handleAjaxError(xhr, textStatus, errorThrown) {
  var errorMessage = "An error occurred while processing your request.";
  if (xhr.responseJSON && xhr.responseJSON.message) {
    errorMessage = xhr.responseJSON.message;
  }

  $('.toast-body').text(errorMessage);
  $('#error-modal').addClass('show');
  $('.error').toast({delay: 5000});
  $('.error').toast('show');
  $('#success-modal').removeClass('show');

}


function successMessage(message){

    $('.toast-body').text(message);
    $('#success-modal').addClass('show');
    $('.success').toast({delay: 2000});
    $('.success').toast('show');
    $('#error-modal').removeClass('show');

}
function errorMessage(message){

    $('.toast-body').text(message);
    $('#error-modal').addClass('show');
    $('.error').toast({delay: 5000});
    $('.error').toast('show');
    $('#success-modal').removeClass('show');

}

//INITIALIZATION CODE
function init(){
	$('#employee-form').submit(addEmployee);
	$('#add-employee-button').click(addEmployeeModal);
	$('#edit-employee-form').submit(updateEmployee);
	$('#next-page').click(nextPage);
    $('#previous-page').click(previousPage);
}

$(document).ready(init);
$(document).ready(getLimitedEmployeeList(1));

