package com.Day1;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PositiveTest {
	String id;
  
	@Test(enabled = true, description = "for getting all contact list")
  public void getAllContacts() {
		
		given()
		.when()
		.get("http://3.13.86.142:3000/contacts")
		.then()
		.log()
		.body()
		.statusCode(200);	
	}
	  
	@Test(enabled = true, description = "for adding contact") 
	public void addContact() {
		JSONObject loc = new JSONObject();
		loc.put("city", "Pune");
		loc.put("country", "India");

		JSONObject emp = new JSONObject();
		emp.put("jobTitle", "Automation Tester");
		emp.put("company", "LTI");
		
		JSONObject ob = new JSONObject();
		ob.put("firstName", "Mayank");
		ob.put("lastName", "Sharma");
		ob.put("email", "asmith@gmail.com");
		ob.put("location", loc);
		ob.put("employer", emp);
		
	
		 id = given()
				.header("Content-Type","application/json")
				.body(ob.toJSONString())
			.when()
			.post("http://3.13.86.142:3000/contacts")
			.then()
			.log()
			.body()
			.statusCode(200)
			.extract()
			.jsonPath()
			.get("_id");
		System.out.println("id is" + id);	
	}
	
	@Test(enabled = true,dependsOnMethods = "addContact", description = "for getting specific contact details") 
	public void getContactDetails() {
		
		given()
		.when()
		.get("http://3.13.86.142:3000/contacts/" + id)
		.then()
		.log()
		.body()
		.statusCode(200);
	}
	
	@Test(enabled = true, dependsOnMethods = "getContactDetails", description = "for updating contact") 
	public void updateContact() {
		JSONObject loc = new JSONObject();
		loc.put("city", "Pune");
		loc.put("country", "India");

		JSONObject emp = new JSONObject();
		emp.put("jobTitle", "Automation Tester");
		emp.put("company", "LTI");
		
		JSONObject ob = new JSONObject();
		ob.put("firstName", "John");
		ob.put("lastName", "Smith");
		ob.put("email", "asmith@gmail.com");
		ob.put("location", loc);
		ob.put("employer", emp);
		
		given()
		.header("Content-Type","application/json")
		.body(ob.toJSONString())
		.when()
		.put("http://3.13.86.142:3000/contacts/" + id)
		.then()
		.log()
		.body()
		.statusCode(204); 
	}
	
	@Test(enabled = true, dependsOnMethods = "updateContact", description = "for getting specific contact details") 
	public void deleteDetails() {
		
		given()
		.when()
		.delete("http://3.13.86.142:3000/contacts/" + id)
		.then()
		.log()
		.body()
		.statusCode(204);
	}
	
}
