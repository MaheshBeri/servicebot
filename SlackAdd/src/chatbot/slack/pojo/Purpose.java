package chatbot.slack.pojo;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"value",
"creator",
"last_set"
})
public class Purpose {

@JsonProperty("value")
private String value;
@JsonProperty("creator")
private String creator;
@JsonProperty("last_set")
private Integer lastSet;

/**
* 
* @return
* The value
*/
@JsonProperty("value")
public String getValue() {
return value;
}

/**
* 
* @param value
* The value
*/
@JsonProperty("value")
public void setValue(String value) {
this.value = value;
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
* The lastSet
*/
@JsonProperty("last_set")
public Integer getLastSet() {
return lastSet;
}

/**
* 
* @param lastSet
* The last_set
*/
@JsonProperty("last_set")
public void setLastSet(Integer lastSet) {
this.lastSet = lastSet;
}

}

