package com.ach.lambda.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TeamDAO {
	java.sql.Connection conn;

	final String tblName1 = "mydb.TASKS";
	final String tblName2 = "mydb.ASSignments";
	
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
            members.add(getTeammate(resultSet.getString("Name")));
        }
		return members;
		
	}
	private Teammate getTeammate(String name) {
		
		return new Teammate(name);
	}
}
