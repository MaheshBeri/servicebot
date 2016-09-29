package chatbot;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	private String text;
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("attachments")
	private List<Attachment> attachments = new ArrayList<Attachment>();

	/**
	* 
	* @return
	* The attachments
	*/
	@JsonProperty("attachments")
	public List<Attachment> getAttachments() {
	return attachments;
	}

	/**
	* 
	* @param attachments
	* The attachments
	*/
	@JsonProperty("attachments")
	public void setAttachments(List<Attachment> attachments) {
	this.attachments = attachments;
	}
	

	
}
