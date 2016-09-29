package chatbot;
/*
 *  "confirm": {
                        "title": "Vendor selection",
                        "text": "Do you want to recommend AquaPress?",
                        "ok_text": "Yes",
                        "dismiss_text": "No"
                    }
 */
public class Confirm {
	String text;
	String ok_text;
	String dismiss_text;
	String title;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getOk_text() {
		return ok_text;
	}
	public void setOk_text(String ok_text) {
		this.ok_text = ok_text;
	}
	public String getDismiss_text() {
		return dismiss_text;
	}
	public void setDismiss_text(String dismiss_text) {
		this.dismiss_text = dismiss_text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
