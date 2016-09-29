/*
* Copyright (c) 2016. All rights reserved.
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This code is free software; you can redistribute it and/or modify it
* under the terms of the GNU General Public License version 2 only, as
* published by the Free Software Foundation.
*/
package chatbot;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
public class LambdaFunctionHandler implements RequestHandler<RequestClass, ResponseClass>{
	@Override
	public ResponseClass handleRequest(RequestClass objRequest, Context context) {
		ResponseClass objResponseClass=new ResponseClass();
		try{
			
			
			if (objRequest.getToken()==null)
				return objResponseClass;
			AppConfig objConfig=AppConfigHelper.getConfigObject(objRequest.getApi_app_id());
			if(!objRequest.getToken().equals(objConfig.getSlack_token()))
				return objResponseClass;
			AmazonDynamoDBClient objClient=new AmazonDynamoDBClient(
					new EnvironmentVariableCredentialsProvider());
			DynamoDB dynamoDB = new DynamoDB(objClient);
			//Get all data set status inactive
			DynamoDBMapper mapper = new DynamoDBMapper(objClient);
			String sAccessToken=EntityHelper.getEntityObject(objRequest.getTeam_id()).getAccess_token();
			String sChannel_1=EntityHelper.getEntityObject(objRequest.getTeam_id()).getChannel_1();

			//context.getLogger().log("CHALLENGE : " + objRequest.getChallenge());
			if(objRequest.getChallenge()!=null && objRequest.getType().equals("url_verification")){    		
				objResponseClass.setChallenge(objRequest.getChallenge());
				return objResponseClass;
			}
			//"subtype": "bot_message"
			String sSubType=objRequest.getEvent().getSubtype();
			context.getLogger().log("SUBTYPE : " + sSubType);
			if(sSubType!=null)// && (sSubType.equals("channel_join")||sSubType.equals("bot_message") || sSubType.equals("message_changed") || sSubType.equals("message_deleted")) ){
				return objResponseClass;
			//}
			context.getLogger().log("Now working: "+sSubType);
			Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			eav.put(":val1", new AttributeValue().withS("ACTIVE"));
			System.out.println("Team ID "+objRequest.getTeam_id());
			eav.put(":val2", new AttributeValue().withS(objRequest.getTeam_id()));
			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
					.withFilterExpression("CASE_STATE = :val1 and TEAM_ID = :val2")
					.withExpressionAttributeValues(eav);
			List<Case> scanResult = mapper.scan(Case.class, scanExpression);
			int caseID;
			String sCurrentStage="";
			Case objCurrentCase=null;
			context.getLogger().log("Cases : " + scanResult);
			for (Case objCase : scanResult) {
				System.out.println(objCase);
				caseID=objCase.getId();
				sCurrentStage=objCase.getStage();
				System.out.println("ID "+caseID);				
				objCase.setUser(objRequest.getEvent().getUsername());
				mapper.save(objCase);
				objCurrentCase=objCase;
			}		 
			String sResponse=objRequest.getEvent().getText().trim();
			System.out.println(" You typed "+ sResponse);
			
			String message_response="";			
			String message_attachment="";
			Table objTable = dynamoDB.getTable("RESPONSE_ACTIONS");
			Map<String,Map<String,List<String>>> objResponseActions= new HashMap<String,Map<String,List<String>>>() ;
			//objResponseActions.put()
			QuerySpec spec = new QuerySpec()
					.withKeyConditionExpression("STAGE = :val1")
					.withValueMap(new ValueMap()
							.withString(":val1", sCurrentStage));
			ItemCollection<QueryOutcome> items = objTable.query(spec);
			Iterator<Item> iterator = items.iterator();
			while (iterator.hasNext()) {
				ObjectMapper objMapper = new ObjectMapper();					
				String jsonInString=iterator.next().toJSONPretty();
				//JSON from String to Object
				ResponseActions objResponseActionsRow= objMapper.readValue(jsonInString, ResponseActions.class);
				Map<String,List<String>> objResponseList=objResponseActions.get(objResponseActionsRow.getSTAGE());
				if(objResponseList==null){
					objResponseList= new HashMap<String,List<String>>();
					objResponseActions.put(objResponseActionsRow.getSTAGE(),objResponseList);	
				}				
				List<String> objResponseListData= new ArrayList<String>();
				objResponseList.put(objResponseActionsRow.getREQUESTTEXT(),objResponseListData);
				objResponseListData.add(sChannel_1);
				objResponseListData.add(objResponseActionsRow.getRESPONSETEXT());	
				objResponseListData.add(objResponseActionsRow.getResponse_attachment());
			}
			Map<String,List<String>> objResponseList=objResponseActions.get(sCurrentStage);
			if(objResponseList==null)
				return objResponseClass;
			List<String> objResponseListData=objResponseList.get(sResponse);
			if(objResponseListData==null){
				objResponseListData=objResponseList.get("Other");
				if(objResponseListData==null)
					return objResponseClass;
			}
			message_response=objResponseListData.get(1);
			message_attachment=objResponseListData.get(2);
			objCurrentCase.setStage("1");
			mapper.save(objCurrentCase);
			String sURL="https://slack.com/api/chat.postMessage";
			HttpClient httpClient=HttpClients.createDefault();
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("token",sAccessToken));
			urlParameters.add(new BasicNameValuePair("channel", sChannel_1));
			if(message_response!=null && !message_response.trim().isEmpty())
				urlParameters.add(new BasicNameValuePair("text", message_response));
			urlParameters.add(new BasicNameValuePair("attachments",message_attachment));
			urlParameters.add(new BasicNameValuePair("as_user","false"));
			HttpPost httpPost= new HttpPost(sURL);
			httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpResponse.getEntity().getContent()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			System.out.println("POST Response Status:: "
					+ httpResponse.getStatusLine().getStatusCode()+ " "+response.toString());
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
			context.getLogger().log("Error 1: " + e.toString());
		}
		return objResponseClass;
	}
}
