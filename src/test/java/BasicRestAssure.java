import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class BasicRestAssure {
    @Test
        public void getStatusCode() {
            given().
                    get("https://jsonplaceholder.typicode.com/posts/3").
            then().
                 statusCode(200);
    }
}
