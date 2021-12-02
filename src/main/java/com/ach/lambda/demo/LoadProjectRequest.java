package com.ach.lambda.demo;

public class LoadProjectRequest {
	public String projectID;
	
	public LoadProjectRequest(String projectID) {
		this.projectID = projectID;
	}
	
	public LoadProjectRequest() {}

	public String toString() {
		return projectID + "";
	}
}
