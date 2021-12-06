package com.ach.lambda.demo;

public class addTeammateRequest {
	String projectID;
	String name;
	
	public addTeammateRequest(String projectID, String name){
		this.projectID = projectID;
		this.name = name;
	}
	public addTeammateRequest(){
		this.projectID = "";
		this.name = "";
	}
}
