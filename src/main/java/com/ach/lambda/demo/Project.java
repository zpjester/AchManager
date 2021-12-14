package com.ach.lambda.demo;

import org.json.simple.JSONObject;

public class Project {
	public String projectID;
	public boolean isArchived;
	public int percentComplete;
	public Project (String pID) {
		this.projectID = pID;
		this.isArchived = false;
		this.percentComplete = -1;
	}
	public Project (String pID, boolean v, int pct) {
		this.projectID = pID;
		this.isArchived = v;
		this.percentComplete = pct;
	}
	public JSONObject toJSON() {
		JSONObject response = new JSONObject();
		response.put("projectID", projectID);
		response.put("isArchived", isArchived);
		response.put("percentComplete", percentComplete);
		return response;
	}
}
