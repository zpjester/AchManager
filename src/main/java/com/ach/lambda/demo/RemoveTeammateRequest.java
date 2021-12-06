package com.ach.lambda.demo;

public class RemoveTeammateRequest {

	public String projectID;
	public String name;
	
	public RemoveTeammateRequest(String projectID, String name) {
		this.projectID = projectID;
		this.name = name;
	}
	
	public RemoveTeammateRequest() {}

}
