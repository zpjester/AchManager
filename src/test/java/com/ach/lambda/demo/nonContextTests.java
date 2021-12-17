package com.ach.lambda.demo;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import java.util.LinkedList;

/**
 * written by KRSTOVALL
 */
public class nonContextTests {

    
    @Test
    public void testToJSON() {
        Teammate trumbus = new Teammate("trumbus");
        JSONObject response = new JSONObject();
    	response.put("name", "trumbus");
        Assert.assertEquals(trumbus.toJSON(), response);
    }
    @Test
    public void testProjectList() {
        Project defender = new Project("defender");
        Project stardust = new Project("stardust", false, 2);
        JSONObject response = new JSONObject();
		response.put("projectID", "stardust");
		response.put("isArchived", false);
		response.put("percentComplete", 2);
        Assert.assertEquals(stardust.toJSON(), response);
        LinkedList<Project> empire = new LinkedList<Project>();
        empire.add(stardust);
        ProjectList scariff = new ProjectList(empire); 
        empire.add(defender);
        scariff.setProjects(empire);
        LinkedList<Project> rebel = scariff.getProjects();
        for(int i=0; i<2;i++) {
        	Assert.assertEquals(rebel.get(i), empire.get(i));
        }
        
    }
    @Test
    public void testProjectListResponse() {
        Project defender = new Project("defender");
        Project stardust = new Project("stardust", false, 2);
        JSONObject response = new JSONObject();
		response.put("projectID", "stardust");
		response.put("isArchived", false);
		response.put("percentComplete", 2);
        LinkedList<Project> empire = new LinkedList<Project>();
        empire.add(stardust);
        ProjectListResponse scariff = new ProjectListResponse(empire, "Lothal", "Ilum"); 
        empire.add(defender);
        scariff.setProjects(empire);
        LinkedList<Project> rebel = scariff.getProjects();
        for(int i=0; i<2;i++) {
        	Assert.assertEquals(rebel.get(i), empire.get(i));
        }
        scariff.setError("Batuu"); 
        Assert.assertEquals(scariff.getError(), "Batuu"); 
        scariff.setStatus("Chandrila"); 
        Assert.assertEquals(scariff.getStatus(), "Chandrila"); 
    }
    @Test
    public void testTask() {
        Teammate trumbus = new Teammate("trumbus");
        Teammate george = new Teammate("george");
        LinkedList<Teammate> soft = new LinkedList<Teammate>();
        soft.add(trumbus);
        soft.add(george);
        Task task1 = new Task("u","String n", "String o", soft, "String p",false,false);
        Task task2 = new Task("String n", "String o", soft, "String p",false,false);
        Task task3 = new Task("u");
        JSONObject response = new JSONObject();
		response.put("name", "String n");
		response.put("outlineID", "String o");
		int teammateCount = soft.size();
    	JSONObject[] teammateArray = new JSONObject[teammateCount];
    	for(int i = 0; i < soft.size(); i++) {
    		teammateArray[i] = soft.get(i).toJSON();
    	}
    	response.put("teammateList", teammateArray);
		
    	response.put("parentTask", "String p");
    	response.put("isTerminal", false);
    	response.put("isComplete", false);
    	
       // Assert.assertEquals(task1.toJSON(), response);
        Assert.assertTrue(task1.toJSON().get("name").equals(response.get("name")));
        Assert.assertTrue(task1.toJSON().get("outlineID").equals(response.get("outlineID")));
        Assert.assertTrue(task1.toJSON().get("parentTask").equals(response.get("parentTask")));
        Assert.assertTrue(task1.toJSON().get("isTerminal").equals(response.get("isTerminal")));
        Assert.assertTrue(task1.toJSON().get("isComplete").equals(response.get("isComplete")));
        
        for(int i = 0; i < soft.size(); i++) {
        	JSONObject[] teammateArrayResponse  = (JSONObject[]) task1.toJSON().get("teammateList");
    		Assert.assertEquals(teammateArray[i], teammateArrayResponse[i]);
    	}
    }
    
}
