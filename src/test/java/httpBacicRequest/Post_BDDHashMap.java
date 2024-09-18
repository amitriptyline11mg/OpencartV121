package httpBacicRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.matcher.RestAssuredMatchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class Post_BDDHashMap {
  @Test
  public void createResource() {
	  // Now s post method expects a req payload (In json)
	  // To tell the server that read my data in json format I have to add CONTENT TYPE AS A HEADER
	  	 
	// Request Payload
	  
	  HashMap<String, Object> map = new HashMap<String, Object>();
	  map.put("name", "Amit");
	  map.put("Job", "QA");
	  
	  // Pass this payload pre-req below
	  
	  
	  given()
	  .contentType("application/json") // Header
	  .body(map)
	  
	  .when()
	  .post("https://reqres.in/api/users")
	  
	  .then()
	  .statusCode(201)
	  .body("name", equalTo("Amit"))
	  .body("Job", equalTo("QA"))
	  .log().all();
	  
  }
}
