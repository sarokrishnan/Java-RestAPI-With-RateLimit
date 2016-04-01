package com.saro.challenge.util;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.util.Properties;

/**
 * Created by sbalakrishnan on 3/30/16.
 */
public class DBConnectionUtil {

    public static DB getConnection(){
        MongoClient mongoClient = null;
        DB db= null;
        try{
            PropertyUtil prop = new PropertyUtil();
            Properties properties = prop.getProperties();
            mongoClient = new MongoClient(properties.getProperty("dbhost"), Integer.parseInt(properties.getProperty("port")));
            db = mongoClient.getDB( properties.getProperty("collection") );
        }catch(Exception e){
            e.printStackTrace();
        }

        return db;
    }
}
