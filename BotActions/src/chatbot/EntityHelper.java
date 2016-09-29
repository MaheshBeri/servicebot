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

public class EntityHelper {

	
	public static Entity getEntityObject(String sTeamId) throws Exception{
		// TODO Auto-generated constructor stub
		AmazonDynamoDBClient objClient=new AmazonDynamoDBClient(
				new EnvironmentVariableCredentialsProvider());
		
		//Get all data set status inactive
		DynamoDBMapper mapper = new DynamoDBMapper(objClient);

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val2", new AttributeValue().withS(sTeamId));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("TEAM_ID = :val2")
				.withExpressionAttributeValues(eav);

		List<Entity> scanResult = mapper.scan(Entity.class, scanExpression);
		System.out.println("Cases : " + sTeamId);
		Entity objEntityResult=null;
		for (Entity objEntity : scanResult) {
			System.out.println(objEntity);
			objEntityResult=objEntity;
			
		}
		return objEntityResult;
	}

}
