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
public class DeleteProjectTest {

    private static DeleteProjectRequest input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new DeleteProjectRequest("1234");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testDeleteProjectHandler() {
        DeleteProjectHandler handler = new DeleteProjectHandler();
        Context ctx = createContext();

        JSONObject output = handler.handleRequest(input, ctx);

       //Object output = null;
		// TODO: validate output here if needed.
        try {
			Assert.assertEquals(handler.deleteProject("1234"), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testCreateProject() throws Exception {
    	CreateProjectHandler handler = new CreateProjectHandler();
    	ProjectsDAO dao = new ProjectsDAO();
    	Assert.assertEquals(handler.createProject("Project"),dao.addProject(new Project("Project")));
    }
}
