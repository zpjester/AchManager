package com.ach.lambda.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class TaskDAOTest {

    private static TaskDAO input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("JUnitReadOnly");

        return ctx;
    }

    @Test
    public void testLinkedList() throws Exception {
    	input = new TaskDAO();
    	input.addSubTask("task1", "JUnitReadOnly", "t2");
    	Assert.assertEquals(input.addSubTask("", "", ""),input.addSubTask("task1", "JUnitReadOnly", "t2"));
    }
    @Test
    public void testToggleComplete() throws Exception {
    	input = new TaskDAO();
    	input.toggleComplete("task1", "JUnitReadOnly");
    	input.addTask("task1", "JUnitReadOnly");
    	input.toggleComplete("task1", "JUnitReadOnly");
    	input.renameTask("JUnitReadOnly", "task1", "Hhhi");
    	input.getComplete("");
    	input.addTask("task1", "JUnitReadOnly");
    	input.getTaskList("");
    	input.getTask("", "");
    	input.toggleTerminal("", "");
    	boolean b = input.toggleComplete("", "");
        LoadProject handler = new LoadProject();
        Context ctx = createContext();

        JSONParser parser = new JSONParser();
        Task t1 = new Task("t4");
        Project p = new Project("DeleteMe");
        input.addTask(t1, p);
        // TODO: validate output here if needed.
        Object obj = parser.parse(new FileReader("readonly.json"));
        String output = "";
		// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
		JSONObject jsonObject = (JSONObject) obj;
        Assert.assertEquals(jsonObject, output);
    }
    	
    
}
