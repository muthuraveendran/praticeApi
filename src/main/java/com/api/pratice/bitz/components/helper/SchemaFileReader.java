package com.api.pratice.bitz.components.helper;

import java.io.InputStream;
import org.apache.log4j.Logger;
public class SchemaFileReader {
        private static Logger logger = Logger.getLogger(SchemaFileReader.class);
        /**
         *
         * @param relativeResourcePath
         * @return
         */
        public synchronized static InputStream readFile(String relativeResourcePath) {
            InputStream inputStream = null;
            if (null != relativeResourcePath && !relativeResourcePath.isEmpty()) {
                inputStream = SchemaFileReader.class.getResourceAsStream(relativeResourcePath);
                System.out.println("<<<<<<<Iput file reader >>>>>>>>>>>>>>" + inputStream.toString());
            }
            return inputStream;
        }

}
