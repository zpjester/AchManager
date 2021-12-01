package com.amazonaws.lambda.demo;

public class Project {
	public String projectID;
	public boolean isArchived = false;
	public Project (String pID) {
		projectID = pID;
	}
	public Project (String pID, boolean v) {
		projectID = pID;
		isArchived = v;
	}
}
