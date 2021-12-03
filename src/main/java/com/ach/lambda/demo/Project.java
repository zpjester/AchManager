package com.ach.lambda.demo;

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
}
