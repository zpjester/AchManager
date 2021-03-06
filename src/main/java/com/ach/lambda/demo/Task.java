package com.ach.lambda.demo;

import java.util.LinkedList;
import java.util.UUID;
import org.json.simple.JSONObject;

public class Task {
	public String name;
	public String tasksID;
	public String outlineID;
	public LinkedList<Teammate> teammateList;
	public String parentTask;
	public boolean isTerminal;
	public boolean isComplete;
	
	public Task(String n, String o, LinkedList<Teammate> t, String p, boolean term, boolean com) {
		this.name = n;
		this.tasksID = UUID.randomUUID().toString().replace("-", "");
		this.outlineID = o;
		this.teammateList = t;
		this.parentTask = p;
		this.isTerminal = term;
		this.isComplete = com;
	}
	public Task(String u,String n, String o, LinkedList<Teammate> t, String p, boolean term, boolean com) {
		this.name = n;
		this.tasksID = u;
		this.outlineID = o;
		this.teammateList = t;
		this.parentTask = p;
		this.isTerminal = term;
		this.isComplete = com;
	}
	public Task(String n) {
		this.outlineID = "-1";
		this.name = n;
		this.tasksID = UUID.randomUUID().toString().replace("-", "");
		this.teammateList = new LinkedList<Teammate>();
		this.parentTask = "";
		this.isTerminal = false;
		this.isComplete = false;
	}
	
	public JSONObject toJSON(){
		JSONObject response = new JSONObject();
		response.put("name", name);
		response.put("outlineID", outlineID);
		
		
		
		int teammateCount = teammateList.size();
    	JSONObject[] teammateArray = new JSONObject[teammateCount];
    	for(int i = 0; i < this.teammateList.size(); i++) {
    		teammateArray[i] = teammateList.get(i).toJSON();
    	}
    	response.put("teammateList", teammateArray);
		
    	response.put("parentTask", parentTask);
    	response.put("isTerminal", isTerminal);
    	response.put("isComplete", isComplete);
    	
		return response;
	}
}