package com.ach.lambda.demo;

import java.util.LinkedList;

public class ProjectList {
	private LinkedList<Project> projects;

	public ProjectList (LinkedList<Project> projects) {
		this.projects = projects;
	}

	public LinkedList<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(LinkedList<Project> projects) {
		this.projects = projects;
	}
}
