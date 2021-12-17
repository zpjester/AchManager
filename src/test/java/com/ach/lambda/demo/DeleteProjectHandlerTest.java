package com.ach.lambda.demo;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DeleteProjectHandlerTest {

    private static CreateProjectRequest input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new CreateProjectRequest("1234");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testCreateProjectHandler() {
        CreateProjectHandler handler = new CreateProjectHandler();
        Context ctx = createContext();

        JSONObject output = handler.handleRequest(input, ctx);

       //Object output = null;
		// TODO: validate output here if needed.
        Assert.assertEquals((new ProjectData("1234")).toJSON(), output);
    }
    
    @Test
    public void testCreateProject() throws Exception {
    	CreateProjectHandler handler = new CreateProjectHandler();
    	ProjectsDAO dao = new ProjectsDAO();
    	Assert.assertEquals(handler.createProject("Project"),dao.addProject(new Project("Project")));
    }
}
