package com.psqa.framework;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

/**
 * Created by david_him on 12/16/2016.
 */
public class JSONFactory {

    public JSONFactory() {

    }

    public String parseJSON(String json) {
        String jsonString = null;

        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String trackingID = (String) jsonObject.get("trackingID");
        String accountNumber = (String) jsonObject.get("accountNumber");
        String alertID = (String) jsonObject.get("alertID");
        String batchID = (String) jsonObject.get("batchID");
        Boolean billableInd = (Boolean) jsonObject.get("billableInd");
        String billingDivision = (String) jsonObject.get("billingDivision");
        String campaignID = (String) jsonObject.get("campaignID");
        String campaignName = (String) jsonObject.get("campaignName");
        String city = (String) jsonObject.get("city");
        Long clientID = (Long) jsonObject.get("clientID");
        String consumerID = (String) jsonObject.get("consumerID");
        String conversationID = (String) jsonObject.get("conversationID");
        String country = (String) jsonObject.get("country");
        String county = (String) jsonObject.get("county");
        String dateOfBirth = (String) jsonObject.get("dateOfBirth");
        Boolean dialerDispatched = (Boolean) jsonObject.get("dialerDispatched");
        String dialerList = (String) jsonObject.get("dialerList");
        String dupeHash = (String) jsonObject.get("dupeHash");
        String encryptionKey = (String) jsonObject.get("encryptionKey");
        String expirationDate = (String) jsonObject.get("expirationDate");
        String expirationSource = (String) jsonObject.get("expirationSource");
        Boolean fieldLengthsScrubbed = (Boolean) jsonObject.get("fieldLengthsScrubbed");
        String firstName = (String) jsonObject.get("firstName");
        String gender = (String) jsonObject.get("gender");
        String inputID = (String) jsonObject.get("inputID");
        String installationID = (String) jsonObject.get("installationID");
        String lastName = (String) jsonObject.get("lastName");
        Long loadDate = (Long) jsonObject.get("loadDate");
        String locateStatus = (String) jsonObject.get("locateStatus");

        JSONArray medias = (JSONArray) jsonObject.get("medias");

        for(int i=0; i<medias.size(); i++) {
            System.out.println(medias.get(i));
        }

        Iterator iter = medias.iterator();

        while(iter.hasNext()){
            JSONObject innerObj = (JSONObject) iter.next();
            String mediaId = (String) innerObj.get("mediaId");
            String mediaAddress = (String)innerObj.get("mediaAddress");
            String status = (String)innerObj.get("status");
            Long seqNo = (Long)innerObj.get("seqNo");
            String reasonsInvalid = (String)innerObj.get("reasonsInvalid");

            System.out.println(mediaId + "," + mediaAddress + "," + status + "," + seqNo + "," + reasonsInvalid);
        }

        //System.out.println(medias);

        System.out.println("The tracking_id is: " + trackingID);

        return jsonString;
    }
}
