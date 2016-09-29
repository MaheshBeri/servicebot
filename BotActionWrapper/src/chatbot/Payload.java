package chatbot;

import java.util.ArrayList;

public class Payload
{
	private ArrayList<Action> actions;

  public ArrayList<Action> getActions() { return this.actions; }

  public void setActions(ArrayList<Action> actions) { this.actions = actions; }

  private String callback_id;

  public String getCallback_id() { return this.callback_id; }

  public void setCallback_id(String callback_id) { this.callback_id = callback_id; }
  private Team team;

  public Team getTeam() { return this.team; }

  public void setTeam(Team team) { this.team = team; }

  private Channel channel;

  public Channel getChannel() { return this.channel; }

  public void setChannel(Channel channel) { this.channel = channel; }

  private User user;

  public User getUser() { return this.user; }

  public void setUser(User user) { this.user = user; }

  private String action_ts;

  public String getActionTs() { return this.action_ts; }

  public void setActionTs(String action_ts) { this.action_ts = action_ts; }

  private String message_ts;

  public String getMessage_ts() { return this.message_ts; }

  public void setMessage_ts(String message_ts) { this.message_ts = message_ts; }

  private String attachment_id;

  public String getAttachmentId() { return this.attachment_id; }

  public void setAttachmentId(String attachment_id) { this.attachment_id = attachment_id; }

  private String token;

  public String getToken() { return this.token; }

  public void setToken(String token) { this.token = token; }

   private Message original_message;

  public Message getOriginal_message() { return this.original_message; }

 public void setOriginal_message(Message original_message) { this.original_message = original_message; }

  private String response_url;

  public String getResponse_url() { return this.response_url; }

  public void setResponse_url(String response_url) { this.response_url = response_url; }
}
