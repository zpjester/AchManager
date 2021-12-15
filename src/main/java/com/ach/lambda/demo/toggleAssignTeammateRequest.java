package com.ach.lambda.demo;

public class toggleAssignTeammateRequest {
	public String projectID;
	public String name;
	public String taskName;
	
	public toggleAssignTeammateRequest(String projectID, String name, String taskName){
		this.projectID = projectID;
		this.name = name;
		this.taskName = taskName;
	}
	public toggleAssignTeammateRequest(){
		this.projectID = "";
		this.name = "";
		this.taskName = "";
	}
}
