/*
 * static Map<String,Map<String,ArrayList<String>>> objConverstationData= new HashMap<String,Map<String,ArrayList<String>>>();
	static{

		{
			String sStage0="0";
			String sResponse1="Yes";
			String sResponse2="No";
			String sResponse3="Other";
			Map<String,ArrayList<String>> objConverstationResponse0= new HashMap<String,ArrayList<String>>();		

			ArrayList<String> objConverstationList_Yes = new ArrayList<String>();
			objConverstationList_Yes.add("1");
			objConverstationList_Yes.add("Thank you for your response. The incident has been assigned to you. Based on the trend, there is need for Part replacement. Based on inventory levels, recommendation is to initiate Part order. Approved Vendors are: v1 and v2. Who is your preferred vendor?");
			objConverstationList_Yes.add("C27N3EP2R");
			objConverstationResponse0.put(sResponse1, objConverstationList_Yes);

			ArrayList<String> objConverstationList_No = new ArrayList<String>();
			objConverstationList_No.add("0");
			objConverstationList_No.add("Sure. I got it. I will check with other Site Engineers who might be available. Thank you.");
			objConverstationList_No.add("C27N3EP2R");
			objConverstationResponse0.put(sResponse2, objConverstationList_No);

			ArrayList<String> objConverstationList_Other = new ArrayList<String>();
			objConverstationList_Other.add("0");
			objConverstationList_Other.add("Sorry. I did not get you. Please say 'Yes' to start working on incident and 'No' to decline.");
			objConverstationList_Other.add("C27N3EP2R");
			objConverstationResponse0.put(sResponse3, objConverstationList_Other);			
			objConverstationData.put(sStage0, objConverstationResponse0);
		}
		{
			String sStage1="1";
			Map<String,ArrayList<String>> objConverstationResponse1= new HashMap<String,ArrayList<String>>();		
			String sResponse_v1="1";
			String sResponse_v2="2";
			String sResponse_vO="Other";

			ArrayList<String> objConverstationList_v1 = new ArrayList<String>();
			objConverstationList_v1.add("2");
			objConverstationList_v1.add("Alright, Vendor 1  is  your preferred vendor. Next step is commercial evaluation. You would be notified once vendor selection is finalized. ");
			objConverstationList_v1.add("C27N3EP2R");
			objConverstationList_v1.add("Incident XXXY . Part replacement is recommened. Site engineer has reviewed the case and suggested approved Vendor 1  as preferred. Do you want to request quotes from vendors?");
			objConverstationList_v1.add("C294RB21W");
			objConverstationResponse1.put(sResponse_v1, objConverstationList_v1);


			ArrayList<String> objConverstationList_v2 = new ArrayList<String>();
			objConverstationList_v2.add("2");
			objConverstationList_v2.add("Alright, Vendor 2  is  your preferred vendor. Next step is commercial evaluation. You would be notified once vendor selection is finalized. ");
			objConverstationList_v2.add("C27N3EP2R");
			objConverstationList_v1.add("Incident XXXY . Part replacement is recommened. Site engineer has reviewed the case and suggested approved Vendor 2  as preferred. Do you want to request quotes from vendors?");
			objConverstationList_v1.add("C294RB21W");
			objConverstationResponse1.put(sResponse_v2, objConverstationList_v2);


			ArrayList<String> objConverstationList_v3 = new ArrayList<String>();
			objConverstationList_v3.add("1");
			objConverstationList_v3.add("Sorry. I did not get you. Approved Vendors are: v1 and v2. Select 1 or 2 as your preferred choice.");
			objConverstationList_v3.add("C27N3EP2R");
			objConverstationResponse1.put(sResponse_vO, objConverstationList_v2);

			objConverstationData.put(sStage1, objConverstationResponse1);
		}
		//Here are the approved vendors V1, V2 and V3? Please confirm which vendor you want to recommend.
	}
 */

package chatbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResponseClass {
    
    String challenge="XYZ";
    
    public ResponseClass(){
    	
    }
    public ResponseClass(String challenge) {
    	this.challenge=challenge;
    }


	public String getChallenge() {
		return challenge;
	}


	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

}