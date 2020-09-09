import com.api.pratice.bitz.components.Application.ApplicationBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class UploadFile extends ApplicationBase {

    public static void main(String[] args) {

        File file = new File("C:/Users/Ideas2it/Downloads/students.jpg");

       Response response = RestAssured.given().multiPart("file",file,"multipart/form-data").
              post("https://the-internet.herokuapp.com/upload").thenReturn();

       System.out.println(response.prettyPrint());


    }

    @Test
    public void uploadDocument(){
        Response response = getSampleData("PaymentsReciept.doc","generate.json");
        System.out.println(response.prettyPrint());
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
