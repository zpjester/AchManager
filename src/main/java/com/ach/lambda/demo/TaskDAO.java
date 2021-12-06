package com.ach.lambda.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaskDAO {
	
	java.sql.Connection conn;
	
	final String tblName = "mydb.TASKS";
	
	public TaskDAO() throws Exception{
		try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
//    		throw e;
    	}
	}
	
	public boolean addTask(Task task, Project p) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectID = ? and Name = ?;");
            
            ps.setString(1, p.projectID);
            ps.setString(2,  task.name);
            
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("insert into mydb.TASKS (TASKSid, Name, ProjectID, OutlineID, ParentTask, isCompleted, isTerminal) values(?,?,?,?, null, 0, 1);");
            ps.setString(1,  task.tasksID);
            ps.setString(2,  task.name);
            ps.setString(3,  p.projectID);
            ps.setString(4,  task.outlineID);
            
            
//            ps.setBoolean(2,  constant.isArchived);
            ps.execute();
            return true;
            

        } catch (Exception e) {
            throw new Exception("Failed to create project: " + e.getMessage());
        }
	}
	public Task getTask(String t, String p) throws Exception {
        try {
            Task constant = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectID = ? and Name = ?;");
            ps.setString(1, p);
            ps.setString(2, t);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                constant = generateTask(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return constant;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("getTask Failed in getting project: " + e.getMessage());
        }
	}
	public LinkedList<Task> getTaskList(String p) throws Exception{
		LinkedList<Task> constant = new LinkedList<Task>();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectID = ?;");
		ps.setString(1, p);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            constant.add(getTask(resultSet.getString("Name"), p));
        }
        
		return constant;
		
	}

	private Task generateTask(ResultSet resultSet) throws SQLException {
		
		return new Task(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),null,resultSet.getString(5),resultSet.getBoolean(6),resultSet.getBoolean(7));
	}

}
