package chatbot;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
@DynamoDBTable(tableName="RESPONSE_ATT")
public class ResponseAttachment {
	
	@DynamoDBHashKey(attributeName="RESPONSE_ID")
	String response_id;
	
	public String getResponse_id() {
		return response_id;
	}

	public void setResponse_id(String response_id) {
		this.response_id = response_id;
	}

	@DynamoDBAttribute(attributeName="STAGE")
	String stage;
	
	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getAttachment_txt() {
		return attachment_txt;
	}

	public void setAttachment_txt(String attachment_text) {
		this.attachment_txt = attachment_text;
	}

	@DynamoDBAttribute(attributeName="ATTACHMENT_TXT")
	String attachment_txt;
}
