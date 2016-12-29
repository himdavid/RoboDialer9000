package com.psqa.framework;

import com.google.common.collect.Multimap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

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
        String personID = (String) jsonObject.get("personID");
        String productID = (String) jsonObject.get("productID");
        Long resultDate = (Long) jsonObject.get("resultDate");
        //String accountEntity = (String) jsonObject.get("rootElement");

        String medias[] = {"mediaId", "mediaAddress", "status", "seqNo", "reasonsInvalid", "secondaryStateCode"};
        ArrayList<ArrayList<String>> JSONValues = getJSONArray(jsonObject, "medias", medias);
        printArray(JSONValues);

        return jsonString;
    }

    public static HashMap<String,String> parseJSONMap(String JSONString) throws ParseException {

        HashMap<String, String> map = new HashMap<>();
        JSONParser JSONParser = new JSONParser();

        JSONObject jObject = (JSONObject) JSONParser.parse(JSONString);

        Iterator iter = jObject.keySet().iterator();
        while(iter.hasNext()){
            String key = iter.next().toString();
            String val;
            if(jObject.get(key) == null) {
                val = "null";
            } else {
                val = jObject.get(key).toString();
            }
            map.put(key, val);
        }
        return map;
    }

    public ArrayList<ArrayList<String>> getJSONArray(JSONObject jsonObject, String array, String[] arrayValues) {


        JSONArray JSONChildObject = (JSONArray) jsonObject.get(array);
        ArrayList<String> JSONSubList = new ArrayList<>();
        ArrayList<ArrayList<String>> JSONMainList = new ArrayList<>();

        for(int i = 0; i < JSONChildObject.size(); i++) {
            JSONObject JSONNest = (JSONObject) JSONChildObject.get(i);
            for(int a = 0; a < arrayValues.length; a++) {
                String JSONValue;
                if(JSONNest.get(arrayValues[a]) == null) {
                    JSONValue = "null";
                } else {
                    JSONValue =  JSONNest.get(arrayValues[a]).toString();
                }
                JSONSubList.add(JSONValue);
            }
            JSONMainList.add(JSONSubList);
            JSONSubList = new ArrayList<>();
        }
        return JSONMainList;
    }


    public void printArray(ArrayList<ArrayList<String>> list) {

        for(int i = 0; i < list.size(); i++) {
            ArrayList<String> mainObject = list.get(i);
            for(int a = 0; a < mainObject.size(); a++) {
                System.out.println("Object " + i + ": " + mainObject.get(a));
            }
        }
    }

    public void printJSONMap(HashMap<String, String> map) {
        for(String json : map.keySet()) {
            String key = json.toString();
            String value = map.get(key).toString();

            System.out.println("Key: " + key +"\n" + "Value: " + value);
        }
    }
}
