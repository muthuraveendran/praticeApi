import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

public class UploadFile {

    public static void main(String[] args) {

        File file = new File("C:/Users/Ideas2it/Downloads/students.jpg");

       Response response = RestAssured.given().multiPart("file",file,"multipart/form-data").
              post("https://the-internet.herokuapp.com/upload").thenReturn();

       System.out.println(response.prettyPrint());
    }
}
