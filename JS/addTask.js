function handleAddTaskClick(e) {
  var form = document.getElementById("addTaskForm");
  // console.log("Creating project with " + e.createForm.JSON.stringify);
  



var taskName = form.taskName.value;
let parentTaskName = prompt("Enter parent task name, or leave blank for top level task:");

addTask(taskName, parentTaskName);
}

function addTask(taskName, parentTaskName){
if(parentTaskName == ""){
	parentTaskName = "null";
}

var output = document.getElementById("output");
	var projName = output.innerText;
  // if (form.system.checked) {  // be sure to flag system constant requests...
  //    data["system"] = true;
  // }
  
  // data["value"] = form.constantValue.value;

  // var js = JSON.stringify(data);
  var projURL = addTask_url + "/" + projName + "?taskID="+ taskName + "&parentTaskName=" + parentTaskName;
  // console.log("JS:" + js);
  var xhr = new XMLHttpRequest();
  console.log("Preparing to open POST at " + projURL);
  xhr.open("POST", projURL, true);
  

	xhr.send();
console.log("Post request sent!");

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
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processAddTaskResponse("N/A");
    }
  };
}