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

public class CaseHelper {
	AmazonDynamoDBClient objClient;
	DynamoDB dynamoDB;
	DynamoDBMapper mapper;
	Case objCurrentCase=null;
	public Case getCase(){
		return objCurrentCase;
	}
	public void updateCaseState(String sState){
		objCurrentCase.setStage(sState);
		mapper.save(objCurrentCase);
	}
	
	public void updateCaseTS(String sCallBack,String sMessageTs){
		
		//Double objTS=Double.parseDouble(sMessageTs);
		System.out.println("CALL BACK TS"+ sCallBack+ " "+sMessageTs+ " "+sMessageTs);
		if(sCallBack.equals("vendor_engg_rec"))
			objCurrentCase.setTs_stage_0("d"+sMessageTs);		
		if(sCallBack.equals("vendor_buyer_rec"))
			objCurrentCase.setTs_stage_1("d"+sMessageTs);
		if(sCallBack.equals("vendor_opsmgr_rec"))
			objCurrentCase.setTs_stage_2("d"+sMessageTs);	
		mapper.save(objCurrentCase);
	}
	public void updateCaseRecomendation(String sCallBack,String sVendor){
		if(sCallBack.equals("vendor_engg_rec"))
			objCurrentCase.setVendor_stage_0(sVendor);		
		if(sCallBack.equals("vendor_buyer_rec"))
			objCurrentCase.setVendor_stage_1(sVendor);
		if(sCallBack.equals("vendor_opsmgr_rec"))
			objCurrentCase.setVendor_stage_22(sVendor);	
		mapper.save(objCurrentCase);
	}
	public CaseHelper(){
		 objClient=new AmazonDynamoDBClient(
				new EnvironmentVariableCredentialsProvider());
		dynamoDB = new DynamoDB(objClient);
		//Get all data set status inactive
		mapper = new DynamoDBMapper(objClient);
	
	}
	void init(String sTeamID){
			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS("ACTIVE"));
		
		eav.put(":val2", new AttributeValue().withS(sTeamID));

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("CASE_STATE = :val1 and TEAM_ID = :val2")
				.withExpressionAttributeValues(eav);

		List<Case> scanResult = mapper.scan(Case.class, scanExpression);
		int caseID;
		String sCurrentStage="";
		objCurrentCase=null;
		
		for (Case objCase : scanResult) {
			System.out.println(objCase);
			caseID=objCase.getId();
			sCurrentStage=objCase.getStage();
			
			System.out.println("Case ID "+caseID);				
			
			//mapper.save(objCase);
			objCurrentCase=objCase;
		}	
	}
}
