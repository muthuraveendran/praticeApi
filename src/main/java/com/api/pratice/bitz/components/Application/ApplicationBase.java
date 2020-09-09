package com.api.pratice.bitz.components.Application;

import com.api.pratice.base.ServiceTestBase;
import com.api.pratice.bitz.components.helper.SchemaFileReader;
import com.api.pratice.bitz.components.helper.SchemaJsonReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.io.InputStream;

public class ApplicationBase extends ServiceTestBase {

  Gson gson =  new GsonBuilder().serializeNulls().create();

    public ApplicationBase() {
        super(Constant.PROPERTIES_FILE_DOCUMENT);
//        requestSpecBuilder.addHeader("Channel-Id","1061");
        requestSpecBuilder.addHeader("Cache-Control","max-age=0");
        requestSpecBuilder.addHeader("Connection","keep-alive");
        requestSpec = requestSpecBuilder.build();
       responseSpec = responseSpecBuilder.build();
        System.out.println("Inside the Application Base >>>>>>>>>>>>>>>>>>>> ");
    };

    public Response getSampleData(String FileName, String jsonFile) {
       InputStream docFile = getData(FileName);
        String sampleData = getSamplData(jsonFile);
        System.out.println("<<<<<Json Data e>>>>>>>" + sampleData);

        String URL = String.format(Constant.UploadUrl);
        return uploadDocumentPostRequest(requestSpec,responseSpec, FileName , docFile, URL);


//        System.out.println("<<<<<docFiledocFiledocFile>>>>>>>" + docFile);
//        String a = shemaDir + FileName;
//return shemaDir;
    };

    public InputStream getData(String FileName){
        String filePath = shemaDir+"/Documents/"+FileName;
        System.out.println("<<<<<<<<The File Path is >>>>>>>>>>>>>>>" + filePath);
        return SchemaFileReader.readFile(filePath);
    };
    public String getSamplData(String FileName) {
         String fileDir = shemaDir+"/Documents/"+FileName;
        JsonObject request = gson.fromJson(SchemaJsonReader.getReader(fileDir), JsonObject.class);
      String sampleValue = String.valueOf(request);
      System.out.println("<<<<<<<samplevalue>>>>>>>>>"+ sampleValue);
        return sampleValue;
    }



}
