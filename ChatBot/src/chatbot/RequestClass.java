
/*
{
    "token": "Jhj5dZrVaK7ZwHHjRyZWjbDl",
    "challenge": "3eZbrw1aBm2rZgRNFdxV2595E9CY3gmdALWMmHkvFXO7tYXAYM8P",
    "type": "url_verification"
}
 
 */
/*
 * 
  { "token": "d0SsH23j6cxaTQy2pkB0YC8N",
    "team_id": "T25JRRJRX",
    "api_app_id": "A280ERLES",
    "event": {
        "type": "message",
        "user": "U25JETJ3S",
        "text": "Yes",
        "ts": "1473776045.000002",
        "channel": "C27N3EP2R",
        "event_ts": "1473776045.000002"
    },
    "type": "event_callback",
    "authed_users": [
        "U25JETJ3S"
    ]   
  } 
 * 
 */

/*
 * Method request body before transformations:
{
    "token": "d0SsH23j6cxaTQy2pkB0YC8N",
    "team_id": "T25JRRJRX",
    "api_app_id": "A280ERLES",
    "event": {
        "text": "Your response is recorded. Next request will be sent to Buyer for commercials. You will be notified once order is finalized.55",
        "username": "ServiceApp",
        "bot_id": "B282P136U",
        "type": "message",
        "subtype": "bot_message",
        "ts": "1473776151.000003",
        "channel": "C27N3EP2R",
        "event_ts": "1473776151.000003"
    },
    "type": "event_callback",
    "authed_users": [
        "U25JETJ3S"
    ]
}
 */

package chatbot;

import java.util.ArrayList;
import java.util.List;

public class RequestClass {
	private String challenge;
	private String token;
	private String team_id;
	private String api_app_id;
	private Event event;
	private String type;


	private List<String> authedUsers = new ArrayList<String>();

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	/**
	 * 
	 * @return The token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 
	 * @param token
	 *            The token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 
	 * @return The teamId
	 */
	public String getTeam_id() {
		return team_id;
	}

	/**
	 * 
	 * @param teamId
	 *            The team_id
	 */
	public void setTeam_id(String teamId) {
		this.team_id = teamId;
	}

	/**
	 * 
	 * @return The apiAppId
	 */
	public String getApi_app_id() {
		return api_app_id;
	}

	/**
	 * 
	 * @param apiAppId
	 *            The api_app_id
	 */
	public void setApi_app_id(String apiAppId) {
		this.api_app_id = apiAppId;
	}

	/**
	 * 
	 * @return The event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * 
	 * @param event
	 *            The event
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * 
	 * @return The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            The type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The authedUsers
	 */
	public List<String> getAuthedUsers() {
		return authedUsers;
	}

	/**
	 * 
	 * @param authedUsers
	 *            The authed_users
	 */
	public void setAuthedUsers(List<String> authedUsers) {
		this.authedUsers = authedUsers;
	}

}
