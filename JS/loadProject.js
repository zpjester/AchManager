

function processLoadResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);

  // refreshProjectsList();

  var paragraph = document.getElementById("output");
  var taskList = document.getElementById("taskList");
  var teammateList = document.getElementById("teammateList");

  paragraph.innerHTML = result.projectID;
  var taskForm = document.getElementById("addTaskForm");

  var teammateForm = document.getElementById("addTeammateForm");
  
  if(result.archived){
   taskForm.hidden = true;
   teammateForm.hidden = true;
  }
	else{
   taskForm.hidden = false;
   teammateForm.hidden = false;
}

  


	while(taskList.firstChild){
		taskList.removeChild(taskList.firstChild);
	}
	while(teammateList.firstChild){
		teammateList.removeChild(teammateList.firstChild);
	}

  for (let i = 0; i < result.taskList.length; i++) {
	var task = result.taskList[i];
    var node = document.createElement("li");
	var taskText = task.name;
	if(task.isComplete){
		taskText = taskText.concat(" Pretend there's a green check here");
	}
	else{
		taskText = taskText.concat(" Pretend there's a red X here");
	}
    var textnode = document.createTextNode(taskText);
	node.name = "taskItem" + (i+1);
    node.appendChild(textnode);
    document.getElementById("taskList").appendChild(node);
	
	var teammateNode = document.createElement("SELECT");
	teammateNode.name = "teammateDropdown" + (i+1);
	document.getElementById("taskList").appendChild(teammateNode)

	for (let j = 0; j < result.taskList[i].teammateList.length; j++) {
		var teammateOptionNode = document.createElement("OPTION");
		var teammateOptionTextnode = document.createTextNode(result.taskList[i].teammateList[j].name);
		teammateOptionNode.name = "teammateNum" + j;
		teammateOptionNode.appendChild(teammateOptionTextNode);
		document.getElementById(teammateNode.name).appendChild(teammateOptionNode);	
	}
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
	var name = form.projectName.value;
	loadProject(name);
	}
	
function loadProject(projName){
  
  // console.log("Creating project with " + e.createForm.JSON.stringify);

  
  // if (form.system.checked) {  // be sure to flag system constant requests...
  //    data["system"] = true;
  // }
  
  // data["value"] = form.constantValue.value;

  // var js = JSON.stringify(data);
  var projURL = projectView_url + "/" + projName;
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
