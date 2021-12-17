function handleDecomposeTaskClick(e) {
  var form = document.getElementById("addTaskForm");
  // console.log("Creating project with " + e.createForm.JSON.stringify);
	var output = document.getElementById("output");
	var projName = output.innerText;
  
  console.log(projName);
  


var taskName = form.taskName.value;
let newTasks = prompt("Enter new tasks, separated by a comma.");
var newTasksList = newTasks.split(",");
var numTasks = newTasksList.length;



if(numTasks == 1){
	addTask(newTasksList[0], taskName);
}

else{
console.log(newTasksList);

var parentTask;
for (task of projectData.taskList){
	if(task.name == taskName){
		parentTask = task;
	}
}

var teammates = parentTask.teammateList;
var numTeammates = teammates.length;
for(teammate of teammates){
	toggleTeammate(teammate.name, task.name);
}

for(newTaskName of newTasksList){
	addTask(newTaskName, taskName);
}
var i = 0;
for(teammate of teammates){
	toggleTeammate(teammate.name,newTasksList[0]);
	i++;
}
}

}