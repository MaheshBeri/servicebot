package chatbot;


public class Action
{
	private String text;
	private String style;
	private String type;
	private Confirm confirm;
	
  public Confirm getConfirm() {
		return confirm;
	}

	public void setConfirm(Confirm confirm) {
		this.confirm = confirm;
	}

public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

private String name;

  public String getName() { return this.name; }

  public void setName(String name) { this.name = name; }

  private String value;

  public String getValue() { return this.value; }

  public void setValue(String value) { this.value = value; }
}
