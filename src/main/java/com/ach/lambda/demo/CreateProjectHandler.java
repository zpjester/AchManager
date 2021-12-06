package com.ach.lambda.demo;

import com.ach.lambda.demo.CreateProjectRequest;
import com.ach.lambda.demo.Project;
import com.ach.lambda.demo.ProjectData;
import com.ach.lambda.demo.ProjectsDAO;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import org.json.simple.JSONObject; 
public class CreateProjectHandler implements RequestHandler<CreateProjectRequest,JSONObject>{

LambdaLogger logger;
	
	// To access S3 storage
	//private AmazonS3 s3 = null;
		
	// Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	//public static final String REAL_BUCKET = "constants/";
	//
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean createProject(String name) throws Exception { 
		if (logger != null) { logger.log("in createProject \n"); }

		ProjectsDAO dao = new ProjectsDAO();
		return dao.addProject(new Project(name));
		/*
		// check if present
		Project exist = dao.getProject(name);
		Project constant = new Project (name);
		if (exist == null) {
			return dao.addProject(constant);
		} else {
			return false;
		}*/
	}
	
	
	
	@Override 
	public JSONObject handleRequest(CreateProjectRequest req, Context context)  {
		logger = context.getLogger();
		
		logger.log("Project ID: " + req.toString() + "\n");
		
		ProjectData response;
		
		
		/*
		
		response = new ProjectData("Test", null, null, false);
		return response;
		
		//*/
		
		try {
			
			if (createProject(req.projectID)) {
				response = new ProjectData(req.projectID);
			} else {
				response = new ProjectData(req.projectID, 422);
			}
		} catch (Exception e) {
			response = new ProjectData("Unable to create project: " + req.projectID + "(" + e.getMessage() + ")", 400);
			
		}
		
		return response.toJSON();
		//*/
	}
}
