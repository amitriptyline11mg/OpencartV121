package httpBacicRequest;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

/*
given() : Prerequisite (Req header, payload, query, path param, cookies, authentication)

when()  : API request

then()  : Validation

*/

public class Get_RequestBDD {

  @Test
  public void getSingleUser() {
	  
	  given()
	  
	  .when()
	  .get("https://reqres.in/api/users/2")
	  
	  
	  .then()
	  .statusCode(200)
	  .body("data.id", equalTo(2))
	  .body("support.url", equalTo("https://reqres.in/#support-heading"))
	  
	  
	  
	  .log().all();
  }
  
  @Test
  public void getAllUser() {
	  
	  given()
	  
	  .when()
	  .get("https://reqres.in/api/users?page=2")
	  	  
	  .then()
	  .statusCode(200)
	  .body("data[1].id", equalTo(8))
	// hasItems() Check all element are in collection
	  .body("data.id", hasItems(8,9,10,12,11)) // Pass
	  .body("data.id", hasItems(8,9,10)) // Pass
	  .body("data.id", hasItems(11,12,10)) // Pass
	  
	// hasItems() Check all element are in collection are in stric order
	// this will throw an error if items are out of order or missing any element
//	  .body("data.id", contains(8,11,12)) // fail
	  .body("data.id", contains(7, 8, 9, 10, 11, 12))
	  
	  .log().body();
	  
	  
	  
	  
	  
  }
}
