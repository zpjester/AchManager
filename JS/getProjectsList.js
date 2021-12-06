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
   for (let i = 0; i < js.list.length; i++) {
     var listJson = js.list[i];
     console.log(listJson);
     
     var projectName = listJson["name"];
     var archived = listJson["isArchived"];
     output = output + "<div id=\"proj" + projectName + "\"><b>" + projectName + ":</b> = " + archived + "<br></div>";
   }
 
   // Update computation result
   projectsList.innerHTML = "TESTING";
 }