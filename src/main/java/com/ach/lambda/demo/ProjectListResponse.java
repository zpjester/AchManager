package com.ach.lambda.demo;

public class ProjectListResponse {
	private Project[] projectList;
	private String error;
	private String statusCode;

	public ProjectListResponse (Project[] projects, String error, String statusCode) {
		this.projectList = projects;
		this.error = error;
		this.statusCode = statusCode;
	}

	public Project[] getProjects() {
		return this.projectList;
	}

	public void setProjects(Project[] projects) {
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
