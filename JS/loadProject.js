var projectData;

function processLoadResponse(result) {
  // Can grab any DIV or SPAN HTML element and can then manipulate its
  // contents dynamically via javascript
  console.log("result:" + result);
  projectData = result;
  // refreshProjectsList();
  
  // Get HTML elements
  var paragraph = document.getElementById("output");
  var taskList = document.getElementById("taskList");
  var teammateList = document.getElementById("teammateList");

 
  var taskForm = document.getElementById("addTaskForm");

  var teammateForm = document.getElementById("addTeammateForm");
  var teammateDropdown = document.getElementById("teammateDropdown");


// Show / hide controls based on archival status
  if(result.archived){
   taskForm.hidden = true;
   teammateForm.hidden = true;
  }
	else{
   taskForm.hidden = false;
   teammateForm.hidden = false;
}

// Clear lists of tasks and teammates
while(taskList.firstChild){
		taskList.removeChild(taskList.firstChild);
	}
	while(teammateList.firstChild){
		teammateList.removeChild(teammateList.firstChild);
	}
	
   // Project name
  paragraph.innerHTML = result.projectID;


	
// Put all tasks in task list
  for (let i = 0; i < result.taskList.length; i++) {
	var task = result.taskList[i];
    var node = document.createElement("li");
	var taskText = task.name;
	if(task.isComplete){
		taskText = taskText.concat('&#9989;');
	}
	else{
		taskText = taskText.concat("&#10060;");
	}
	
    var textnode = document.createTextNode(taskText);
	node.name = "taskItem" + (i+1);
    node.appendChild(textnode);
    document.getElementById("taskList").appendChild(node);
	
	// Teammate list
	//not working
	/*
	var teammateNode = document.createElement("SELECT");
	teammateNode.name = "teammateDropdown" + (i+1);
	document.getElementById("taskList").appendChild(teammateNode);

	for (let j = 0; j < result.taskList[i].teammateList.length; j++) {
		var teammateOptionNode = document.createElement("OPTION");
		var teammateOptionTextnode = document.createTextNode(result.taskList[i].teammateList[j].name);
		teammateOptionNode.name = "teammateNum" + j;
		teammateOptionNode.appendChild(teammateOptionTextNode);
		document.getElementById(teammateNode.name).appendChild(teammateOptionNode);	
	}*/
	document.getElementById("taskList").innerHTML = document.getElementById("taskList").innerHTML.replace("&amp;","&");
  }
  
var dropdownString = "<option value=\"\" disabled selected>Select a teammate</option>";
	
	
	
	
	

//Add teammates to list

  for (let i = 0; i < result.teammateList.length; i++) {
    var node = document.createElement("li");
    var name = result.teammateList[i].name;
    var textnode = document.createTextNode(name);
    node.appendChild(textnode);
    document.getElementById("teammateList").appendChild(node);
	node.id = "teammate" + (i+1);
    dropdownString = dropdownString + "<option value =" + name + ">" + name + "</option>";
  }
// Push teammate list to selector dropdown
  teammateDropdown.innerHTML = dropdownString;
}

function loopThroughTasks(taskList, tm, tmTasks) {
	for (let i = 0; i < taskList.length; i++) {
		if (taskList[i].isTerminal) {
				tml = taskList[i].teammateList;
			for (let listedTeammate of tml){
				
				if(listedTeammate.name == tm){
					console.log("Found teammate " + tm + " in " + taskList[i].name);
					tmTasks.push(taskList[i].name);
				}
			}
		}
		else {
			loopThroughTasks(taskList[i].taskList, tm, tmTasks);
		}
	}
	return tmTasks;
}

function handleToggleTeammateViewClick(e) {
	var teammateForm = document.getElementById("addTeammateForm");
	teammateForm.hidden = !teammateForm.hidden;
	var taskList = document.getElementById("taskList");
	taskList.hidden = !taskList.hidden;
	var taskForm = document.getElementById("addTaskForm");
	taskForm.hidden = !taskForm.hidden;
	if (taskForm.hidden) {
		for (let i = 0; i < projectData.teammateList.length; i++) {
		    var tm = projectData.teammateList[i].name;
			console.log("Scanning for tasks with " + tm);
			var tmTasks = [];
			tmTasks = loopThroughTasks(projectData.taskList, tm, tmTasks);
			tmTasks.push("test");
			console.log("TM Task list length = " + tmTasks.length);
			var node = document.createElement("ul");
			node.id = "teammateTasks" + (i+1);
			for (let j = 0; j < tmTasks.length; j++) {
				var subNode = document.createElement("li");
				var name = tmTasks[j].name;
				//var name = "testName";
				var textnode = document.createTextNode(name);
				node.appendChild(subNode).appendChild(textnode);
				document.getElementById("teammate" + (i+1)).appendChild(node);
			}
		}
	}
	else {
		for (let i = 0; i < projectData.teammateList.length; i++) {
			document.getElementById("teammateTasks" + (i+1)).remove();
		}
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
  document.getElementById("teammateViewButton").hidden = false;
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
