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
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import chatbot.slack.pojo.Channel;
import chatbot.slack.pojo.ChannelAddResponse;
import chatbot.slack.pojo.Env;
import chatbot.slack.pojo.SlackAuth;
import chatbot.slack.pojo.SlackChannels;
import chatbot.slack.pojo.SlackData;

/*
* Service Bot
*	Lambda Function: SlackAdd
*	Purpose:1) Implement OAuth for Slack based on Add to Slack API
*  		 	2) Setup ServiceBot for your Slack Team
*	Uses Slack API:
* 		  	oauth.access
* 			channels.list
* 			channels.create
*  
*	Uses AWS SDK:
* 			AWS Dynamo DB SDK
* 
* 	Author	: Mahesh Beri
* 	Date 	: 21 Sep -2016
*/

public class LambdaFunctionHandler implements RequestHandler<SlackData, String> {
	@Override
	public String handleRequest(SlackData input, Context context) {
		String sResponse=", Welcome to Service Bot";
		try{
			//context.getLogger().log(input);
			context.getLogger().log("Input Code: " + input.getCode());
			context.getLogger().log("Input State: " + input.getState());
			Env objEnv=input.getEnv();
			String sURL="https://slack.com/api/oauth.access";	
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("client_id", objEnv.getClientId()));
			urlParameters.add(new BasicNameValuePair("client_secret", objEnv.getSlackClientSecret()));
			urlParameters.add(new BasicNameValuePair("code", input.getCode()));
			urlParameters.add(new BasicNameValuePair("redirect_uri", objEnv.getSlackRedirectUrl()));
			String jsonInString =invokeSlackAPI(sURL,urlParameters,context);
			ObjectMapper mapper = new ObjectMapper();					
			//JSON from String to Object
			SlackAuth objSlackAuth= mapper.readValue(jsonInString, SlackAuth.class);		
			sResponse=objSlackAuth.getTeam_name()+sResponse;
			sURL="https://slack.com/api/channels.list";
			urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("token", objSlackAuth.getAccess_token()));
			jsonInString =invokeSlackAPI(sURL,urlParameters,context);		
			//JSON from String to Object
			SlackChannels objSlackChannels= mapper.readValue(jsonInString, SlackChannels.class);
			System.out.println("Channels "+objSlackChannels.getChannels());
			String sSiteChannel_ID=null;
			String sBuyersChannel_ID=null;
			String sManagers_ID=null;
			for( Channel objChannel: objSlackChannels.getChannels()){
				if(objChannel.getName().equals("site-engineers")){
					sSiteChannel_ID=objChannel.getId();
				}
				if(objChannel.getName().equals("buyers")){
					sBuyersChannel_ID=objChannel.getId();
				}
				if(objChannel.getName().equals("operations-managers")){
					sManagers_ID=objChannel.getId();
				}
			}
			sURL="https://slack.com/api/channels.create";
			if(sSiteChannel_ID==null){
				urlParameters = new ArrayList<NameValuePair>();			
				urlParameters.add(new BasicNameValuePair("token", objSlackAuth.getAccess_token()));
				urlParameters.add(new BasicNameValuePair("name", "site-engineers"));
				jsonInString =invokeSlackAPI(sURL,urlParameters,context);					
				ChannelAddResponse objChannelAddResponse= mapper.readValue(jsonInString, ChannelAddResponse.class);
				sSiteChannel_ID=(objChannelAddResponse.getChannel()).getId();
			}
			if(sBuyersChannel_ID==null){
				urlParameters = new ArrayList<NameValuePair>();			
				urlParameters.add(new BasicNameValuePair("token", objSlackAuth.getAccess_token()));
				urlParameters.add(new BasicNameValuePair("name", "buyers"));
				jsonInString =invokeSlackAPI(sURL,urlParameters,context);					
				ChannelAddResponse objChannelAddResponse= mapper.readValue(jsonInString, ChannelAddResponse.class);
				sBuyersChannel_ID=(objChannelAddResponse.getChannel()).getId();
			}
			if(sManagers_ID==null){
				urlParameters = new ArrayList<NameValuePair>();			
				urlParameters.add(new BasicNameValuePair("token", objSlackAuth.getAccess_token()));
				urlParameters.add(new BasicNameValuePair("name", "operations-managers"));
				jsonInString =invokeSlackAPI(sURL,urlParameters,context);					
				ChannelAddResponse objChannelAddResponse= mapper.readValue(jsonInString, ChannelAddResponse.class);
				sManagers_ID=(objChannelAddResponse.getChannel()).getId();
			}			
			AmazonDynamoDBClient objClient=new AmazonDynamoDBClient(
					new EnvironmentVariableCredentialsProvider());
			DynamoDB dynamoDB = new DynamoDB(objClient);
			//Get all data set status inactive
			Item objNewTeam =new Item()
					.withPrimaryKey("TEAM_ID", objSlackAuth.getTeam_id())
					.withString("TEAM_NAME", objSlackAuth.getTeam_name())
					.withString("CHANNEL_1", sSiteChannel_ID)
					.withString("CHANNEL_2", sBuyersChannel_ID)
					.withString("CHANNEL_3", sManagers_ID)
					.withString("ACCESS_TOKEN", objSlackAuth.getAccess_token());
			Table tableCases = dynamoDB.getTable("ENTITY");
			tableCases.putItem(objNewTeam);    			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sResponse;
	}
	
	String  invokeSlackAPI(String sURL, List<NameValuePair> urlParameters,Context context) throws Exception{
		HttpClient httpClient=HttpClients.createDefault();
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
			//context.getLogger().log(inputLine);
		}
		return response.toString();		
	}
}
