import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class BasicRestAssure {
//    @Test
        public void getStatusCode() {
            given().
                    get("https://jsonplaceholder.typicode.com/posts/3").
            then().
                 statusCode(200);
    }

//    @Test
        public void rootStatus() {
            given().
                 get("http://dummy.restapiexample.com/api/v1/employee/1").
            then().
                    root("data").
                   body("employee_name",is("Tiger Nixon"));

    }

//    @Test
        public void detatchStatus() {
            given().
                    get("https://reqres.in/api/users?page=2").
             then().
                    root("data[0]").
                    body("first_name",is("Michael")).
                    detachRoot("data[0]").
                    body("ad.company",is("StatusCode Weekly"));
    }

    /*
    * hit the url from the url hit another url
    * */
//    @Test
    public void extractDataAndhitAnotherUtl() {
     String href =   given().
                get("https://jsonplaceholder.typicode.com/photos/1").
        then().
                contentType(ContentType.JSON).
                body("albumId",equalTo(1)).
                extract().
                    path("url");
     System.out.println("<<<<<<<<<<< href >>>>>>>>>>>>" + href);
     given().when().get(href).then().statusCode(200);
    };

    /*
    * Extratcing  details as String and fetching deyails using json path
    * */


//    @Test
    public void testSchema() {
      String json =  when().
              get("http://dummy.restapiexample.com/api/v1/employees").
         then().
                extract().asString();
        JsonPath jsonpath = new JsonPath(json).setRoot("data");

        List<String> list = jsonpath.get("employee_name");

        System.out.println("<<<<<<<<<<<<>>>>>>>>>>>" + list);
    }

    /*
    * How to get headers value
    * */

//    @Test
    public void getHeaders() {
        Response response = get("https://jsonplaceholder.typicode.com/posts");
        // To get a single header
        String data =  response.getHeader("CF-RAY");
        System.out.println("<<<<<<<data>>>>>>>>>" + data);


        // To get All headers
        Headers header =  response.getHeaders();
        for (Header h : header) {
            System.out.println(h.getName()+":" + h.getValue());
        }
    }

    /*
    * How to get cookies
    * */
//    @Test
    public void setCookies() {
        Response response = get("https://jsonplaceholder.typicode.com/posts");
        Map<String, String> cookies =   response.getCookies();

        for(Map.Entry<String, String> entry : cookies.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /*
    * Cookie details
    * */

    //@Test
    public void DetailsCookie() {
        Response response = get("https://jsonplaceholder.typicode.com/posts");
        Cookie a = response.getDetailedCookie("__cfduid");
        System.out.println("Detailed: " + a.getExpiryDate());
        System.out.println("Detailed: " + a.hasExpiryDate());
        System.out.println("Detailed: " + a.hasValue());

    }

    // Generally COnect is used in Https instead of get

//    @Test
    public void TestConnectRequest() {
        when().
                request("CONNECT","https://www.fonts.com/web-fonts/developers/api/add-font").
                then().
                statusCode(200);
    }


  // we can pass alist of multiple value in param
    public void setMultipleValueParam() {
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        given().
                param("Key",list).
                when().
                request("CONNECT","https://www.fonts.com/web-fonts/developers/api/add-font").
                then().
                statusCode(200);
    }

    //Status code verification
//    @Test
    public void  testStatusResponse() {
        given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().statusLine("Request Method: GET");
    }

    //get body response
//    @Test
    public void testBodyResponse(){
        String responseString = get("https://jsonplaceholder.typicode.com/photos/1").asString();
        given().get("https://jsonplaceholder.typicode.com/photos/1").then().assertThat().body(equalTo(responseString));
    }

    // Buld the response header
    ResponseSpecification responsespec;
    //@BeforeClass
    public void setup() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        builder.expectHeader("content-type","application/json; charset=utf-8");
        builder.expectHeader("cache-control","max-age=43200");
        responsespec = builder.build();

    };

    //@Test
    public void response1() {
        when().get("https://jsonplaceholder.typicode.com/photos").then().
                spec(responsespec);
    };

    //Log if the Error has come
//    @Test
    public void checkError() {
        given().get("http://api.fonts.com/rest/json/Domains/").then().log().ifError();
    };

    //Base URI and Base PATH
    @BeforeClass
    public void baseSetUp(){
        baseURI = "https://jsonplaceholder.typicode.com";
        basePath= "/photos";
//        reset();
    };

   // @Test
    public void test1() {
        given().get("/2").then().statusCode(200).log().all();
    };

    /*
     we can handle SLL in same class and before class also.
     first test case  reffer to before class SSL validation
     Second test case reffer to same class SSL validation

    */
//    @BeforeClass
    public  void beforeSSL() {
        RestAssured.useRelaxedHTTPSValidation();
    }

//    @Test
    public void getSSL() {
        given().get("https://jsonplaceholder.typicode.com").then().assertThat().statusCode(200);// SSLPEERUnverifiedException
//        given().relaxedHTTPSValidation().when().get("https://jsonplaceholder.typicode.com").then().assertThat().statusCode(200);
    }

    /*
    we can also handle TLS protocol using overloaded methon
    * */
    @Test
    public void getTSL() {
        given().relaxedHTTPSValidation("TLS").when().get("https://jsonplaceholder.typicode.com").then().assertThat().statusCode(200);
    }


}
