package com.ach.lambda.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ProjectsDAO {
	
java.sql.Connection conn;
	
	final String tblName = "mydb.PROJECTS";
	
	public ProjectsDAO() throws Exception{
		try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
//    		throw e;
    	}
	}

	public boolean addProject(Project constant) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectName = ?;");
            
            ps.setString(1, constant.projectID);
            
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
            	Project c = generateProject(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO " + tblName + " (ProjectName) values(?);");
            ps.setString(1,  constant.projectID);
//            ps.setBoolean(2,  constant.isArchived);
            ps.execute();
            return true;
            

        } catch (Exception e) {
            throw new Exception("Failed to create project: " + e.getMessage());
        }
	}

	public Project getProject(String name) throws Exception {
        try {
            Project constant = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectName=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                constant = generateProject(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return constant;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("getProject Failed in getting project: " + e.getMessage());
        }
	}

	private Project generateProject(ResultSet resultSet) throws SQLException {
		String name  = resultSet.getString("ProjectName");
        Boolean value = resultSet.getBoolean("isAchived");
        return new Project (name, value);
	}

}
