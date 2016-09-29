package chatbot;

public class Event {

	private String text;
	private String username;
	private String botId;
	private String type;
	private String subtype;
	private String ts;
	private String channel;
	private String eventTs;

	/**
	 * 
	 * @return The text
	 */
	public String getText() {
		return text;
	}

	/**
	 * 
	 * @param text
	 *            The text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 
	 * @return The username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 *            The username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return The botId
	 */
	public String getBotId() {
		return botId;
	}

	/**
	 * 
	 * @param botId
	 *            The bot_id
	 */
	public void setBotId(String botId) {
		this.botId = botId;
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
	 * @return The subtype
	 */
	public String getSubtype() {
		return subtype;
	}

	/**
	 * 
	 * @param subtype
	 *            The subtype
	 */
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	/**
	 * 
	 * @return The ts
	 */
	public String getTs() {
		return ts;
	}

	/**
	 * 
	 * @param ts
	 *            The ts
	 */
	public void setTs(String ts) {
		this.ts = ts;
	}

	/**
	 * 
	 * @return The channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * 
	 * @param channel
	 *            The channel
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * 
	 * @return The eventTs
	 */
	public String getEventTs() {
		return eventTs;
	}

	/**
	 * 
	 * @param eventTs
	 *            The event_ts
	 */
	public void setEventTs(String eventTs) {
		this.eventTs = eventTs;
	}

}