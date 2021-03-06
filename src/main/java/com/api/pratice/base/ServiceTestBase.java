package com.api.pratice.base;


import java.util.Properties;

public class ServiceTestBase {

public String propertiesFile = null;
protected Properties internalPropertie = null;
public String configDir = null;
public String   newEnv = null;

    public ServiceTestBase(String propertiesFile){
        String a = System.getProperty(Base.ENVIRONMENT_PROPERTY);
        this.propertiesFile = propertiesFile;

        System.out.println(">>>>>>>>>>>Inside the service base >>>>>>>>>>>>>>>>>" + a);
           String currentEnvironment =  System.getenv(Base.ENVIRONMENT_PROPERTY);
        configDir = "/" + a;
        System.out.println(">>>>>>>>>>>getting the property file>>>>>>>>>>>>>>>>>" + this.propertiesFile);
        readFromPropertyFiles();
        System.out.println(">>>>>User Directory >>>>>>>>>"+ System.getProperty("user.dir"));

    };

    protected void readFromPropertyFiles() {
        String setDefault =  Base.DEFAULT_CONFIG_DIRECTORY + propertiesFile;
       internalPropertie =  InternalConfigManager.getAllConfig(setDefault);
       System.out.println(">>>>>>>The internal Properties >>>>>>>>>>." + internalPropertie);
        String baseUrl = internalPropertie.getProperty(Base.HTTP_PRPERTY_BASE_URL);

        String configPath = configDir + propertiesFile;
        internalPropertie =  InternalConfigManager.getAllConfig(configPath);
         internalPropertie.putAll(InternalConfigManager.getAllConfig(configPath));
        String YoutubeUrl = internalPropertie.getProperty(Base.HTTP_PRPERTY_BASE_URL);

//        String YoutubeUrl  = internalPropertie.getProperty(configPath);

        System.out.println(" internalPropertie.putAll(InternalConfigManager.getAllConfig(configPath))." + YoutubeUrl);


    }


    public void add() {
        System.out.println("I am in add Function >>>>>>>>>>>>>>>");
    }
}
