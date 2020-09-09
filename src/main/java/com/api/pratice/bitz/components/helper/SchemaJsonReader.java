package com.api.pratice.bitz.components.helper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class SchemaJsonReader {
        public synchronized static Reader getReader(String relativeResourcePath){
            String path = null;
            Reader reader = null;
            try {
                path = System.getProperty("user.dir") + "/src/main/resources" + relativeResourcePath;
                reader = new FileReader(path);
            }catch (IOException e){
                System.out.println("Error while creating the reader for the file: " + path);
                return null;
            }
            return reader;
        }
    }
