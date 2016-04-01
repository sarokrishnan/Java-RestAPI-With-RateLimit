package com.saro.challenge.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by sbalakrishnan on 3/30/16.
 */
public class PropertyUtil {
    InputStream inputStream = null;
    public Properties getProperties() throws IOException {
        Properties properties = null;
        try {
            properties = new Properties();
            String fileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("File Not Found '" + fileName );
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }finally {
            inputStream.close();
        }
        return properties;
    }

}
