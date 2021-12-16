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
public class toggleAssignHandler implements RequestHandler<toggleAssignTeammateRequest,JSONObject>{

LambdaLogger logger;
	

	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean toggleAssignment(String name, String projectID, String taskName) throws Exception { 
//		if (logger != null) { logger.log("in createProject \n"); }

		TeamDAO dao = new TeamDAO();
		
		boolean exist = dao.getTeammate(name, projectID);
		if(exist) {
//			return dao.toggleTeammate(name, taskName, projectID);	Not created yet
			return false;
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
	public JSONObject handleRequest(toggleAssignTeammateRequest req, Context context)  {
		logger = context.getLogger();
		String name = req.name;
		String projectID = req.projectID;
		String taskName = req.taskName;
		
		logger.log("\n Assignment Req: " + req.toString() + "\n");
		logger.log("Toggling assignment of teammate " + name + " to task " + taskName + " in project " + projectID + "\n");
		
	
		/*
		
		response = new ProjectData("Test", null, null, false);
		return response;
		
		//*/
		JSONObject response = new JSONObject();
		String code = "";
		
		try {
			
			if (toggleAssignment(name, projectID, taskName)) {
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
