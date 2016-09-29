package chatbot;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="CASES")
public class Case {

	//Partition key
	@DynamoDBHashKey(attributeName="CASE_ID")
	int id;
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	@DynamoDBAttribute(attributeName="USER")        
	String user;
	public String getUser() { return user; }
	public void setUser(String user) { this.user = user; }    

	@DynamoDBAttribute(attributeName="STAGE")        
	String stage;
	public String getStage() { return stage; }
	public void setStage(String stage) { this.stage = stage; }    

	@DynamoDBAttribute(attributeName="CASE_STATE")        
	String status;
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }  
	
	@DynamoDBAttribute(attributeName="DESCRIPTION")        
	String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }                   

}
