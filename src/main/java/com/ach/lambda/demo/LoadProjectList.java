package com.ach.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LoadProjectList implements RequestHandler<String, ProjectListResponse> {
	LambdaLogger logger;
    @Override
    public ProjectListResponse handleRequest(String s, Context context) {

        logger = context.getLogger();
		
		logger.log("input" + s + "\n");
		
		ProjectListResponse response = null;
		
		try {
			ProjectsDAO dao = new ProjectsDAO();
			response = new ProjectListResponse(dao.getProjectList().getProjects(), null, "200");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return response;
    }

}
