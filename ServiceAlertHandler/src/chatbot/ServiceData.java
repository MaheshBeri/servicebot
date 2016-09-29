package chatbot;

public class ServiceData {
	int temperature;
	String team_id;
	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	 public ServiceData() {
     }
}

/*
 
{
    "attachments": [
        {
            "fallback": "Required plain-text summary of the attachment.",
             "color": "#F35A00",
            
            "author_name": "Excavator System",
            "author_link": "http://flickr.com/bobby/",
            "author_icon": "http://flickr.com/icons/bobby.jpg",
            "title": "Hydraulic Oil Temperature Alert",
            "title_link": "https://api.slack.com/",
            "text": "System detected abnormal rise in hydarulic oil temperature.",
            "fields": [                
                {
                    "title": "Maximum",
                    "value": "28\u00B0 C",
                    "short": true
                },
                {
                    "title": "Recorded ",
                    "value": "33\u00B0 C",
                    "short": true
                }
            ],
            "image_url": "http://marketminder.com/img/Rising_Rates_Exhibit1.jpg",
            "thumb_url": "http://noamusic.fr/wp-content/rising-sea-levels-graph-7411.gif",
            "footer": "MODEL 7830 L | SERIAL Number 8923901-23",
            "footer_icon": "http://www.freeiconspng.com/uploads/alert-storm-warning-weather-icon--icon-search-engine-0.png",
            "ts": 1474045595
        }
    ]
}
 
 
 
 {
    "attachments": [
    		{
            "fallback": "New ticket from Andrea Lee - Ticket #1943: Can't rest my password - https://groove.hq/path/to/ticket/1943",
            "pretext": "Suggested Action:",
            "title": "Case #1943: Pump replacement order",
            "title_link": "https://groove.hq/path/to/ticket/1943",
            "text": "New case has been registered. \n\n Historical trend in oil temperature rise indicates failure of auxillary coolant pump. I have checked our inventory, we do not have spare for the pump model J0712. ",
			 "fields": [
                {
                    "title": "Resolution",
                    "value": "Place order for replacement coolant pump.",
                    "short": false
                },
    			{
                    "title": "Next Step",
                    "value": "Do you want to start working on the case ?",
                    "short": false
                }
							 
			 ],
            "color": "#7CD197"
        }
    ]
}
 */
		