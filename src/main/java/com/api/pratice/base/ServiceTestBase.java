package com.api.pratice.base;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;

import java.io.InputStream;
import java.util.Properties;

public class ServiceTestBase {

public String propertiesFile = null;
protected Properties internalPropertie = null;
public String configDir = null;
public String   newEnv = null;
public String   shemaDir = null;
public RequestSpecification requestSpec;
public RequestSpecBuilder requestSpecBuilder =null;
public ResponseSpecification responseSpec;
public ResponseSpecBuilder responseSpecBuilder =null;


    public ServiceTestBase(String propertiesFile){
        String a = System.getProperty(Base.ENVIRONMENT_PROPERTY);
        this.propertiesFile = propertiesFile;

        System.out.println(">>>>>>>>>>>Inside the service base >>>>>>>>>>>>>>>>>" + a);
        configDir = "/" + a;
        System.out.println(">>>>>>>>>>>getting the property file>>>>>>>>>>>>>>>>>" + this.propertiesFile);

        requestSpecBuilder = new RequestSpecBuilder();
        responseSpecBuilder = new ResponseSpecBuilder();

        readFromPropertyFiles();
        System.out.println(">>>>>User Directory >>>>>>>>>"+ System.getProperty("user.dir"));
        setSchemaDir();
        String baseUrl = internalPropertie.getProperty(Base.HTTP_PRPERTY_BASE_URL);
        System.out.println("The Base Url is <<<<<>>>>>" + baseUrl);
        requestSpecBuilder.setBaseUri(baseUrl);


    };

    protected void readFromPropertyFiles() {
        String setDefault =  Base.DEFAULT_CONFIG_DIRECTORY + propertiesFile;
       internalPropertie =  InternalConfigManager.getAllConfig(setDefault);
       System.out.println(">>>>>>>The internal Properties >>>>>>>>>>." + internalPropertie);
        String baseUrl = internalPropertie.getProperty(Base.HTTP_PRPERTY_BASE_URL);

//        String configPath = configDir + propertiesFile;
//        internalPropertie =  InternalConfigManager.getAllConfig(configPath);
//         internalPropertie.putAll(InternalConfigManager.getAllConfig(configPath));

    }

    protected void setSchemaDir() {
        shemaDir = internalPropertie.getProperty(Base.SHEMA_BASE);
        System.out.println(">>>>>>>>>internal properties >>>>>>>>>>>>>>>>>>>>>>" + shemaDir);
    }

    public void add() {
        System.out.println("I am in add Function >>>>>>>>>>>>>>>");
    }


    protected Response uploadDocumentPostRequest(RequestSpecification reqSpec, ResponseSpecification resSpec , String filename, InputStream doc , String postURI) {
      Response responseApp =  RestAssured.given().log().all().spec(reqSpec)
        .multiPart("file",filename,doc).
        post(postURI).
                      then().spec(resSpec).extract().response();
      return responseApp;
    };
//String filename, InputStream doc ,
    protected Response PostData(RequestSpecification reqSpec, ResponseSpecification resSpec , String data,  String postURI) {
        System.out.println("Data>>>>>>>>>>>>>>>>>>>>>>>>>>." + data);
        Response responseApp =  RestAssured.given().log().all().spec(reqSpec).
        body( data ).
                 post(postURI);
//                .then().spec(resSpec).extract().response();
        return responseApp;
    };

    protected Response PostDataJson(RequestSpecification reqSpec, ResponseSpecification resSpec , String data, String postURI) {
        System.out.println("Data>>>>>>>>>>>>>>>>>>>>>>>>>>." + data);
        Response responseApp =  RestAssured.given().log().all().spec(reqSpec).
                body( data ).
                post(postURI);
//                .then().spec(resSpec).extract().response();
        return responseApp;
    };


}




