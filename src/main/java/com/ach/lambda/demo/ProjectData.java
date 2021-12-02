package com.ach.lambda.demo;

import java.util.LinkedList;

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
	public ProjectData(String projectID) {
		this.projectID = projectID;
	    this.teammateList = new LinkedList<Teammate>();
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
//	
//	public Teammate[] getTeammateList() {
//	    return this.teammateList;
//	}
//	
//	public void setTeammateList(Teammate[] newTeammates) {
//	    this.teammateList = newTeammates;
//	}
//	
//	public Task[] getTaskList() {
//	    return this.taskList;
//	}
//	
//	public void setTaskList(Task[] newTasks) {
//	    this.taskList = newTasks;
//	}
	
	public boolean getArchived() {
	    return this.archived;
	}
	
	public void setArchived(boolean isArchived) {
	    this.archived = isArchived;
	}

}