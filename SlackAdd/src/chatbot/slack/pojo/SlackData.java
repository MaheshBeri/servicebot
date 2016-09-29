package chatbot.slack.pojo;

public class SlackData {
String code ;
String state;
Env env;
public Env getEnv() {
	return env;
}
public void setEnv(Env env) {
	this.env = env;
}

public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
}
