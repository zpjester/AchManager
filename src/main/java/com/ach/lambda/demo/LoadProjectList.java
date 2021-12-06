package com.ach.lambda.demo;

import java.util.LinkedList;

import org.json.simple.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LoadProjectList implements RequestHandler<JSONObject, JSONObject> {
	LambdaLogger logger;
    @Override
    public JSONObject handleRequest(JSONObject s, Context context) {

        logger = context.getLogger();
		
		logger.log("Admin Request " + s + "\n");
		
		ProjectListResponse response = null;
		
		try {
			ProjectsDAO dao = new ProjectsDAO();
			response = new ProjectListResponse(dao.getProjectList().getProjects(), "", "200");
		} catch (Exception e) {
			logger.log("Exception Found: ");
			e.printStackTrace();
			response = new ProjectListResponse(new LinkedList<Project>(), "Exception", "420");
		}
		
        return response.toJSON();
    }

}
