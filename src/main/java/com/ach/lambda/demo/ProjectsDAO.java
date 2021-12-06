package com.ach.lambda.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
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
	public ProjectList getProjectList() throws Exception {
        try {
            ProjectList constant = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            if (!resultSet.isAfterLast()) {
                constant = generateProjectList(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return constant;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("getProject Failed in getting project: " + e.getMessage());
        }
	}
	private ProjectList generateProjectList(ResultSet resultSet) throws SQLException {
		LinkedList<Project> projs = new LinkedList<Project>();
		resultSet.next();
		if(!resultSet.isAfterLast()){
			projs.add(new Project(resultSet.getString(1), resultSet.getBoolean(2)));
			resultSet.next();
		}
		return new ProjectList(projs);
	}

	public ProjectData getProjectData(String name) throws Exception {
		
		try {
            ProjectData constant = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE ProjectName=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            if (!resultSet.isAfterLast()) {
                constant = generateProjectData(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return constant;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("getProject Failed in getting project: " + e.getMessage());
        }
	}

	private ProjectData generateProjectData(ResultSet resultSet) throws SQLException {
		String name  = resultSet.getString("ProjectName");
        return new ProjectData (name, 200);
	}

	private Project generateProject(ResultSet resultSet) throws SQLException {
		String name  = resultSet.getString("ProjectName");
        Boolean value = resultSet.getBoolean("isAchived");
        return new Project (name, value);
	}

}
