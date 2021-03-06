

function processRemoveTeammateResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);

  // refreshProjectsList();

  var paragraph = document.getElementById("output");
   paragraph.innerHTML = result;
  var teammateList = document.getElementById("teammateList");
  var node = document.createElement("li");
  var textnode = document.createTextNode(result.name);
  node.appendChild(textnode);
  document.getElementById("teammateList").appendChild(node);
}

function handleRemoveTeammateClick(e) {
  var form = document.getElementById("addTeammateForm");
  // console.log("Creating project with " + e.createForm.JSON.stringify);
	var output = document.getElementById("output");
	var projName = output.innerText;
  
  console.log(projName);
  
  // if (form.system.checked) {  // be sure to flag system constant requests...
  //    data["system"] = true;
  // }
  
  // data["value"] = form.constantValue.value;

  // var js = JSON.stringify(data);
  var projURL = removeTeammate_url + "/" + projName + "?teammateID="+form.teammateName.value;
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
		loadProject(projName);
    	 }
  };
}
}