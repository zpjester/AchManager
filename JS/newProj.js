

function processCreateResponse(projName) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("Process created, loading now!");
	
	loadProject(projName);
}

function handleCreateClick(e) {
  var form = document.createProj;
  // console.log("Creating project with " + e.createForm.JSON.stringify);

  var projectID = form.projectName.value;
  var idLower = projectID.toLowerCase();
  var validName = !(idLower.includes(" ") || idLower.includes("drop table"));
  

  // if (form.system.checked) {  // be sure to flag system constant requests...
  //    data["system"] = true;
  // }
  
  // data["value"] = form.constantValue.value;

  // var js = JSON.stringify(data);


	if(validName){
		var projURL = createProject_url + "?projectID=" + projectID;
  // console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  console.log("Preparing to open POST at " + projURL);
  xhr.open("POST", projURL, true);
  console.log("Post request sent!");

	xhr.send();


  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log("Load ended")
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      //console.log ("XHR:" + xhr.responseText);
	      processCreateResponse(form.projectName.value);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processCreateResponse("N/A");
    }
  };
	}else{
		if(idLower.includes("drop table")){
			alert("Nice try Bobby Tables");
		}
		else if(idLower.includes(" ")){
			alert("Invalid project name, cannot contain spaces");
		}
		
	}
  
}