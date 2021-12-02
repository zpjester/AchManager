package com.ach.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LoadProject implements RequestHandler<LoadProjectRequest, ProjectData> {
	LambdaLogger logger;
    @Override
    public ProjectData handleRequest(LoadProjectRequest input, Context context) {

        logger = context.getLogger();
		
		logger.log("input" + input + "\n");
		
		ProjectData response = null;
		
		try {
			ProjectsDAO dao = new ProjectsDAO();
			response = dao.getProjectData(input.projectID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return response;
    }

}
