
package chatbot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

public class AppConfigHelper {

	
	public static AppConfig getConfigObject(String sAppId) throws Exception{
		// TODO Auto-generated constructor stub
		
		AmazonDynamoDBClient objClient=new AmazonDynamoDBClient(
				new EnvironmentVariableCredentialsProvider());
		
		//Get all data set status inactive
		DynamoDBMapper mapper = new DynamoDBMapper(objClient);

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(sAppId));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("APP_ID = :val2")
				.withExpressionAttributeValues(eav);

		List<AppConfig> scanResult = mapper.scan(AppConfig.class, scanExpression);
		System.out.println("App ID : " + sAppId);
		AppConfig objEntityResult=null;
		for (AppConfig objEntity : scanResult) {
			System.out.println(objEntity);
			objEntityResult=objEntity;
			
		}
		return objEntityResult;
	}

}
