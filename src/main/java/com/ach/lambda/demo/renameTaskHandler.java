package com.ach.lambda.demo;

import com.ach.lambda.demo.RemoveTeammateRequest;
import com.ach.lambda.demo.Project;
import com.ach.lambda.demo.ProjectData;
import com.ach.lambda.demo.ProjectsDAO;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import org.json.simple.JSONObject; 
public class renameTaskHandler implements RequestHandler<renameTaskRequest,JSONObject>{

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
	boolean renameTask(String name, String projectID, String newName) throws Exception { 
		if (logger != null) { logger.log("in addTask \n"); }

		TaskDAO dao = new TaskDAO();
		return dao.renameTask(projectID, name, newName); // not implemented
		//return false;
		
		/*
		// check if present
		Project exist = dao.getProject(name);
		Project constant = new Project (name);
		if (exist == null) {
			return dao.addProject(constant);
		} else {
			return false;
		}*/
//		return false;
	}
	
	
	
	@Override 
	public JSONObject handleRequest(renameTaskRequest req, Context context)  {
		logger = context.getLogger();
		
		logger.log("Renaming Task: " + req.toString() + "\n");
		
		JSONObject response = new JSONObject();
		String code = "";
		
		/*
		
		response = new ProjectData("Test", null, null, false);
		return response;
		
		//*/
		
		try {
			
			if (renameTask(req.name, req.projectID, req.newName)) {
				code = "200";
			} else {
				code = "404";
			}
		} catch (Exception e) {
			code = "420";
			e.printStackTrace();
			
		}
		response.put("code", code);
		return response;
		//*/
	}
}
