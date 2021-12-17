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
public class archiveProjectHandlerTest {

    private static archiveProjectRequest input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new archiveProjectRequest("JUnitReadOnly");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("JUnitReadOnly");

        return ctx;
    }

    @Test
    public void testArchiveProject() throws Exception {
    	input = new archiveProjectRequest("JUnitReadOnly");
        archiveProjectHandler handler = new archiveProjectHandler();
        Context ctx = createContext();

        JSONObject output = handler.handleRequest(input, ctx);
        JSONParser parser = new JSONParser();
        // TODO: validate output here if needed.
        Object obj = parser.parse(new FileReader("readonly.json"));
        handler.archiveProject("JUnitReadOnly");
        handler.handleRequest(input, ctx);
		// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
		JSONObject jsonObject = (JSONObject) obj;
        Assert.assertEquals(jsonObject, output);
    }
    
}
