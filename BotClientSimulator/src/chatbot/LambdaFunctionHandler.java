/*
* Copyright (c) 2016. All rights reserved.
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This code is free software; you can redistribute it and/or modify it
* under the terms of the GNU General Public License version 2 only, as
* published by the Free Software Foundation.
*/

package chatbot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.iot.client.sample.pubSub.PublishSubscribeSample;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Request, Object> {

	@Override
	public Response handleRequest(Request input, Context context) {
		Response objResponse= new Response();
		try{

			context.getLogger().log("Input: " + input.getType());
			if(input.getType()==0){
				String[] args=new String[0];
				PublishSubscribeSample objPublishSubscribeSample=new PublishSubscribeSample();
				context.getLogger().log("Input: " + input.getTemperature());
				objPublishSubscribeSample.setTemperature(input.getTemperature());
				objPublishSubscribeSample.setTeam_id(input.getTeam_id());
				objPublishSubscribeSample.main(args);
				objResponse.setMessage("Message Posted");
			}
			if(input.getType()==1){

				AmazonDynamoDBClient objClient=new AmazonDynamoDBClient(
						new EnvironmentVariableCredentialsProvider());		
				ScanResult result = null;
				ScanRequest req = new ScanRequest();
				req.setTableName("ENTITY");
				result = objClient.scan(req);
				List<Team> teams= new ArrayList<Team>();
				objResponse.setTeams(teams);
				for (Map<String, AttributeValue> item : result.getItems()){

					Team obj1= new Team();
					obj1.setTeam_id(item.get("TEAM_ID").getS());
					obj1.setTeam_name(item.get("TEAM_NAME").getS());
					teams.add(obj1);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			objResponse.setMessage(e.getMessage());

		}
		return objResponse;
	}

}
