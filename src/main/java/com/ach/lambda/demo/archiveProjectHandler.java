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
public class archiveProjectHandler implements RequestHandler<archiveProjectRequest,JSONObject>{

LambdaLogger logger;
	
	
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean archiveProject(String projectID) throws Exception { 
		if (logger != null) { logger.log("in archiveProject \n"); }

		ProjectsDAO dao = new ProjectsDAO();
		return dao.archiveProject(projectID); // Not created yet
		//return false;		
	}
	
	
	public JSONObject handleRequest(archiveProjectRequest req, Context context) {
		logger = context.getLogger();
		
		logger.log("Archiving Project: " + req.projectID + "\n");
		
		JSONObject response = new JSONObject();
		String code = "";
		
		/*
		
		response = new ProjectData("Test", null, null, false);
		return response;
		
		//*/
		
		try {
			
			if (archiveProject(req.projectID)) {
				code = "200";
			} else {
				code = "404";
			}
		} catch (Exception e) {
			code = "420";
			logger.log(e.getStackTrace().toString());
			
		}
		response.put("code", code);
		return response;
		//*/
	}
}
