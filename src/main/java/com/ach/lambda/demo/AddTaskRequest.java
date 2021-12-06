package com.ach.lambda.demo;

public class AddTaskRequest {

	public String projectID;
	public String name;
	
	public AddTaskRequest(String projectID, String name) {
		this.projectID = projectID;
		this.name = name;
	}
	
	public AddTaskRequest() {}

}
