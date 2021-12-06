package com.ach.lambda.demo;

public class addTeammateRequest {
	public String projectID;
	public String name;
	
	public addTeammateRequest(String projectID, String name){
		this.projectID = projectID;
		this.name = name;
	}
	public addTeammateRequest(){
		this.projectID = "";
		this.name = "";
	}
}
