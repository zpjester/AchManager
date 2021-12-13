/**
 * Refresh constant list from server
 *
 *    GET list_url
 *    RESPONSE  list of [name, value, system] constants 
 */
 function refreshProjectsList() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", listProjects_URL, true);
    xhr.send();
    
    console.log("sent");
 
   // This will process results and update HTML as appropriate. 
   xhr.onloadend = function () {
     if (xhr.readyState == XMLHttpRequest.DONE) {
       console.log ("XHR:" + xhr.responseText);
       processListResponse(xhr.responseText);
     } else {
       processListResponse("N/A");
     }
   };
 }
 
 /**
  * Respond to server JSON object.
  *
  * Replace the contents of 'constantList' with a <br>-separated list of name,value pairs.
  */
 function processListResponse(result) {
	
   console.log("res:" + result);
   // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
   var js = JSON.parse(result);
   var projectsList = document.getElementById('projectList');
   
   var output = "";
   for (let i = 0; i < js.projectList.length; i++) {
     var listJson = js.projectList[i];
     console.log(listJson);
     
     var projectID = listJson["projectID"];
     console.log("Name: " + projectID);
     var archived = listJson["isArchived"];
     console.log("Archived: " + archived);
	 var completion = listJson["pctComplete"];
     console.log("Completion %: " + completion + "%");

	var archivedString;
	if(archived){
		archivedString = "Archived"
	}
	else{
		archivedString = "Not Archived"
	}
	
	
     output = output + "<div id=\"proj" + projectID+ "\"><b>" + projectID+ " -> " + archivedString + ", " + completion + "% complete " + "<br></div>";
   }
 	
	var display = document.getElementById("listWindow");
	display.innerHTML = output;
	
	var dropdownString = "";
	var nameList = [];
	for (let i = 0; i < js.projectList.length; i++){
		var li = js.projectList[i];
		name = li["projectID"];
		nameList[i] = name;
		dropdownString = dropdownString + "<option value =" + name + ">" + name + "</option>";
	}
	console.log(projectList);
	
	
	var archiveToggle = document.getElementById("deleteDropdown");
	archiveToggle.innerHTML = dropdownString;
	
	var deletProj = document.getElementById("deleteProj");
	deleteProj.removeAttribute("hidden");
   
 }