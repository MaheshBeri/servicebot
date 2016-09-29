package chatbot.slack.pojo;

public class SlackAuth {

	private Boolean ok;
	private String access_token;
	private String scope;
	private String user_id;
	private String team_name;
	private String team_id;
	private IncomingWebhook incoming_webhook;
	private Bot bot;
	public Boolean getOk() {
		return ok;
	}
	public void setOk(Boolean ok) {
		this.ok = ok;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public IncomingWebhook getIncoming_webhook() {
		return incoming_webhook;
	}
	public void setIncoming_webhook(IncomingWebhook incoming_webhook) {
		this.incoming_webhook = incoming_webhook;
	}
	public Bot getBot() {
		return bot;
	}
	public void setBot(Bot bot) {
		this.bot = bot;
	}

	
}