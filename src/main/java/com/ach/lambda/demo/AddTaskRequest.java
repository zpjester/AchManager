package com.ach.lambda.demo;

public class AddTaskRequest {

	public String projectID;
	public String name;
	public String parentTaskName;
	
	public AddTaskRequest(String projectID, String name, String parentTaskName) {
		this.projectID = projectID;
		this.name = name;
		this.parentTaskName = parentTaskName;
	}
	
	public AddTaskRequest() {}

}
