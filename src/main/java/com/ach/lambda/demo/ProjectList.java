package com.ach.lambda.demo;

public class ProjectList {
	private Project[] projects;

	public Project (Project[] projects) {
		this.projects = projects;
	}

	public Project[] getProjects() {
		return this.projects;
	}

	public void setProjects(Project[] projects) {
		this.projects = projects;
	}
}
