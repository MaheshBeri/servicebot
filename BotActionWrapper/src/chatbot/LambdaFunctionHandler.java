/*
* Copyright (c) 2016. All rights reserved.
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This code is free software; you can redistribute it and/or modify it
* under the terms of the GNU General Public License version 2 only, as
* published by the Free Software Foundation.
*/
package chatbot;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaAsync;
import com.amazonaws.services.lambda.AWSLambdaAsyncClientBuilder;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaFunctionHandler implements RequestHandler<RequestData, Object> {

    @Override
    public Response handleRequest(RequestData  input, Context context) {
    	Response objResponse= new Response();
    	try {
    	context.getLogger().log("Input: " + input);
        
		ObjectMapper objMapper=new ObjectMapper();
		String jsonInString = objMapper.writeValueAsString(input);
        
        System.out.println("Here 1"+ new Date());

        AWSLambda lambda = AWSLambdaClientBuilder.defaultClient();
        InvokeRequest req = new InvokeRequest()
            .withFunctionName("BotActions")
            .withInvocationType("Event")
            .withPayload(ByteBuffer.wrap(jsonInString.getBytes()));
        System.out.println("Here 2"+ new Date());

        		lambda.invoke(req);

        System.out.println("Here 4"+ new Date());
		objResponse.setAttachments(input.getPayload().getOriginal_message().getAttachments());
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return objResponse;
    }

}
