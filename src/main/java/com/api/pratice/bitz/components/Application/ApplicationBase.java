package com.api.pratice.bitz.components.Application;

import com.api.pratice.base.ServiceTestBase;
import com.api.pratice.bitz.components.helper.SchemaFileReader;
import com.api.pratice.bitz.components.helper.SchemaJsonReader;
import com.api.pratice.schema.template.TemplateInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.HashMap;

public class ApplicationBase extends ServiceTestBase {

  Gson gson =  new GsonBuilder().serializeNulls().create();

    public ApplicationBase() {
        super(Constant.PROPERTIES_FILE_DOCUMENT);
//        requestSpecBuilder.addHeader("Channel-Id","1061");
//        requestSpecBuilder.addHeader("Cache-Control","max-age=0");
        requestSpecBuilder.addHeader("Content-Type","application/json");
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
//

    //String filename

    private TemplateInfo   getTemplateInfoRequest(String filename) {
        String fileDir =  shemaDir+"/Documents/"+  filename ;
        System.out.println("<<<<<<<<fileDdir>>>>>>" + fileDir);
        System.out.println("<<<<<<<<fileDir>>>>>>>" + fileDir);
        TemplateInfo request = gson.fromJson(SchemaJsonReader.getReader(fileDir),TemplateInfo.class);
        return request;
    };

    //getDetailFileName use to read the JSon file
    public  String getDetailFileName(String fileName) {
        String fileDir =  shemaDir+"/Documents/"+fileName;
        JsonObject request = gson.fromJson(SchemaJsonReader.getReader(fileDir),JsonObject.class);
        String sammpleValue = String.valueOf(request);
        return sammpleValue;
    };


    public String getSamplData(String FileName) {
         String fileDir = shemaDir+"/template/"+FileName;
        JsonObject request = gson.fromJson(SchemaJsonReader.getReader(fileDir), JsonObject.class);
      String sampleValue = String.valueOf(request);
      System.out.println("<<<<<<<samplevalue>>>>>>>>>"+ sampleValue);
        return sampleValue;
    };



    public Response EnterData() {
        String URL = String.format(Constant.location);
        JSONObject json = new JSONObject();
       json.put("id","");
        json.put("language","DEVELOPER");
        json.put("state","Bose");

        return PostData(requestSpec,responseSpec,json.toJSONString()  , URL);
    };


    //getDetailFileName use to read the JSon file
    public Response EnterJsonData(String data , String name) {
//       String sampleData1 = getDetailFileName(data);
        TemplateInfo sampleData =   getTemplateInfoRequest(data);
        System.out.println("The value inside sample Data "+ gson.toJson(sampleData));
          sampleData.setLanguage(name);
        String URL = String.format(Constant.location);
        return PostDataJson(requestSpec,responseSpec, gson.toJson(sampleData) ,URL);
    };






}
