package chatbot;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="ENTITY")
public class Entity {

	//Partition key
	@DynamoDBHashKey(attributeName="TEAM_ID")
	String team_id;
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getChannel_1() {
		return channel_1;
	}
	public void setChannel_1(String channel_1) {
		this.channel_1 = channel_1;
	}
	public String getChannel_2() {
		return channel_2;
	}
	public void setChannel_2(String channel_2) {
		this.channel_2 = channel_2;
	}
	public String getChannel_3() {
		return channel_3;
	}
	public void setChannel_3(String channel_3) {
		this.channel_3 = channel_3;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}


	@DynamoDBAttribute(attributeName="ACCESS_TOKEN")        
	String access_token;
	
	@DynamoDBAttribute(attributeName="CHANNEL_1")        
	String channel_1;
   

	@DynamoDBAttribute(attributeName="CHANNEL_2")        
	String channel_2;
	
	@DynamoDBAttribute(attributeName="CHANNEL_3")        
	String channel_3;
	
	@DynamoDBAttribute(attributeName="TEAM_NAME")        
	String team_name;
	
}
