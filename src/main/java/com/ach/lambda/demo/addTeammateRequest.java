package com.ach.lambda.demo;

public class addTeammateRequest {
	String projectID;
	String name;
	
	addTeammateRequest(String projectID, String name){
		this.projectID = projectID;
		this.name = name;
	}
}
