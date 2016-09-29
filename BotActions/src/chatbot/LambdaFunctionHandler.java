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
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
public class LambdaFunctionHandler implements RequestHandler<RequestData, Object> {
	@Override
	public Response handleRequest(RequestData  input, Context context) {
		Response objResponse= new Response();
		objResponse.setAttachments(input.getPayload().getOriginal_message().getAttachments());
		;
		try{
			ResponseAttachmentHelper objResponseAttachmentHelper = new ResponseAttachmentHelper();
			String call_back=input.getPayload().getCallback_id();
			context.getLogger().log(" Callback ----- " + call_back);
			objResponseAttachmentHelper.init();
			context.getLogger().log(" Response init done ----- " );
			CaseHelper objCaseHelper = new CaseHelper();
			context.getLogger().log(" Case  init before ----- " );
			objCaseHelper.init(input.getPayload().getTeam().getId());
			context.getLogger().log(" Case  init after ----- " );
			String actionCommand=input.getPayload().getActions().get(0).getName();
			String actionValue=input.getPayload().getActions().get(0).getValue();
			objCaseHelper.updateCaseRecomendation(call_back,actionValue);
			objCaseHelper.updateCaseTS(call_back,input.getPayload().getMessage_ts());
			//Message objOriginalMessage=input.getPayload().getOriginal_message();
			context.getLogger().log(" Action " + actionCommand);
			context.getLogger().log(" Action val " + actionValue);
			//context.getLogger().log("Original MESSAGE"+objOriginalMessage);
			context.getLogger().log("Attachment Count"+input.getPayload().getOriginal_message().getAttachments().size());
			List<Attachment> objOriginalAttachmentList=input.getPayload().getOriginal_message().getAttachments();
			String sColourMain="#36a64f";
			String sColourButton="#3AA3E3";
			String sColourNote="#FFAF00";
			String sColourMainNoHash="36a64f";
			String sColourButtonNoHash="3AA3E3";
			String sColourNoteNoHash="FFAF00";
			ArrayList<Attachment> objPreparedAttachmentList= new ArrayList<Attachment>();
			System.out.println("BEFORE"+objPreparedAttachmentList.size());
			for (int i=0; i<objOriginalAttachmentList.size();i++) {
				Attachment objOriginalAttachment =objOriginalAttachmentList.get(i);
				String sColour=objOriginalAttachment.getColor();			
				if(sColour.equals(sColourMain)|| sColour.equals(sColourNote)||
						sColour.equals(sColourMainNoHash)|| sColour.equals(sColourNoteNoHash)
						)
					objPreparedAttachmentList.add(objOriginalAttachment);
				if(sColour.equals(sColourButton)||sColour.equals(sColourButtonNoHash)){
					boolean replace=false;
					if(actionValue.equals("1") && i==1){
						replace=true;
					}
					if(actionValue.equals("2") && i>1){
						replace=true;
					}
					String sDeptText="";
					//actionValue is 1 or 2
					if(call_back.equals("vendor_engg_rec")){
						sDeptText=":white_check_mark: Recommended by Engineering";
					}
					if(call_back.equals("vendor_buyer_rec")){
						sDeptText=":white_check_mark: Recommended by Purchasing";						
					}
					if(call_back.equals("vendor_opsmgr_rec")){
						sDeptText=":white_check_mark: Approved by Operations Manager";
					}
					if(replace){
						Attachment objAttachmentNew= new Attachment();
						objAttachmentNew.setTitle("Notes");
						objAttachmentNew.setColor("#FFAF00");
						objAttachmentNew.setText(sDeptText);
						List<String> lstMk=new ArrayList<String>();
						lstMk.add("text");
						lstMk.add("pretext");
						objAttachmentNew.setMrkdwnIn(lstMk);
						objPreparedAttachmentList.add(objAttachmentNew);
					}
				}
				System.out.println("AFTER"+i+" "+objPreparedAttachmentList.size());
			}
			String sTeamId=input.getPayload().getTeam().getId();
			Entity objEntity=EntityHelper.getEntityObject(sTeamId);
			String sAuthToken=objEntity.getAccess_token();
			System.out.println("AFTER"+objPreparedAttachmentList.size());
			String sResponseAttachment="";
			ObjectMapper objMapper=new ObjectMapper();
			sResponseAttachment = objMapper.writeValueAsString(objPreparedAttachmentList);
			exec(sTeamId,sAuthToken,input.getPayload().getChannel().getId(),input.getPayload().getMessage_ts(), objCaseHelper,sResponseAttachment);
			//update CHAT
			objResponse.setAttachments(objPreparedAttachmentList);
			if(call_back.equals("vendor_buyer_rec")){
				objCaseHelper.updateCaseState("2");
			}
			if(call_back.equals("vendor_opsmgr_rec")){
				objCaseHelper.updateCaseState("3");
			}
			if(call_back.equals("vendor_opsmgr_rec")){
				String sChannelToPost="";
				sChannelToPost=objEntity.getChannel_2();
				String tsValue=objCaseHelper.getCase().getTs_stage_1().substring(1);
				exec(sTeamId,sAuthToken,sChannelToPost,tsValue, objCaseHelper,objResponseAttachmentHelper);
				sChannelToPost=objEntity.getChannel_1();
				tsValue=objCaseHelper.getCase().getTs_stage_0().substring(1);
				exec(sTeamId,sAuthToken,sChannelToPost,tsValue, objCaseHelper,objResponseAttachmentHelper);
			}
			System.out.println("###### call_back "+call_back);
			if(call_back.equals("vendor_engg_rec")||call_back.equals("vendor_buyer_rec")){
				HttpClient httpClient=HttpClients.createDefault();
				String sURL="https://slack.com/api/chat.postMessage";
				// System.out.println("Response URL "+sUrl);
				List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
				urlParameters.add(new BasicNameValuePair("token",sAuthToken));
				String sChannelToPost="";
				if(call_back.equals("vendor_engg_rec"))
					sChannelToPost=objEntity.getChannel_2();
				if(call_back.equals("vendor_buyer_rec"))
					sChannelToPost=objEntity.getChannel_3();
				urlParameters.add(new BasicNameValuePair("channel", sChannelToPost));
				sResponseAttachment=objResponseAttachmentHelper.getPreparedResponseAttachmentString(objCaseHelper.getCase(),false);
				urlParameters.add(new BasicNameValuePair("attachments",sResponseAttachment));
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
				reader.close();
				System.out.println("POST Response Status:: "
						+ response.toString());
			}
			context.getLogger().log(" Message Posted via Thread .. Now Sending response ..");
		}catch(Exception e){
			e.printStackTrace();       	
		}
		return objResponse;
	}
	public void exec(String sTeamId,String sAuthToken,String sChannel,String sTimeStamp, CaseHelper objCaseHelper,String sResponseAttachment) throws Exception {
		HttpClient httpClient=HttpClients.createDefault();
		String sURL="https://slack.com/api/chat.update";
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		System.out.println("Got Team Id :: "+ sTeamId);
		urlParameters.add(new BasicNameValuePair("token",sAuthToken));
		urlParameters.add(new BasicNameValuePair("attachments",sResponseAttachment));
		urlParameters.add(new BasicNameValuePair("channel", sChannel));
		urlParameters.add(new BasicNameValuePair("ts",""+sTimeStamp));
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
		reader.close();
		System.out.println("UPDATE POST Response Status:: "
				+ response.toString());
	}
	public void exec(String sTeamId,String sAuthToken,String sChannel,String sTimeStamp, CaseHelper objCaseHelper,ResponseAttachmentHelper objResponseAttachmentHelper) throws Exception {
		String sResponseAttachment=objResponseAttachmentHelper.getPreparedResponseAttachmentString(objCaseHelper.getCase(),true);
		exec(sTeamId,sAuthToken,sChannel,sTimeStamp, objCaseHelper,sResponseAttachment);
	}   
}
