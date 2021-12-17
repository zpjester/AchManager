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
public class ProjectsDAOTest {

    private static ProjectsDAO input;

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
    public void testToggleComplete() throws Exception {
    	input = new ProjectsDAO();
    	input.getProjectData("JUnitReadOnly");
        LoadProject handler = new LoadProject();
        Context ctx = createContext();

        JSONParser parser = new JSONParser();
        // TODO: validate output here if needed.
        Object obj = parser.parse(new FileReader("readonly.json"));
        String output = "";
		// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
		JSONObject jsonObject = (JSONObject) obj;
        Assert.assertEquals(jsonObject, output);
    }
    	
    
}
