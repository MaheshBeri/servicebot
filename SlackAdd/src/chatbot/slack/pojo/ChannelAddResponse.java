package chatbot.slack.pojo;

public class ChannelAddResponse {
	String ok;
	Channel channel;
	public String getOk() {
		return ok;
	}
	public void setOk(String ok) {
		this.ok = ok;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}
