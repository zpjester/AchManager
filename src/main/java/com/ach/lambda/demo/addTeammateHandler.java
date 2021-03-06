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
public class addTeammateHandler implements RequestHandler<addTeammateRequest,JSONObject>{

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
	boolean addTeammate(String name, String projectID) throws Exception { 
//		if (logger != null) { logger.log("in createProject \n"); }

		TeamDAO dao = new TeamDAO();
		
		boolean exist = dao.getTeammate(name, projectID);
		if(!exist) {
			return dao.addTeammate(name, projectID);	
			
		}
		else {
			return false;
		}
				
				
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
	public JSONObject handleRequest(addTeammateRequest req, Context context)  {
		logger = context.getLogger();
		
		logger.log("Teammate Req: " + req.toString() + "\n");
		
		String name = req.name;
		String projectID = req.projectID;
		
		
		/*
		
		response = new ProjectData("Test", null, null, false);
		return response;
		
		//*/
		JSONObject response = new JSONObject();
		String code = "";
		
		try {
			
			if (addTeammate(name, projectID)) {
				code = "200";
			} else {
				code = "403";
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
