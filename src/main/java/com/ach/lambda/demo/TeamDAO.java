package com.ach.lambda.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TeamDAO {
	java.sql.Connection conn;

	final String tblName1 = "mydb.MEMBERS";
	
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
	
	
	
	public boolean getTeammate(String name, String projectID) throws SQLException{
		
		
LinkedList<Teammate> members = new LinkedList<Teammate>();
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName1 + " WHERE Pid = ?;");
		ps.setString(1, name);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            members.add(new Teammate(resultSet.getString(2)));
        }
		resultSet.close();
		ps.close();
		
		for(Teammate tm: members) {
			if(tm.name == name) {
				return true;
			}
		}
		return false;
	}
	
	private boolean addTeammate(String name, String p) throws SQLException {
		
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

        ps = conn.prepareStatement("INSERT INTO " + tblName1 + " (MEMDERSid, Name, Pid) values(?,?);");
        ps.setString(1, UUID.randomUUID().toString().replace("-", ""));
        ps.setString(2, name);
        ps.setString(3, p);
//        ps.setBoolean(2,  constant.isArchived);
        ps.execute();
        return true;
		
		//return false;
	}
}


