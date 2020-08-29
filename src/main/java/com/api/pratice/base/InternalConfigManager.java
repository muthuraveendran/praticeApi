package com.api.pratice.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.nio.file.Files;


public class InternalConfigManager {
    /**
     * Reads all properties from file.
     *
     * @return properties
     */
    public synchronized static Properties getAllConfig(String propertiesFile) {
        try {
            InputStream is = InternalConfigManager.class.getResourceAsStream(propertiesFile);
            if (is == null) {
                File file = new File(propertiesFile);
                if (!file.isDirectory()) {
                    if (file.exists()) {
                        is = new FileInputStream(file);
                    } else {
                        throw new IOException("File not exist: " + propertiesFile);
                    }
                } else {
                    throw new IOException("Unexpected dir path passed (a file path should be passed): " +
                            propertiesFile);
                }
            }
            Properties testProperties = new Properties();
            testProperties.load(is);
            return testProperties;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

}
