package chatbot;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="APP_CONFIG")
public class AppConfig {
	//Partition key
		@DynamoDBHashKey(attributeName="APP_ID")
		String app_id;
		@DynamoDBAttribute(attributeName="SLACK_TOKEN")        
		String slack_token;
		public String getApp_id() {
			return app_id;
		}
		public void setApp_id(String app_id) {
			this.app_id = app_id;
		}
		public String getSlack_token() {
			return slack_token;
		}
		public void setSlack_token(String slack_token) {
			this.slack_token = slack_token;
		}
			
}
