import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DownloadFile {
    public static void main(String[] args) throws IOException {
       Response response = RestAssured.given().log().all()
                .when().get("https://reqres.in/api/users").andReturn();

       byte[] bytes = response.getBody().asByteArray();
        File file = new File("C:/Users/Ideas2it/Downloads/NewJsonFile.jpg");
        Files.write(file.toPath(), bytes);

    }
}
