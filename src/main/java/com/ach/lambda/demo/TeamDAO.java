package com.ach.lambda.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
}


