import com.api.pratice.bitz.components.Application.ApplicationBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class UploadFile extends ApplicationBase {

    private String SampleDataFile ="generate.json";
  public String name =   RandomStringUtils.randomAlphabetic(20);
//    public static void main(String[] args) {
//
////        File file = new File("C:/Users/Ideas2it/Downloads/students.jpg");
//        String uploadFile =  System.getProperty("user.dir");
//       String url =  uploadFile +"\\src\\main\\resources\\schema\\Documents\\PaymentsReciept.doc";
//                File file = new File(url);
//        System.out.println("The upload file >>>>>" + uploadFile);
//       Response response = RestAssured.given().multiPart("file",file,"multipart/form-data").
//              post("https://the-internet.herokuapp.com/upload").thenReturn();
//
//       System.out.println(response.prettyPrint());
//
//
//    }

//    @Test
    public void uploadDocument(){
        Response response = getSampleData("PaymentsReciept.doc","generate.json");
        System.out.println("REsponse is >>>>>>>>>>>>>>>>>>>>" + response.asString());
        Assert.assertEquals(response.getStatusCode(),200);

    }

//    @Test
    public void CreateValidData(){
        Response response =   EnterData();
        System.out.println("REsponse is >>>>>>>>>>>>>>>>>>>>" + response.asString());
        Assert.assertEquals(response.getStatusCode(),201);
    }

    //Upload the file with JSon
    @Test
    public void CreateDataWithJson(){
        Response response =   EnterJsonData(SampleDataFile ,name);
        System.out.println("REsponse is >>>>>>>>>>>>>>>>>>>>" + response.asString());

        Assert.assertEquals(response.getStatusCode(),201);
    }


}
