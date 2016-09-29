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
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
/*
 * Service Bot
 *	Lambda Function: ServiceAlertHandler
 *	Purpose:1) Post Service Alert to your team and channel based on event received from IoT 
 *  		 	2) IoT Rule will pass the telemetry data and team_id which allows us to post message appropriately 
 *	Uses Slack API:
 * 		  	chat.postMessage
 * 	
 *  
 *	Uses AWS SDK:
 * 			AWS Dynamo DB SDK
 * 
 * 	Author	: Mahesh Beri
 * 	Date 	: 21 Sep -2016
 */
public class ServiceAlertHandler implements RequestHandler<ServiceData, String> {
	HashMap<Integer,String> objIDList= new HashMap<Integer,String> ();
	private  void FindCasesActive(
			DynamoDBMapper mapper, String sTeamID) throws Exception {
		System.out.println("Finding Active Cases ...");
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS("ACTIVE"));
		eav.put(":val2", new AttributeValue().withS(sTeamID));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("CASE_STATE = :val1 and TEAM_ID = :val2")				
				.withExpressionAttributeValues(eav);
		List<Case> scanResult = mapper.scan(Case.class, scanExpression);
		for (Case objCase : scanResult) {
			System.out.println(objCase);
			System.out.println("ID "+objCase.getId());
			objIDList.put(objCase.getId(), "");
			objCase.setStatus("INACTIVE");
			mapper.save(objCase);
		}
	}
	@Override
	public String handleRequest(ServiceData input, Context context) {
		String message_response="_Hello";
		try{
			context.getLogger().log("Input: " + input.getTemperature());
			int Min=3000;
			int Max=8000;
			int ID=0;
			while(true){
				ID=Min + (int)(Math.random() * ((Max - Min) + 1));
				if(objIDList.containsKey(new Integer(ID))){
					continue;
				}else
					break;
			}
			AmazonDynamoDBClient objClient=new AmazonDynamoDBClient(
					new EnvironmentVariableCredentialsProvider());
			DynamoDB dynamoDB = new DynamoDB(objClient);
			//Get all data set status inactive
			DynamoDBMapper mapper = new DynamoDBMapper(objClient);
			FindCasesActive(mapper,input.getTeam_id());
			Item objNewCase =new Item()
					.withPrimaryKey("CASE_ID", ID)
					.withString("USER", "unassigned")
					.withString("DESCRIPTION", "Blah")
					.withString("STAGE", "0")
					.withString("TEAM_ID", input.getTeam_id())
					.withString("CASE_STATE", "ACTIVE");
			Table tableCases = dynamoDB.getTable("CASES");
			tableCases.putItem(objNewCase);         
			
			message_response="Service Alert : Priority - High";
			String sURL="https://slack.com/api/chat.postMessage";
			HttpClient httpClient=HttpClients.createDefault();
			String sTeamId=input.getTeam_id();
			Entity objEntity=EntityHelper.getEntityObject(sTeamId);
			String sAuthToken=objEntity.getAccess_token();
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("token", sAuthToken));
			urlParameters.add(new BasicNameValuePair("channel", objEntity.getChannel_1()));
			urlParameters.add(new BasicNameValuePair("text", message_response));
			String sAttachment1="[\r\n        {\r\n            \"fallback\": \"Required plain-text summary of the attachment.\",\r\n             \"color\": \"#F35A00\",\r\n            \r\n            \"author_name\": \"Excavator System\",\r\n            \"author_link\": \"http://flickr.com/bobby/\",\r\n            \"author_icon\": \"http://flickr.com/icons/bobby.jpg\",\r\n            \"title\": \"Hydraulic Oil Temperature Alert\",\r\n            \"title_link\": \"https://api.slack.com/\",\r\n            \"text\": \"System detected abnormal rise in hydarulic oil temperature.\",\r\n            \"fields\": [                \r\n                {\r\n                    \"title\": \"Maximum\",\r\n                    \"value\": \"28\\u00B0 C\",\r\n                    \"short\": true\r\n                },\r\n                {\r\n                    \"title\": \"Recorded \",\r\n                    \"value\": \"";
			sAttachment1+=input.getTemperature();
			String sAttachment2="\\u00B0 C\",\r\n                    \"short\": true\r\n                }\r\n            ],\r\n            \"image_url\": \"http://servicebot.valueinnovation.co.in/slackhack/tempTrend1.jpg\",\r\n            \"thumb_url\": \"http://noamusic.fr/wp-content/rising-sea-levels-graph-7411.gif\",\r\n            \"footer\": \"MODEL 7830 L | SERIAL Number 8923901-23\",\r\n            \"footer_icon\": \"http://www.freeiconspng.com/uploads/alert-storm-warning-weather-icon--icon-search-engine-0.png\",\r\n            \"ts\":";
			long unixTime = System.currentTimeMillis() / 1000L;
			sAttachment2+=unixTime;
			String sAttachment3="\r\n        }\r\n    ]";
			urlParameters.add(new BasicNameValuePair("attachments",sAttachment1+sAttachment2+sAttachment3));
			HttpPost httpPost= new HttpPost(sURL);
			httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			System.out.println("POST Response Status:: "
					+ httpResponse.getStatusLine().getStatusCode());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpResponse.getEntity().getContent()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			reader.close();
			List<NameValuePair> urlParameters1 = new ArrayList<NameValuePair>();
			urlParameters1.add(new BasicNameValuePair("token", sAuthToken));
			urlParameters1.add(new BasicNameValuePair("channel", objEntity.getChannel_1()));
			try{
				Thread.sleep(4000);
			}catch(Exception e){
			}
			urlParameters1.add(new BasicNameValuePair("text", "I recommend, we order a new coolant pump. Do you wish to see vendor parts?"));
			httpPost.setEntity(new UrlEncodedFormEntity(urlParameters1));
			httpResponse = httpClient.execute(httpPost);
			System.out.println("POST Response Status:: "
					+ httpResponse.getStatusLine().getStatusCode());
			// print result
			System.out.println(response.toString());    		
		}catch(Exception e){
			e.printStackTrace();
		}
		// TODO: implement your handler
		return "Hello " +input.getTemperature();
	}
}
