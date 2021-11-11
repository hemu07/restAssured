package com.Day1;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class githubRepo {
  @Test(enabled = false, description = "getting all repositories")
  public void getAllRepo() {
	  
	  given()
	  .auth()
	  .oauth2("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")
	  .when()
	  .get("https://api.github.com/user/repos")
	  .then()
	  .log()
	  .body()
	  .statusCode(200)
	  .time(Matchers.lessThan(2000L), TimeUnit.MILLISECONDS);
  }
  
  @Test(enabled = true, description = "for adding repository") 
	public void add() {
		JSONObject repo = new JSONObject();
		repo.put("name", "tsl-968restAssured");
		repo.put("description", "I am created by restAssured");
		repo.put("homepage", "https://github.com/hemu07");
		
		given()
		.auth()
		  .oauth2("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")
		  .header("Content-Type","application/json")
			.body(repo.toJSONString())
		  .when()
		  .post("https://api.github.com/user/repos")
		  .then()
		  .log()
		  .body()
		  .statusCode(201)
		  .time(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS);
		
  }
  
}
