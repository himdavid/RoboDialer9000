package com.psqa.framework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by david_him on 12/29/2016.
 */
public class ValidationFactory {

    public ValidationFactory() {

    }

    public boolean validatePETesterInputMapping(HashMap<String, String> mapTestFile, HashMap<String, String> mapJSON) {
        boolean testStatus = false;

        String keyTestFile;
        String valueTestFile;
        String keyJSON = null;
        String valueJSON;

        int countFail = 0;

        for(Map.Entry<String, String> entry1 : mapTestFile.entrySet()) {
            keyTestFile = entry1.getKey().toLowerCase().replace("_", "");
            valueTestFile = entry1.getValue();

            if(!mapJSON.containsKey(keyTestFile)) {
                System.out.println("Did not find a JSON field for: " + keyTestFile);

                if (mapJSON.containsValue(valueTestFile)) {

                    for (Map.Entry<String, String> entry2 : mapJSON.entrySet()) {
                        keyJSON = entry2.getKey();
                        valueJSON = entry2.getValue();

                        if (valueTestFile.equals(valueJSON)) {
                            System.out.println("\tBut found the value: " + valueJSON + " correponding to JSON field: " + keyJSON);
                        }
                    }
                }
                countFail++;

            } else {
                valueJSON = mapJSON.get(keyTestFile);
                System.out.println("Comparing test file field:" + keyTestFile + " \n\t value: " + valueTestFile);
                System.out.println("Found JSON value:" + valueJSON);
            }
        }

        if(countFail == 0) {
            testStatus = true;
        }

        return testStatus;
    }

}
