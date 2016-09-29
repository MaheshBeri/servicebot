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

	@DynamoDBAttribute(attributeName="VENDOR_STAGE_0")        
	String vendor_stage_0;
	
	@DynamoDBAttribute(attributeName="VENDOR_STAGE_1")        
	String vendor_stage_1;
	
	@DynamoDBAttribute(attributeName="VENDOR_STAGE_22")        
	String vendor_stage_22;
	
	public String getVendor_stage_22() {
		return vendor_stage_22;
	}
	public void setVendor_stage_22(String vendor_stage_22) {
		this.vendor_stage_22 = vendor_stage_22;
	}

	@DynamoDBAttribute(attributeName="TS_STAGE_0")        
	String ts_stage_0;
	
	@DynamoDBAttribute(attributeName="TS_STAGE_1")        
	String ts_stage_1;

	@DynamoDBAttribute(attributeName="TS_STAGE_2")        
	String ts_stage_2;
	
	public String getTs_stage_0() {
		return ts_stage_0;
	}
	public void setTs_stage_0(String ts_stage_0) {
		this.ts_stage_0 = ts_stage_0;
	}
	public String getTs_stage_1() {
		return ts_stage_1;
	}
	public void setTs_stage_1(String ts_stage_1) {
		this.ts_stage_1 = ts_stage_1;
	}
	public String getTs_stage_2() {
		return ts_stage_2;
	}
	public void setTs_stage_2(String ts_stage_2) {
		this.ts_stage_2 = ts_stage_2;
	}
	public String getVendor_stage_0() {
		return vendor_stage_0;
	}
	public void setVendor_stage_0(String vendorStage0) {
		this.vendor_stage_0 = vendorStage0;
	}
	public String getVendor_stage_1() {
		return vendor_stage_1;
	}
	public void setVendor_stage_1(String vendorStage1) {
		this.vendor_stage_1 = vendorStage1;
	}


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
