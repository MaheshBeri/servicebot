package chatbot.slack.pojo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
"id",
"name",
"created",
"creator",
"is_archived",
"is_member",
"num_members",
"topic",
"purpose"
})
public class Channel {

@JsonProperty("id")
private String id;
@JsonProperty("name")
private String name;
@JsonProperty("created")
private Integer created;
@JsonProperty("creator")
private String creator;
@JsonProperty("is_archived")
private Boolean isArchived;
@JsonProperty("is_member")
private Boolean isMember;
@JsonProperty("num_members")
private Integer numMembers;
@JsonProperty("topic")
private Topic topic;
@JsonProperty("purpose")
private Purpose purpose;

/**
* 
* @return
* The id
*/
@JsonProperty("id")
public String getId() {
return id;
}

/**
* 
* @param id
* The id
*/
@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

/**
* 
* @return
* The name
*/
@JsonProperty("name")
public String getName() {
return name;
}

/**
* 
* @param name
* The name
*/
@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

/**
* 
* @return
* The created
*/
@JsonProperty("created")
public Integer getCreated() {
return created;
}

/**
* 
* @param created
* The created
*/
@JsonProperty("created")
public void setCreated(Integer created) {
this.created = created;
}

/**
* 
* @return
* The creator
*/
@JsonProperty("creator")
public String getCreator() {
return creator;
}

/**
* 
* @param creator
* The creator
*/
@JsonProperty("creator")
public void setCreator(String creator) {
this.creator = creator;
}

/**
* 
* @return
* The isArchived
*/
@JsonProperty("is_archived")
public Boolean getIsArchived() {
return isArchived;
}

/**
* 
* @param isArchived
* The is_archived
*/
@JsonProperty("is_archived")
public void setIsArchived(Boolean isArchived) {
this.isArchived = isArchived;
}

/**
* 
* @return
* The isMember
*/
@JsonProperty("is_member")
public Boolean getIsMember() {
return isMember;
}

/**
* 
* @param isMember
* The is_member
*/
@JsonProperty("is_member")
public void setIsMember(Boolean isMember) {
this.isMember = isMember;
}

/**
* 
* @return
* The numMembers
*/
@JsonProperty("num_members")
public Integer getNumMembers() {
return numMembers;
}

/**
* 
* @param numMembers
* The num_members
*/
@JsonProperty("num_members")
public void setNumMembers(Integer numMembers) {
this.numMembers = numMembers;
}

/**
* 
* @return
* The topic
*/
@JsonProperty("topic")
public Topic getTopic() {
return topic;
}

/**
* 
* @param topic
* The topic
*/
@JsonProperty("topic")
public void setTopic(Topic topic) {
this.topic = topic;
}

/**
* 
* @return
* The purpose
*/
@JsonProperty("purpose")
public Purpose getPurpose() {
return purpose;
}

/**
* 
* @param purpose
* The purpose
*/
@JsonProperty("purpose")
public void setPurpose(Purpose purpose) {
this.purpose = purpose;
}

}