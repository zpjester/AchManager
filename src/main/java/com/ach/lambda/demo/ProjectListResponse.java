package com.ach.lambda.demo;

import java.util.LinkedList;

import org.json.simple.JSONObject;

public class ProjectListResponse {
	private LinkedList<Project> projectList;
	private String error;
	private String statusCode;
	
	JSONObject toJSON() {
		JSONObject response = new JSONObject();
//		response.put("Temp", "Nothing here yet");
		
		
		int projCount = projectList.size();
    	JSONObject[] projectArray = new JSONObject[projCount];
    	for(int i = 0; i < projCount; i++) {
    		projectArray[i] = projectList.get(i).toJSON();
    	}
    	response.put("projectList", projectArray);
		return response;
	}

	public ProjectListResponse (LinkedList<Project> linkedList, String error, String statusCode) {
		this.projectList = linkedList;
		this.error = error;
		this.statusCode = statusCode;
	}

	public LinkedList<Project> getProjects() {
		return this.projectList;
	}

	public void setProjects(LinkedList<Project> projects) {
		this.projectList = projects;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return this.statusCode;
	}

	public void setStatus(String statusCode) {
		this.statusCode = statusCode;
	}
}
