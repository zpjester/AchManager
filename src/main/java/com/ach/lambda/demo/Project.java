package com.ach.lambda.demo;

import org.json.simple.JSONObject;

public class Project {
	public String projectID;
	public boolean isArchived = false;
	public Project (String pID) {
		this.projectID = pID;
	}
	public Project (String pID, boolean v) {
		this.projectID = pID;
		this.isArchived = v;
	}
	public JSONObject toJSON() {
		JSONObject response = new JSONObject();
		response.put("projectID", projectID);
		response.put("isArchived", isArchived);
		return response;
	}
}
