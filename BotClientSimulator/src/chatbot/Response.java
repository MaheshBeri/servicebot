package chatbot;

import java.util.ArrayList;
import java.util.List;

public class Response {
	List<Team> teams=null;
	String message=null;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
