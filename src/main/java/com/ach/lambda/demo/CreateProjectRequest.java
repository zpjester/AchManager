package com.ach.lambda.demo;

public class CreateProjectRequest {

	public String projectID;
	
	public CreateProjectRequest(String projectID) {
		this.projectID = projectID;
	}
	
	public CreateProjectRequest() {}

	public String toString() {
		return projectID + "";
	}
}
