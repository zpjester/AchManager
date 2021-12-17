package com.ach.lambda.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class loadProjectTest {

    private static LoadProjectRequest input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testloadProject() throws FileNotFoundException, IOException, ParseException {
    	input = new LoadProjectRequest("JUnitReadOnly");
        LoadProject handler = new LoadProject();
        Context ctx = createContext();

        JSONObject output = handler.handleRequest(input, ctx);
        JSONParser parser = new JSONParser();
        
        LoadProjectList handler2 = new LoadProjectList();
        Context ctx2 = createContext();
        JSONObject input2 = new JSONObject();
        JSONObject output2 = handler2.handleRequest(input2, ctx2);
        // TODO: validate output here if needed.
        Object obj = parser.parse(new FileReader("readonly.json"));
        
		// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
		JSONObject jsonObject = (JSONObject) obj;
        Assert.assertEquals(jsonObject, output);
        Assert.assertEquals(jsonObject, output2);
    }
    
}
