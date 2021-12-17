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
	public boolean toggleComplete(String name, String p ) throws Exception {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectID = ? and Name = ?;",

				ResultSet.TYPE_SCROLL_SENSITIVE, 
				ResultSet.CONCUR_UPDATABLE);

		ps.setString(1, p);
		ps.setString(2,  name);

		ResultSet resultSet = ps.executeQuery();

		if (!(resultSet.next())) {
			return false;
		}
		boolean current = resultSet.getBoolean("isCompleted");

		ps = conn.prepareStatement("UPDATE " + tblName + " SET isCompleted = ? WHERE ProjectID = ? and Name = ?;"
				);
//				,ResultSet.TYPE_SCROLL_SENSITIVE, 
//				ResultSet.CONCUR_UPDATABLE);

		ps.setString(2, p);

		ps.setString(3, name);

		ps.setBoolean(1, !current);
		
		ps.execute();

		return true;
	}
	public int getComplete(String p) throws Exception {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectID = ?;"
//				);
				, ResultSet.TYPE_SCROLL_SENSITIVE, 
				ResultSet.CONCUR_UPDATABLE);

		ps.setString(1, p);
		ResultSet resultSet = ps.executeQuery();
		resultSet.last();
		int total = resultSet.getRow();

		if(total == 0) {
			return 0;
		}

		ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectID = ? and isCompleted = ?;"
//				);
				,ResultSet.TYPE_SCROLL_SENSITIVE, 
				ResultSet.CONCUR_UPDATABLE);

		ps.setString(1, p);
		ps.setBoolean(2,  true);

		resultSet = ps.executeQuery();
		resultSet.last();
		// moves cursor to the last row
		int com = resultSet.getRow();



		return (int)((((double)(com))/((double)(total))) * 100);
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
				ps = conn.prepareStatement("select MAX(OutlineID) as mostRecent FROM mydb.TASKS WHERE ProjectID = ? and ParentTask is ?;");
				ps.setString(1, p);
				ps.setString(2, null);
				resultSet = ps.executeQuery();
				resultSet.next();
				outline = String.valueOf(Integer.valueOf(resultSet.getString("mostRecent")) + 1);
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
			throw new Exception("Failed to add task: " + e.getMessage());
		}
	}
	public boolean addSubTask(String task, String p, String parent) throws Exception {
		try {
			String parentID;
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectID = ? and Name = ?;");

			ps.setString(1, p);
			ps.setString(2,  parent);

			ResultSet resultSet = ps.executeQuery();

			// already present?
			if(resultSet.next()) {
				parentID = resultSet.getString("TASKSid");
			}
			else {
				resultSet.close();
				return false;
			}
			
			
			
			ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectID = ? and Name = ?;");

			ps.setString(1, p);
			ps.setString(2,  task);

			resultSet = ps.executeQuery();

			// already present?
			while (resultSet.next()) {
				resultSet.close();
				return false;
			}
			
			String outline;
			try {
				ps = conn.prepareStatement("select MAX(OutlineID) as mostRecent FROM mydb.TASKS WHERE ProjectID = ? and ParentTask is ?;");
				ps.setString(1, p);
				ps.setString(2, parentID);
				resultSet = ps.executeQuery();
				resultSet.next();
				outline = String.valueOf(Integer.valueOf(resultSet.getString("mostRecent")) + 1);
			}
			catch(Exception e) {
				outline = "1";
			}



			ps = conn.prepareStatement("insert into mydb.TASKS (TASKSid, Name, ProjectID, OutlineID, ParentTask, isCompleted, isTerminal) values(?,?,?,?, ?, 0, 1);");
			ps.setString(1,  UUID.randomUUID().toString().replace("-", ""));
			ps.setString(2,  task);
			ps.setString(3,  p);
			ps.setString(4,  outline);
			ps.setNString(5, parentID);


			//            ps.setBoolean(2,  constant.isArchived);
			ps.execute();
			return true;


		} catch (Exception e) {
			throw new Exception("Failed to add task: " + e.getMessage());
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
//			constant.add(new Task(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),
//					new LinkedList<Teammate>(),resultSet.getString(5),resultSet.getBoolean(6),resultSet.getBoolean(7)));
			constant.add(generateTask(resultSet));
			//resultSet.next();
		}
		resultSet.close();
		ps.close();
		//        constant.add(new Task("Test Task"));
		return constant;

	}

	private Task generateTask(ResultSet resultSet) throws Exception {
		TeamDAO dao = new TeamDAO();
		return new Task(resultSet.getString(1),resultSet.getString(2),resultSet.getString(4),dao.getMemberList(resultSet.getString(2)),resultSet.getString(5),resultSet.getBoolean(7),resultSet.getBoolean(6));
	}
	public boolean renameTask(String projectID, String name, String newName) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectID = ? and Name = ?;");

		ps.setString(1, projectID);
		ps.setString(2,  name);

		ResultSet resultSet = ps.executeQuery();

		if (!(resultSet.next())) {
			return false;
		}
		//boolean current = resultSet.getBoolean("isCompleted");

		ps = conn.prepareStatement("UPDATE " + tblName + " SET Name = ? WHERE ProjectID = ? and Name = ?;"
				);
//				,ResultSet.TYPE_SCROLL_SENSITIVE, 
//				ResultSet.CONCUR_UPDATABLE);

		ps.setString(2, projectID);

		ps.setString(3, name);

		ps.setString(1, newName);
		
		ps.execute();

		return true;
	}


}
