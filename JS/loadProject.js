

function processLoadResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);

  // refreshProjectsList();

  var paragraph = document.getElementById("output");
  var orderedList = document.getElementById("taskList");
  paragraph.innerHTML = result.projectID;
  var taskForm = document.getElementById("addTaskForm");
  taskForm.removeAttribute("hidden");
  var teammateForm = document.getElementById("addTeammateForm");
  teammateForm.removeAttribute("hidden");

  for (let i = 0; i < result.taskList.length; i++) {
    var node = document.createElement("li");
    var textnode = document.createTextNode(result.taskList[i].name);
    node.appendChild(textnode);
    document.getElementById("taskList").appendChild(node);
  }
  
  for (let i = 0; i < result.teammateList.length; i++) {
    var node = document.createElement("li");
    var textnode = document.createTextNode(result.teammateList[i].name);
    node.appendChild(textnode);
    document.getElementById("teammateList").appendChild(node);
  }
  
}

function handleLoadClick(e) {
  var form = document.loadProj;
  // console.log("Creating project with " + e.createForm.JSON.stringify);

  var data = {};
  data["projectID"] = form.projectName.value;
  
  // if (form.system.checked) {  // be sure to flag system constant requests...
  //    data["system"] = true;
  // }
  
  // data["value"] = form.constantValue.value;

  // var js = JSON.stringify(data);
  var projURL = projectView_url + "/" + form.projectName.value;
  // console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  console.log("Preparing to open GET at " + projURL);
  xhr.open("GET", projURL, true);
  console.log("GET request sent!");

	xhr.send();


  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    console.log("Load ended")
    console.log(xhr);
    console.log(xhr.responseType);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      //console.log ("XHR:" + xhr.responseText);
	      processLoadResponse(JSON.parse(xhr.response));
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processLoadResponse("N/A");
    }
  };
}

