package com.ach.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LoadProjectResponse implements RequestHandler<ProjectList> {
	LambdaLogger logger;
    @Override
    public ProjectList handleRequest(Context context) {

        logger = context.getLogger();
		
		logger.log("input" + input + "\n");
		
		ProjectListResponse response = null;
		
		try {
			ProjectsDAO dao = new ProjectsDAO();
			response = dao.getProjectList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return response;
    }

}
