package chatbot;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"attachments"
})
public class Message {
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