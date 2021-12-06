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
    	int teammateCount = teammateList.size();
    	JSONObject[] teammateArray = new JSONObject[teammateCount];
    	for(int i = 0; i < this.teammateList.size(); i++) {
    		teammateArray[i] = teammateList.get(i).toJSON();
    	}
    	result.put("teammateList", teammateArray);
    	
    	int taskCount = taskList.size();
    	JSONObject[] taskArray = new JSONObject[taskCount];
    	for(int i = 0; i < this.taskList.size(); i++) {
    		teammateArray[i] = taskList.get(i).toJSON();
    	}
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