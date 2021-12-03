package com.ach.lambda.demo;

public class ProjectListResponse {
	private Project[] projectList;
	private String error;
	private String statusCode;

	public ProjectListResponse (Project[] projectList, String error, String statusCode) {
		this.projectList = projectList;
		this.error = error;
		this.statusCode = statusCode;
	}

	public Project[] getProjects() {
		return this.projectList;
	}

	public void setProjects(Project[] projectList) {
		this.projectList = projectList;
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
