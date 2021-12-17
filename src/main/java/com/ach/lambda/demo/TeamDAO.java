package com.ach.lambda.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TeamDAO {
	java.sql.Connection conn;

	final String tblName1 = "mydb.MEMBERS";
	final String tblName2 = "mydb.ASSIGNMENTS";
	final String tblName3 = "mydb.TASKS";
	
	public TeamDAO() throws Exception{
		try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    		throw e;
    	}
	}
	public LinkedList<Teammate> getTeammateList(String p) throws SQLException{
		LinkedList<Teammate> members = new LinkedList<Teammate>();
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName1 + " WHERE Pid = ?;");
		ps.setString(1, p);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            members.add(new Teammate(resultSet.getString(2)));
        }
		resultSet.close();
		ps.close();
//		members.add(new Teammate("Trumbus"));
		return members;

		
	}
	
	public LinkedList<Teammate> getMemberList(String t) throws SQLException{
		LinkedList<Teammate> members = new LinkedList<Teammate>();
		
		PreparedStatement ps = conn.prepareStatement("select mem.Name from mydb.TASKS inner join (SELECT ASSIGNMENTS.Tid as tid, MEMBERS.Name as name FROM mydb.ASSIGNMENTS inner join MEMBERS on MEMBERS.MEMDERSid = ASSIGNMENTS.Mid) as mem on TASKS.TASKSid = mem.tid where TASKS.Name = ?;");
		ps.setString(1, t);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            members.add(new Teammate(resultSet.getString(1)));
        }
		resultSet.close();
		ps.close();
//		members.add(new Teammate("Trumbus"));
		return members;

		
	}
	
	
	

	public boolean getTeammate(String name, String projectID) throws SQLException{
		
		
		LinkedList<Teammate> members = new LinkedList<Teammate>();
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName1 + " WHERE Pid = ?;");
		ps.setString(1, projectID);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            members.add(new Teammate(resultSet.getString(2)));
        }
		resultSet.close();
		ps.close();
		
		for(Teammate tm: members) {
			if(tm.name.equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean addTeammate(String name, String p) throws SQLException {
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName1 + " WHERE Pid = ? and Name = ?;");
        
        ps.setString(1, p);
        ps.setString(2, name);
        
        ResultSet resultSet = ps.executeQuery();
        
        // already present?
        while (resultSet.next()) {
        	//Project c = generateProject(resultSet);
            resultSet.close();
            return false;
        }

        ps = conn.prepareStatement("INSERT INTO " + tblName1 + " (MEMDERSid, Name, Pid) values(?,?, (SELECT ProjectName from mydb.PROJECTS WHERE ProjectName=?));");
//        ps = conn.prepareStatement("INSERT INTO " + tblName1 + " (MEMDERSid, Name, Pid) values(?,?,?);");
        ps.setString(1, UUID.randomUUID().toString().replace("-", ""));
        ps.setString(2, name);
        ps.setString(3, p);
//        ps.setBoolean(2,  constant.isArchived);
        ps.execute();
        return true;
		
		//return false;
	}
	public boolean removeTeammate(String name, String p) throws SQLException {
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName1 + " WHERE Pid = ? and Name = ?;");
        
        ps.setString(1, p);
        ps.setString(2, name);
        
        ResultSet resultSet = ps.executeQuery();
        
        // already present?
        while (resultSet.next()) {
        	 ps = conn.prepareStatement("DELETE FROM " + tblName1 + " WHERE MEMDERSid = ?");//MEMDER?
             ps.setString(1, resultSet.getString(1));
//             ps.setBoolean(2,  constant.isArchived);
             ps.execute();
             return true;
            
        }

       
        resultSet.close();
        return false;
		//return false;
	}
	public boolean toggleTeammate(String name, String taskName, String projectID) throws Exception {
		TaskDAO tdao = new TaskDAO();
		Task task = tdao.getTask(taskName, projectID);
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName1 + " WHERE Pid = ? and Name = ?;");
        
        ps.setString(1, projectID);
        ps.setString(2, name);
        
        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        String mid = resultSet.getString(1);
		
		 ps = conn.prepareStatement("SELECT * FROM " + tblName2 + " WHERE tid = ? and mid = ?;");
        
        ps.setString(1, task.tasksID);
        ps.setString(2, mid);
		
         resultSet = ps.executeQuery();

    	//ps.close();
        while (resultSet.next()) {
        	//Project c = generateProject(resultSet);
        	ps = conn.prepareStatement("DELETE FROM " + tblName2 + " WHERE tid = ? and mid = ?;");
            
            ps.setString(1, task.tasksID);
            ps.setString(2, mid);
        	ps.execute();
            resultSet.close();
            return true;
        }
        PreparedStatement ps2 = conn.prepareStatement("INSERT INTO " + tblName2 + " (Tid, Mid) values((SELECT tasksID FROM "+ tblName3 + " WHERE TASKsid = ?),(SELECT MEMDERSid from "+ tblName1 + " WHERE MEMDERSid = ?));");
        ps2.setString(1, task.tasksID);
        ps2.setString(2, mid);
    	ps2.execute();
		return true;
	}
}


