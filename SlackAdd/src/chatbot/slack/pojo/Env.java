package chatbot.slack.pojo;

public class Env {


	private String clientId;
	private String slackClientSecret;
	private String slackToken;
	private String slackRedirectUrl;
	private String slackHomePageUrl;


	public String getSlackClientSecret() {
		return slackClientSecret;
	}


	public void setSlackClientSecret(String slackClientSecret) {
		this.slackClientSecret = slackClientSecret;
	}


	public String getSlackToken() {
		return slackToken;
	}


	public void setSlackToken(String slackToken) {
		this.slackToken = slackToken;
	}


	public String getSlackRedirectUrl() {
		return slackRedirectUrl;
	}


	public void setSlackRedirectUrl(String slackRedirectUrl) {
		this.slackRedirectUrl = slackRedirectUrl;
	}


	public String getSlackHomePageUrl() {
		return slackHomePageUrl;
	}


	public void setSlackHomePageUrl(String slackHomePageUrl) {
		this.slackHomePageUrl = slackHomePageUrl;
	}


	/**
	 * 
	 * @return
	 * The clientId
	 */

	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
