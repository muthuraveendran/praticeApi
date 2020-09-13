package com.pratice.reader;

import javax.security.auth.login.Configuration;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class InternalConfigManager {
    public synchronized static Properties getAllConfig(String PropertyFile) {

        try {
            InputStream is = InternalConfigManager.class.getResourceAsStream(PropertyFile);
            Properties testproperties = new Properties();
            testproperties.load(is);
            System.out.println("The Property File is " + testproperties);
            return testproperties;
        } catch (Exception ex) {
            return  null;
        }
    }
}
