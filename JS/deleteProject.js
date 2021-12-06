
function handleDeleteProjectClick(e) {
  // console.log("Creating project with " + e.createForm.JSON.stringify);
  
  // console.log("Creating project with " + e.createForm.JSON.stringify);
  
  //var projForm = document.getElementById("deleteProject");
  var dropdown = document.getElementById("deleteDropdown");
  var projName = dropdown.value;
  
  // if (form.system.checked) {  // be sure to flag system constant requests...
  //    data["system"] = true;
  // }
  
  // data["value"] = form.constantValue.value;

  // var js = JSON.stringify(data);
  var projURL = deleteProject_URL + "?projectID=" + projName;
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
	      //processAddTeammateResponse(xhr.responseText);
		refreshProjectsList();
    	 }
  };
}
}