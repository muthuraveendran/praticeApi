import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Post_Put_Delete {

//    @Test
    public void test1() {
       RequestSpecification request  = RestAssured.given();
       request.header("Content-Type","application/json");

       JSONObject json = new JSONObject();
       json.put("id","3");
       json.put("title","Manager");
       json.put("author","Melbin Bose");

       request.body(json.toJSONString());

      Response response = request.post("http://localhost:3000/posts");

      int code = response.getStatusCode();
        Assert.assertEquals(code,201);

    };

//        @Test
    public void delteRequest() {
        RequestSpecification request  = RestAssured.given();

        Response response = request.delete("http://localhost:3000/posts/3");

        int code = response.getStatusCode();
        Assert.assertEquals(code,200);

    };

        @Test
    public void putDATA() {
        RequestSpecification request  = RestAssured.given();
        request.header("Content-Type","application/json");

        JSONObject json = new JSONObject();
        json.put("id","2");
        json.put("title","Developer");
        json.put("author","Anand");

        request.body(json.toJSONString());

        Response response = request.put("http://localhost:3000/posts/2");

        int code = response.getStatusCode();
        System.out.println("<<<<<<Code>>>>>" + code);

        Assert.assertEquals(code,200);

    };


}