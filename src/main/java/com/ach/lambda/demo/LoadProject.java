package com.ach.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.LinkedList;

import org.json.simple.JSONObject; 
public class LoadProject implements RequestHandler<LoadProjectRequest, JSONObject> {
	LambdaLogger logger;
    @Override
    public JSONObject handleRequest(LoadProjectRequest input, Context context) {
    	String projectID = input.projectID;
        logger = context.getLogger();
		
		logger.log("input" + input + "\n");
		
		ProjectData response = null;
		LinkedList<Task> taskList = new LinkedList<Task>();
		LinkedList<Teammate> teammateList = new LinkedList<Teammate>();
		
		
		try {
			TaskDAO taskDao = new TaskDAO();
			taskList = taskDao.getTaskList(projectID);
			TeamDAO teamDAO = new TeamDAO();
			teammateList = teamDAO.getTeammateList(projectID);			
			
		} catch (Exception e) {
			e.printStackTrace();
			//response = new ProjectData("Something went very wrong loading project " + input.projectID + " we have no idea why");
		}		
		
		
		try {
			ProjectsDAO dao = new ProjectsDAO();
			response = dao.getProjectData(projectID);
			
		} catch (Exception e) {
			e.printStackTrace();
			response = new ProjectData("Project " + input.projectID + " not found", 404);
		}
		
		
		if(response == null) {
			response = new ProjectData("Project " + input.projectID + " not found", 404);
		}
		
		
		response.setTaskList(taskList);
		response.setTeammateList(teammateList);
		
        return response.toJSON();
    }

}
