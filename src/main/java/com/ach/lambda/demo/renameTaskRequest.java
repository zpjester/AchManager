package com.ach.lambda.demo;

public class renameTaskRequest {

	public String projectID;
	public String name;
	public String newName;
	
	public renameTaskRequest(String projectID, String oldName, String newName) {
		this.projectID = projectID;
		this.name = name;
		this.newName = newName;
	}
	
	public renameTaskRequest() {}

}
