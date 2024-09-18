package httpBacicRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class GetWithoutBDD {
	
	/*
  @Test
  public void singleUser() {
	  
	  // I will use the get() of RestAssured class & will store it in Response type variable
	  // RestAssured.get("https://reqres.in/api/users/2");
	  
	  Response res = RestAssured.get("https://reqres.in/api/users/2");
	  
	  System.out.println("Status code is: " + res.getStatusCode());
	  System.out.println("Status line is: " + res.getStatusLine());
	  System.out.println("Time taken is: " + res.getTime());
	  System.out.println("Content type is: " + res.contentType());
	  System.out.println("Pretty String is: " + res.asPrettyString());
	  
	  // Assertion can be applied lessgo
	  
	  Assert.assertEquals(res.getStatusCode(), 200, "You're TEST FAILEDD");
	  System.out.println("TEST PASSED bc STATUS code MATCHED");
	  
	  
	  System.out.println(" ------  xxxx  ------");
	  
  }
  */
  
  @Test
  public void allUsers() {
	  
	  Response res = RestAssured.get("https://reqres.in/api/users?page=2");
	  // res.jsonPath();  	// Returns/stores the entire json response now we can extract anything from it
	  
	  int pageNumber = res.jsonPath().getInt("page");
	  System.out.println("Page number is: "+ pageNumber);
	  
	  
  }
}
