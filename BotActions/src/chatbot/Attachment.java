package chatbot;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Attachment {

@JsonProperty("fallback")
private String fallback;
@JsonProperty("color")
private String color;
@JsonProperty("author_name")
private String authorName;
@JsonProperty("author_link")
private String authorLink;
@JsonProperty("author_icon")
private String authorIcon;
@JsonProperty("pretext")
private String pretext;
@JsonProperty("title")
private String title;
@JsonProperty("title_link")
private String titleLink;
@JsonProperty("text")
private String text;
@JsonProperty("image_url")
private String image_url;

public String getCallback_id() {
	return callback_id;
}

public void setCallback_id(String callback_id) {
	this.callback_id = callback_id;
}

@JsonProperty("callback_id")
private String callback_id;

@JsonProperty("actions")
private List<Action> actions = new ArrayList<Action>();

public List<Action> getActions() {
	return actions;
}

public void setActions(List<Action> actions) {
	this.actions = actions;
}

@JsonProperty("fields")
private List<Field> fields = new ArrayList<Field>();
@JsonProperty("thumb_url")
private String thumbUrl;
@JsonProperty("footer")
private String footer;
@JsonProperty("footer_icon")
private String footerIcon;
@JsonProperty("ts")
private Integer ts;
@JsonProperty("mrkdwn_in")
private List<String> mrkdwnIn = new ArrayList<String>();

/**
* 
* @return
* The fallback
*/
@JsonProperty("fallback")
public String getFallback() {
return fallback;
}

/**
* 
* @param fallback
* The fallback
*/
@JsonProperty("fallback")
public void setFallback(String fallback) {
this.fallback = fallback;
}

/**
* 
* @return
* The color
*/
@JsonProperty("color")
public String getColor() {
return color;
}

/**
* 
* @param color
* The color
*/
@JsonProperty("color")
public void setColor(String color) {
this.color = color;
}

/**
* 
* @return
* The authorName
*/
@JsonProperty("author_name")
public String getAuthorName() {
return authorName;
}

/**
* 
* @param authorName
* The author_name
*/
@JsonProperty("author_name")
public void setAuthorName(String authorName) {
this.authorName = authorName;
}

/**
* 
* @return
* The authorLink
*/
@JsonProperty("author_link")
public String getAuthorLink() {
return authorLink;
}

/**
* 
* @param authorLink
* The author_link
*/
@JsonProperty("author_link")
public void setAuthorLink(String authorLink) {
this.authorLink = authorLink;
}

/**
* 
* @return
* The authorIcon
*/
@JsonProperty("author_icon")
public String getAuthorIcon() {
return authorIcon;
}

/**
* 
* @param authorIcon
* The author_icon
*/
@JsonProperty("author_icon")
public void setAuthorIcon(String authorIcon) {
this.authorIcon = authorIcon;
}

/**
* 
* @return
* The pretext
*/
@JsonProperty("pretext")
public String getPretext() {
return pretext;
}

/**
* 
* @param pretext
* The pretext
*/
@JsonProperty("pretext")
public void setPretext(String pretext) {
this.pretext = pretext;
}

/**
* 
* @return
* The title
*/
@JsonProperty("title")
public String getTitle() {
return title;
}

/**
* 
* @param title
* The title
*/
@JsonProperty("title")
public void setTitle(String title) {
this.title = title;
}

/**
* 
* @return
* The titleLink
*/
@JsonProperty("title_link")
public String getTitleLink() {
return titleLink;
}

/**
* 
* @param titleLink
* The title_link
*/
@JsonProperty("title_link")
public void setTitleLink(String titleLink) {
this.titleLink = titleLink;
}

/**
* 
* @return
* The text
*/
@JsonProperty("text")
public String getText() {
return text;
}

/**
* 
* @param text
* The text
*/
@JsonProperty("text")
public void setText(String text) {
this.text = text;
}

/**
* 
* @return
* The fields
*/
@JsonProperty("fields")
public List<Field> getFields() {
return fields;
}

/**
* 
* @param fields
* The fields
*/
@JsonProperty("fields")
public void setFields(List<Field> fields) {
this.fields = fields;
}

/**
* 
* @return
* The imageUrl
*/
@JsonProperty("image_url")
public String getImage_url() {
return image_url;
}

/**
* 
* @param imageUrl
* The image_url
*/
@JsonProperty("image_url")
public void setImage_url(String imageUrl) {
this.image_url = imageUrl;
}

/**
* 
* @return
* The thumbUrl
*/
@JsonProperty("thumb_url")
public String getThumbUrl() {
return thumbUrl;
}

/**
* 
* @param thumbUrl
* The thumb_url
*/
@JsonProperty("thumb_url")
public void setThumbUrl(String thumbUrl) {
this.thumbUrl = thumbUrl;
}

/**
* 
* @return
* The footer
*/
@JsonProperty("footer")
public String getFooter() {
return footer;
}

/**
* 
* @param footer
* The footer
*/
@JsonProperty("footer")
public void setFooter(String footer) {
this.footer = footer;
}

/**
* 
* @return
* The footerIcon
*/
@JsonProperty("footer_icon")
public String getFooterIcon() {
return footerIcon;
}

/**
* 
* @param footerIcon
* The footer_icon
*/
@JsonProperty("footer_icon")
public void setFooterIcon(String footerIcon) {
this.footerIcon = footerIcon;
}

/**
* 
* @return
* The ts
*/
@JsonProperty("ts")
public Integer getTs() {
return ts;
}

/**
* 
* @param ts
* The ts
*/
@JsonProperty("ts")
public void setTs(Integer ts) {
this.ts = ts;
}

/**
* 
* @return
* The mrkdwnIn
*/
@JsonProperty("mrkdwn_in")
public List<String> getMrkdwnIn() {
return mrkdwnIn;
}

/**
* 
* @param mrkdwnIn
* The mrkdwn_in
*/
@JsonProperty("mrkdwn_in")
public void setMrkdwnIn(List<String> mrkdwnIn) {
this.mrkdwnIn = mrkdwnIn;
}

}