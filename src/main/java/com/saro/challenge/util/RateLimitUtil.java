package com.saro.challenge.util;

import java.io.IOException;
import java.util.List;
import java.util.*;

/**
 * Created by sbalakrishnan on 3/30/16.
 */

public class RateLimitUtil {

    private static final int maxReq = 1;
    PropertyUtil prop = new PropertyUtil();

    private Map<String, List<Long>> apikeyMap = new  HashMap();
    public boolean isRateLimited(String apiKey) throws IOException {

        Properties properties = prop.getProperties();
        int maxReq =  properties.getProperty(apiKey)!=null ? Integer.parseInt(properties.getProperty(apiKey)) : Integer.parseInt(properties.getProperty("globalmaxreq"));
        long time = System.currentTimeMillis();
        List<Long> timeList =  apikeyMap.containsKey(apiKey) ? apikeyMap.get(apiKey) : new LinkedList();
        timeList.add(0, time);
        long previousTime = System.currentTimeMillis() - Long.parseLong(properties.getProperty("Nminute"));

        List<Long> newTimestamps = new LinkedList();
        for (int i = 0; i < timeList.size(); i++) {
            Long currentTimeStamp = timeList.get(i);
            if (currentTimeStamp  >= previousTime) {
                newTimestamps.add(currentTimeStamp);
            } else {
                break;
            }
        }

        apikeyMap.put(apiKey, newTimestamps);

        if (newTimestamps.size() > maxReq) {
            return false;
        } else {
            return true;
        }

    }

}
