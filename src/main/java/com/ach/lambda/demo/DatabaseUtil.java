package com.ach.lambda.demo;


import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseUtil {

	// These should never be stored directly in code.  I am doing this quickly complete the 
	// demonstration code. The appropriate solution is to store these values in environment
	// variables that are accessed by the Lambda function at run time like this
	//
	//  dbUsername = System.getenv("dbUsername");
	//  dbPassword = System.getenv("dbPassword");
	//  rdsMySqlDatabaseUrl = System.getenv("rdsMySqlDatabaseUrl");
	//
	// https://docs.aws.amazon.com/lambda/latest/dg/env_variables.html
	//
	// The above link shows how to do that.
	public static String rdsMySqlDatabaseUrl;
	public static String dbUsername;
	public static String dbPassword;
		
	public final static String jdbcTag = "jdbc:mysql://";
	public final static String rdsMySqlDatabasePort = "3306";
	public final static String multiQueries = "?allowMultiQueries=true";
	   
	public final static String dbName = "mydb";           // Whatever Schema you created in tutorial.
	public final static String testingName = "tmp";       // used for testing (also default created)
	
	// pooled across all usages.
	static Connection conn;
 
	/**
	 * Singleton access to DB connection to share resources effectively across multiple accesses.
	 */
	protected static Connection connect() throws Exception {
		if (conn != null) { return conn; }
		
		boolean useTestDB = System.getenv("TESTING") != null;
		
		// this is resistant to any SQL-injection attack since we choose one of two possible ones.
		String schemaName = dbName;
		if (useTestDB) { 
			schemaName = testingName;
			System.out.println("USE TEST DB:" + useTestDB);
		}
		
//		dbUsername = System.getenv("dbUsername");
		dbUsername = "admin";
		if (dbUsername == null) {
			System.err.println("Environment variable dbUsername is not set!");
		}
//		dbPassword = System.getenv("dbPassword");
		dbPassword = "s9VdIj3iWihzd4X0gCis";
		if (dbPassword == null) {
			System.err.println("Environment variable dbPassword is not set!");
		}
//		rdsMySqlDatabaseUrl = System.getenv("rdsMySqlDatabaseUrl");
		rdsMySqlDatabaseUrl = "database-1.czi81oo6gggs.us-east-2.rds.amazonaws.com";
		if (rdsMySqlDatabaseUrl == null) {
			System.err.println("Environment variable rdsMySqlDatabaseUrl is not set!");
		}
		
		try {
			//System.out.println("start connecting......");
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection(
					jdbcTag + rdsMySqlDatabaseUrl + ":" + rdsMySqlDatabasePort + "/" + schemaName + multiQueries,
					dbUsername,
					dbPassword);

			//System.out.println("Database has been connected successfully.");
			return conn;
		} catch (Exception ex) {
			ex.printStackTrace();
//			throw new Exception(rdsMySqlDatabaseUrl);
			throw new Exception("Failed in database connection");
//			throw ex;
		}
	}
}
