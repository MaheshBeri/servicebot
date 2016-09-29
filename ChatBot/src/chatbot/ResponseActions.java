package chatbot;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"CHANNEL",
"STAGE",
"RESPONSE_TEXT",
"REQUEST_TEXT"
})
public class ResponseActions {

@JsonProperty("CHANNEL")
private String cHANNEL;
@JsonProperty("STAGE")
private String sTAGE;
@JsonProperty("RESPONSE_TEXT")
private String rESPONSETEXT;
@JsonProperty("REQUEST_TEXT")
private String rEQUESTTEXT;
@JsonProperty("RESPONSE_ATTACHMENT")
private String response_attachment;

@JsonProperty("RESPONSE_ATTACHMENT_1")
private String response_attachment_1;

@JsonProperty("RESPONSE_ATTACHMENT_2")
private String response_attachment_2;

@JsonProperty("RESPONSE_ATTACHMENT_3")
private String response_attachment_3;

@JsonProperty("RESPONSE_ATTACHMENT_4")
private String response_attachment_4;

@JsonProperty("RESPONSE_ATTACHMENT_5")
private String response_attachment_5;



public String getResponse_attachment() {
	return response_attachment;
}

public void setResponse_attachment(String response_attachment) {
	this.response_attachment = response_attachment;
}

public String getResponse_attachment_1() {
	return response_attachment_1;
}

public void setResponse_attachment_1(String response_attachment_1) {
	this.response_attachment_1 = response_attachment_1;
}

public String getResponse_attachment_2() {
	return response_attachment_2;
}

public void setResponse_attachment_2(String response_attachment_2) {
	this.response_attachment_2 = response_attachment_2;
}

public String getResponse_attachment_3() {
	return response_attachment_3;
}

public void setResponse_attachment_3(String response_attachment_3) {
	this.response_attachment_3 = response_attachment_3;
}

public String getResponse_attachment_4() {
	return response_attachment_4;
}

public void setResponse_attachment_4(String response_attachment_4) {
	this.response_attachment_4 = response_attachment_4;
}

public String getResponse_attachment_5() {
	return response_attachment_5;
}

public void setResponse_attachment_5(String response_attachment_5) {
	this.response_attachment_5 = response_attachment_5;
}

/**
* 
* @return
* The cHANNEL
*/
@JsonProperty("CHANNEL")
public String getCHANNEL() {
return cHANNEL;
}

/**
* 
* @param cHANNEL
* The CHANNEL
*/
@JsonProperty("CHANNEL")
public void setCHANNEL(String cHANNEL) {
this.cHANNEL = cHANNEL;
}

/**
* 
* @return
* The sTAGE
*/
@JsonProperty("STAGE")
public String getSTAGE() {
return sTAGE;
}

/**
* 
* @param sTAGE
* The STAGE
*/
@JsonProperty("STAGE")
public void setSTAGE(String sTAGE) {
this.sTAGE = sTAGE;
}

/**
* 
* @return
* The rESPONSETEXT
*/
@JsonProperty("RESPONSE_TEXT")
public String getRESPONSETEXT() {
return rESPONSETEXT;
}

/**
* 
* @param rESPONSETEXT
* The RESPONSE_TEXT
*/
@JsonProperty("RESPONSE_TEXT")
public void setRESPONSETEXT(String rESPONSETEXT) {
this.rESPONSETEXT = rESPONSETEXT;
}

/**
* 
* @return
* The rEQUESTTEXT
*/
@JsonProperty("REQUEST_TEXT")
public String getREQUESTTEXT() {
return rEQUESTTEXT;
}

/**
* 
* @param rEQUESTTEXT
* The REQUEST_TEXT
*/
@JsonProperty("REQUEST_TEXT")
public void setREQUESTTEXT(String rEQUESTTEXT) {
this.rEQUESTTEXT = rEQUESTTEXT;
}

}