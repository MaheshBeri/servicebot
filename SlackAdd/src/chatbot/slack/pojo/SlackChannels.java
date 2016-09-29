package chatbot.slack.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"ok",
"channels"
})
public class SlackChannels {

@JsonProperty("ok")
private Boolean ok;
@JsonProperty("channels")
private List<Channel> channels = new ArrayList<Channel>();

/**
* 
* @return
* The ok
*/
@JsonProperty("ok")
public Boolean getOk() {
return ok;
}

/**
* 
* @param ok
* The ok
*/
@JsonProperty("ok")
public void setOk(Boolean ok) {
this.ok = ok;
}

/**
* 
* @return
* The channels
*/
@JsonProperty("channels")
public List<Channel> getChannels() {
return channels;
}

/**
* 
* @param channels
* The channels
*/
@JsonProperty("channels")
public void setChannels(List<Channel> channels) {
this.channels = channels;
}

}
