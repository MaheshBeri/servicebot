package chatbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

public class ResponseAttachmentHelper {
	private List<ResponseAttachment> objResponseAttachmentList=null;
	public List<ResponseAttachment> getResponseAttachmentList(){
		return objResponseAttachmentList;
	}

	public String getPreparedResponseAttachmentString(Case objCurrentCase,boolean isUpdate){
		String sPreparedResponseAttachment=null;
		
		List<ResponseAttachment> objPreparedResponseAttachmentList =getPreparedResponseAttachmentList(objCurrentCase,isUpdate);
		for (ResponseAttachment objResponseAttachment : objPreparedResponseAttachmentList) {
			if(objResponseAttachment==null) continue;
			if(sPreparedResponseAttachment==null){
				sPreparedResponseAttachment="["+objResponseAttachment.getAttachment_txt();	
			}else{
				sPreparedResponseAttachment=sPreparedResponseAttachment+","+objResponseAttachment.getAttachment_txt();		
			}
		}
		if(sPreparedResponseAttachment!=null){
			sPreparedResponseAttachment+="]";
		}
		return sPreparedResponseAttachment;
	}
	public List<ResponseAttachment>  getPreparedResponseAttachmentList( Case objCurrentCase,boolean isUpdate){
		String sStage=objCurrentCase.getStage();
		List<ResponseAttachment> objPreparedResponseAttachmentList=new ArrayList<ResponseAttachment>();
		objPreparedResponseAttachmentList.add(null);
		objPreparedResponseAttachmentList.add(null);
		objPreparedResponseAttachmentList.add(null);
		objPreparedResponseAttachmentList.add(null);
		objPreparedResponseAttachmentList.add(null);
		objPreparedResponseAttachmentList.add(null);
		objPreparedResponseAttachmentList.add(null);
		objPreparedResponseAttachmentList.add(null);
		objPreparedResponseAttachmentList.add(null);
		objPreparedResponseAttachmentList.add(null);
		objPreparedResponseAttachmentList.add(null);
		ResponseAttachment objEngg=null;
		ResponseAttachment objPur=null;
		ResponseAttachment objMgr=null;
		//System.out.println("@@@stage" +sStage);
		for (ResponseAttachment objResponseAttachment : objResponseAttachmentList) {
			String sResponseId=objResponseAttachment.getResponse_id();
			String sStagePos=objResponseAttachment.getStage();
			//System.out.println("@@@response id" +sResponseId);
			if(sResponseId.equals("1")){
				int index=0;
				objPreparedResponseAttachmentList.remove(index);
				objPreparedResponseAttachmentList.add(index,objResponseAttachment);
							
				
			}
			if(sResponseId.equals("2")){
				//System.out.println("@@@ADDING to here" +objResponseAttachment);
				
					int index=6;
					objPreparedResponseAttachmentList.remove(index);
					objPreparedResponseAttachmentList.add(index,objResponseAttachment);
				
			}
			if(sStage.equals("1") ){
				if(sResponseId.equals("5")){
					int index=2;
					
					objPreparedResponseAttachmentList.remove(index);
					objPreparedResponseAttachmentList.add(index,objResponseAttachment);
				}
				if(sResponseId.equals("6")){
					int index=7;
					objPreparedResponseAttachmentList.remove(index);
					objPreparedResponseAttachmentList.add(index,objResponseAttachment);
				}
			}
			if(sStage.equals("2")){
				if(sResponseId.equals("7")){
					int index=2;
					
					objPreparedResponseAttachmentList.remove(index);
					objPreparedResponseAttachmentList.add(index,objResponseAttachment);
				}
				if(sResponseId.equals("8")){
					int index=7;
					objPreparedResponseAttachmentList.remove(index);
					objPreparedResponseAttachmentList.add(index,objResponseAttachment);
				}
			}
			if(sResponseId.equals("9")){
				objEngg=objResponseAttachment;
			}
			if(sResponseId.equals("10")){
				objPur=objResponseAttachment;
			}
			if(sResponseId.equals("11")){
				objMgr=objResponseAttachment;
			}
		}
		//System.out.println("@@@PART 1" +objPreparedResponseAttachmentList);
		if(null!=objCurrentCase.getVendor_stage_0() &&
				objCurrentCase.getVendor_stage_0().equals("1")){
			int index=3;
			objPreparedResponseAttachmentList.remove(index);
			objPreparedResponseAttachmentList.add(index,objEngg);
			
			
		}
		//System.out.println("@@@stage0 " +objCurrentCase.getVendor_stage_0());
		//System.out.println("@@@stage1 " +objCurrentCase.getVendor_stage_1());
		//System.out.println("@@@stage2 " +objCurrentCase.getVendor_stage_22());
		if(null!=objCurrentCase.getVendor_stage_0() &&
				objCurrentCase.getVendor_stage_0().equals("2")){
			int index=8;
			objPreparedResponseAttachmentList.remove(index);
			objPreparedResponseAttachmentList.add(index,objEngg);
			
			
			
		}
		if(null!=objCurrentCase.getVendor_stage_1() &&
				objCurrentCase.getVendor_stage_1().equals("1")){
			int index=4;
			objPreparedResponseAttachmentList.remove(index);
			objPreparedResponseAttachmentList.add(index,objPur);
			
			
		}
		if(null!=objCurrentCase.getVendor_stage_1() &&
				objCurrentCase.getVendor_stage_1().equals("2")){
			int index=9;
			objPreparedResponseAttachmentList.remove(index);
			objPreparedResponseAttachmentList.add(index,objPur);
		}	
		if(null!=objCurrentCase.getVendor_stage_22() &&
				objCurrentCase.getVendor_stage_22().equals("1")){
			int index=5;
			objPreparedResponseAttachmentList.remove(index);
			objPreparedResponseAttachmentList.add(index,objMgr);
		}
		if(null!=objCurrentCase.getVendor_stage_22() &&
				objCurrentCase.getVendor_stage_22().equals("2")){
			int index=10;
			objPreparedResponseAttachmentList.remove(index);
			objPreparedResponseAttachmentList.add(index,objMgr);
		}	
		//System.out.println("Prepared ! "+objPreparedResponseAttachmentList);
		return objPreparedResponseAttachmentList;
	}
	/*List<ResponseAttachment> getData(DynamoDBMapper mapper ,String key,String val){
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(key, new AttributeValue().withS(val));
		DynamoDBQueryExpression<ResponseAttachment> scanExpression = new DynamoDBQueryExpression<ResponseAttachment>()
				.withKeyConditionExpression("RESPONSE_ID = "+key)
				.withExpressionAttributeValues(eav);		
		return mapper.query(ResponseAttachment.class, scanExpression);
	}
	*/
	public void init(){
		AmazonDynamoDBClient objClient=new AmazonDynamoDBClient(
				new EnvironmentVariableCredentialsProvider());
		System.out.println("Here 2");
		//Get all data set status inactive
		DynamoDBMapper mapper = new DynamoDBMapper(objClient);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		objResponseAttachmentList = mapper.scan(ResponseAttachment.class, scanExpression);
		System.out.println("Here 3");
		/*AmazonDynamoDBClient objClient=new AmazonDynamoDBClient(
				new EnvironmentVariableCredentialsProvider());		
		ScanResult result = null;
		ScanRequest req = new ScanRequest();
		req.setTableName("RESPONSE_ATT");
		result = objClient.scan(req);
		List<ResponseAttachment> teams= new ArrayList<ResponseAttachment>();
		
		for (Map<String, AttributeValue> item : result.getItems()){
			item.get("RESPONSE_ID").getS();
			item.get("TEAM_NAME").getS();
			
		}*/
		
		/*
		System.out.println("Here 0");
		if(objResponseAttachmentList==null)
			objResponseAttachmentList = new ArrayList<ResponseAttachment> ();
		System.out.println("Here 1");
		AmazonDynamoDBClient objClient=new AmazonDynamoDBClient(
				new EnvironmentVariableCredentialsProvider());
		System.out.println("Here 2");
		//Get all data set status inactive
		DynamoDBMapper mapper = new DynamoDBMapper(objClient);
		System.out.println("Here 3");
		objResponseAttachmentList.addAll(getData(mapper,":val1","1"));
		System.out.println("Here 4");
		objResponseAttachmentList.addAll(getData(mapper,":val1","2"));
		System.out.println("Here 5");
		objResponseAttachmentList.addAll(getData(mapper,":val1","3"));
		System.out.println("Here 6");
		objResponseAttachmentList.addAll(getData(mapper,":val1","4"));
		System.out.println("Here 7");
		objResponseAttachmentList.addAll(getData(mapper,":val1","9"));
		System.out.println("Here 8");
		
		//System.out.println("Cases : " + sTeamId);
		
		//for (ResponseAttachment objResponseAttachment : objResponseAttachmentList) {
			//System.out.println(objResponseAttachment.getResponse_id());
			//System.out.println(objResponseAttachment.getAttachment_txt());
			//objEntityResult=objEntity;
			
		//}*/
	}
}
