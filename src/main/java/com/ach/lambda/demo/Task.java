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
		name = n;
		tasksID = UUID.randomUUID().toString().replace("-", "");
		outlineID = o;
		teammateList = t;
		parentTask = p;
		isTerminal = term;
		isComplete = com;
	}
	public Task(String u,String n, String o, LinkedList<Teammate> t, String p, boolean term, boolean com) {
		name = n;
		tasksID = u;
		outlineID = o;
		teammateList = t;
		parentTask = p;
		isTerminal = term;
		isComplete = com;
	}
	
	public JSONObject toJSON(){
		JSONObject response = new JSONObject();
		response.put("name", name);
		response.put("outlineID", outlineID);
		
		/*int teammateCount = teammateList.size();
    	JSONObject[] teammateArray = new JSONObject[teammateCount];
    	for(int i = 0; i < this.teammateList.size(); i++) {
    		teammateArray[i] = teammateList.get(i).toJSON();
    	}
    	result.put("teammateList", teammateArray);
		*/
		return response;
	}
}