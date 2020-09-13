package com.pratice.reader;

import com.api.pratice.base.Base;

import java.util.Properties;

import static com.pratice.reader.InternalConfigManager.getAllConfig;

public class basicReader {
    protected static Properties internalProperties = null;
    public  static String  shemaDir = null;

    public static void main(String[] args) {
//        System.out.println(">>>Directory>>>" + System.getProperty("user.dir"));
//        ;

        String setDefault  = constant.PROPERTIES_FILE_DOCUMENT;
        internalProperties =   getAllConfig(setDefault);

        System.out.println("The Constant location "+ internalProperties );
      String data =   internalProperties.getProperty(constant.Data);

        System.out.println("The datadatadata location "+ data );






    }
}
