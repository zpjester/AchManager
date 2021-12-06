package com.ach.lambda.demo;

import java.util.UUID;

public class Task {
	public String name;
	public String tasksID;
	public String outlineID;
	public Teammate [] teammateList;
	public String parentTask;
	public boolean isTerminal;
	public boolean isComplete;
	
	public Task(String n, String o, Teammate[] t, String p, boolean term, boolean com) {
		name = n;
		tasksID = UUID.randomUUID().toString().replace("-", "");
		outlineID = o;
		teammateList = t;
		parentTask = p;
		isTerminal = term;
		isComplete = com;
	}
	public Task(String u,String n, String o, Teammate[] t, String p, boolean term, boolean com) {
		name = n;
		tasksID = u;
		outlineID = o;
		teammateList = t;
		parentTask = p;
		isTerminal = term;
		isComplete = com;
	}
}