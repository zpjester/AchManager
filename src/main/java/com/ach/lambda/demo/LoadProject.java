package com.ach.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.simple.JSONObject; 
public class LoadProject implements RequestHandler<LoadProjectRequest, JSONObject> {
	LambdaLogger logger;
    @Override
    public JSONObject handleRequest(LoadProjectRequest input, Context context) {

        logger = context.getLogger();
		
		logger.log("input" + input + "\n");
		
		ProjectData response = null;
		
		try {
			ProjectsDAO dao = new ProjectsDAO();
			response = dao.getProjectData(input.projectID);
			
		} catch (Exception e) {
			e.printStackTrace();
			response = new ProjectData("Something went very wrong loading project " + input.projectID + " we have no idea why");
		}
		try {
			TaskDAO taskDao = new TaskDAO();
			response.setTaskList(taskDao.getTaskList(response.getProjectID()));
			
		} catch (Exception e) {
			e.printStackTrace();
			//response = new ProjectData("Something went very wrong loading project " + input.projectID + " we have no idea why");
		}
		
		if(response == null) {
			response = new ProjectData("Project " + input.projectID + " not found", 404);
		}
		
        return response.toJSON();
    }

}
