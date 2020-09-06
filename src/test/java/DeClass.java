import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

public class DeClass {

//    @Test
    public  void DeserTest() {
        RestAssured.baseURI = "https://reqres.in/api/users?page=2";

        ListUserPojo pojo = RestAssured.given().when().get().as(ListUserPojo.class);
//        System.out.println(">>>>>>>>>POJO>>>>>>>>>" + pojo.toString());
//        System.out.println("<<<<<<get the specify POJO Cass>>>>>>>>" + pojo.getData());

        List<ListDataPojo> list = pojo.getData();
        for (int i =0; i<list.size(); i++) {
            //get(i) will remove the object and print only array
            System.out.println("This is my" + i + "element" + list.get(i));
        }


    }
    @Test
    public void serial(){
        RestAssured.baseURI = "http://localhost:3000/posts";

        ListSeriPojo series = new ListSeriPojo("PrabuShankar","","Developer");

//        System.out.println("<<<<<series>>>>>>"+ series.toString());



        Response resp =  RestAssured.given().when().body(series).post("http://localhost:3000/posts");

        System.out.println(">>>>>>>>>POJO>>>>>>>>>" + resp.getBody().asString());
//        System.out.println("<<<<<<get the specify POJO Cass>>>>>>>>" + pojo.getData());



    }

}
