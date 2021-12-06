package com.ach.lambda.demo;

import java.util.LinkedList;
import org.json.simple.JSONObject; 
import com.ach.lambda.demo.Teammate;
import com.ach.lambda.demo.Task;

public class ProjectData {
    private String projectID;
    private LinkedList<Teammate> teammateList;
    private LinkedList<Task> taskList;
    private boolean archived;
    public int system = 200;

//	public ProjectData(String projectID, LinkedList<Teammate>, LinkedList<Task> taskList;, boolean archived) {
//	    this.projectID = projectID;
//	    this.teammateList = teammateList;
//	    this.taskList = taskList;
//	    this.archived = archived;
//	}
//	public ProjectData(String projectID, Teammate[] teammateList, Task[] taskList, boolean archived, int s) {
//	    this.projectID = projectID;
//	    this.teammateList = teammateList;
//	    this.taskList = taskList;
//	    this.archived = archived;
//	    this.system = s;
//	}
    public String toString() {
    	
    	String archivedString = " is not archived";
    	String teammatesString = " has no teammates";
    	String tasksString = " has no tasks";
    	
    	if(archived) {
    		archivedString = " is archived";
    	}
    	
    	
    	if(teammateList.size() > 0) {
    		teammatesString = " has teammates ";
    		for(Teammate li : teammateList) {
    			teammatesString.concat(li.toString() + ", ");
    		}
    	}
    	if(taskList.size() > 0) {
    		tasksString = " has tasks ";
    		for(Task li : taskList) {
    			tasksString.concat(li.toString() + ", ");
    		}
    	}
    	
    	String outputString = "Project" + projectID + archivedString + teammatesString + tasksString;
    	return outputString;
    }
    
    
    public JSONObject toJSON() {
    	JSONObject result = new JSONObject();
    	result.put("projectID", projectID);
    	
    	JSONObject fakeTeammate = new JSONObject();
    	fakeTeammate.put("name", "Steve");
    	
    	int teammateCount = teammateList.size();
    	JSONObject[] teammateArray = new JSONObject[teammateCount];
    	for(int i = 0; i < teammateCount; i++) {
//    		teammateArray[i] = fakeTeammate;
    				
    				
    				teammateList.get(i).toJSON();
    	}
    	JSONObject[] teammateArray2 = new JSONObject[1];
    	teammateArray2[0] = new Teammate("Test teammate").toJSON();
    	result.put("teammateList", teammateArray); //Change this
    	
    	int taskCount = taskList.size();
    	JSONObject[] taskArray = new JSONObject[taskCount];
    	for(int i = 0; i < taskCount; i++) {
    		taskArray[i] = taskList.get(i).toJSON();
    	}
//    	taskArray[taskCount + 1] = new Task("Test Task for display purposes","0", new LinkedList<Teammate>(), "", false, false).toJSON();
    	result.put("taskList", taskArray);
    	
    	result.put("archived", archived);   
    	result.put("code", system);
    	return result;
    }
	public ProjectData(String projectID) {
		this.projectID = projectID;
	    this.teammateList = new LinkedList<Teammate>();
//	    this.teammateList.add(new Teammate("testMate"));
	    this.taskList = new LinkedList<Task>();
	    this.archived = false;
	}
	public ProjectData(String projectID, int code) {
		this.projectID = projectID;
	    this.teammateList = new LinkedList<Teammate>();
	    this.taskList = new LinkedList<Task>();
	    this.archived = false;
	    this.system = code;
	}
	
	public ProjectData(String projectID, LinkedList<Teammate> teammateList, LinkedList<Task> taskList) {
		this.projectID = projectID;
	    this.teammateList = teammateList;
	    this.taskList = taskList;
	    this.archived = false;
	}
	
	
	public String getProjectID() {
	    return this.projectID;
	}
	
	public void setProjectID(String newID) {
	    this.projectID = newID;
	}
	
	public LinkedList<Teammate> getTeammateList() {
	    return this.teammateList;
	}
	
		public void setTeammateList(LinkedList<Teammate> newTeammates) {
	    this.teammateList = newTeammates;
	}
	
	public LinkedList<Task> getTaskList() {
	    return this.taskList;
	}
	
	public void setTaskList(LinkedList<Task> newTasks) {
	    this.taskList = newTasks;
	}
	
	public boolean getArchived() {
	    return this.archived;
	}
	
	public void setArchived(boolean isArchived) {
	    this.archived = isArchived;
	}

}