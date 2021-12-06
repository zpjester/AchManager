package com.ach.lambda.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TaskDAO {
	
	java.sql.Connection conn;
	
	final String tblName = "mydb.TASKS";
	
	public TaskDAO() throws Exception{
		try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    		throw e;
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
	public boolean addTask(String task, String p) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectID = ? and Name = ?;");
            
            ps.setString(1, p);
            ps.setString(2,  task);
            
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                resultSet.close();
                return false;
            }
            String outline;
            try {
            ps = conn.prepareStatement("select MAX(Outline) as mostRecent FROM mydb.TASKS (WHERE ProjectID = ? and ParentTask = ?);");
            outline = String.valueOf(Integer.valueOf(ps.executeQuery().getString("mostRecent")) + 1);
            }
            catch(Exception e) {
            	outline = "1";
            }
            ps = conn.prepareStatement("insert into mydb.TASKS (TASKSid, Name, ProjectID, OutlineID, ParentTask, isCompleted, isTerminal) values(?,?,?,?, null, 0, 1);");
            ps.setString(1,  UUID.randomUUID().toString().replace("-", ""));
            ps.setString(2,  task);
            ps.setString(3,  p);
            ps.setString(4,  outline);
            
            
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
            
            if (resultSet.next()) {
                constant = generateTask(resultSet);
            }
            
            
            return constant;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("getTask Failed in getting project: " + e.getMessage());
        }
	}
	public LinkedList<Task> getTaskList(String p) throws Exception{
		LinkedList<Task> constant = new LinkedList<Task>();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectID = ?;");
//		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName);
		
		ps.setString(1, p);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            constant.add(new Task(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),
            				new LinkedList<Teammate>(),resultSet.getString(5),resultSet.getBoolean(6),resultSet.getBoolean(7)));
           //resultSet.next();
        }
        resultSet.close();
        ps.close();
//        constant.add(new Task("Test Task"));
		return constant;
		
	}

	private Task generateTask(ResultSet resultSet) throws SQLException {
		
		return new Task(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),new LinkedList<Teammate>(),resultSet.getString(5),resultSet.getBoolean(6),resultSet.getBoolean(7));
	}

}
