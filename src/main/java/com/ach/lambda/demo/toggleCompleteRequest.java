package com.ach.lambda.demo;

public class toggleCompleteRequest {

	public String projectID;
	public String name;
	
	public toggleCompleteRequest(String projectID, String name) {
		this.projectID = projectID;
		this.name = name;
	}
	
	public toggleCompleteRequest() {}

}
