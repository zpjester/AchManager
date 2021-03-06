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
public class toggleTaskComplete implements RequestHandler<toggleCompleteRequest,JSONObject>{

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
	boolean toggleComplete(String name, String projectID) throws Exception { 
		if (logger != null) { logger.log("in toggleComplete \n"); }

		TaskDAO dao = new TaskDAO();
		return dao.toggleComplete(name, projectID); // needs to be added
//		return false;
	}
	
	
	
	@Override 
	public JSONObject handleRequest(toggleCompleteRequest req, Context context)  {
		logger = context.getLogger();
		
		logger.log("\n\nToggling completion of task " + req.name + " in " + req.projectID + "\n");
		logger.log("\n" + req.name);
		JSONObject response = new JSONObject();
		String code = "";
				
		try {
			
			if (toggleComplete(req.name, req.projectID)) {
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
