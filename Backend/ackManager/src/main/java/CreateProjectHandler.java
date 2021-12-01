import java.io.ByteArrayInputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;



public class CreateProjectHandler implements RequestHandler<CreateProjectRequest,ProjectData>{

LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
		
	// Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	public static final String REAL_BUCKET = "constants/";
	
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean createProject(String name) throws Exception { 
		if (logger != null) { logger.log("in createConstant"); }
		ProjectsDAO dao = new ProjectsDAO();
		
		// check if present
		Project exist = dao.getProject(name);
		Project constant = new Project (name);
		if (exist == null) {
			return dao.addProject(constant);
		} else {
			return false;
		}
	}
	
	/** Create S3 bucket
	 * 
	 * @throws Exception 
	 */
	
	
	@Override 
	public ProjectData handleRequest(CreateProjectRequest req, Context context)  {
		/*logger = context.getLogger();
		logger.log(req.toString());
		
		ProjectData response;
		try {
			
			if (createProject(req.name)) {
				response = new ProjectData(req.name, null, null, false);
			} else {
				response = new ProjectData(req.name, null, null, false, 422);
			}
		} catch (Exception e) {
			response = new ProjectData("Unable to create constant: " + req.name + "(" + e.getMessage() + ")", null, null, false, 400);
		}
		*/
		return new ProjectData("bob", null, null, false);
	}
}
