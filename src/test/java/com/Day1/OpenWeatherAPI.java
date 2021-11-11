package com.Day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OpenWeatherAPI {

	@Test
	public void getWeatherInfo1() {
		RestAssured.given()
		.when()
		.get("https://api.openweathermap.org/data/2.5/weather?q=Vadodara&appid=befade27e05964045351ebc18f9f840f")
		.then()
		.log()
		.body()
		.statusCode(200);
	}

	@Test(enabled = false, description = "Getting weather API information Generally")
	public void getWeatherInfo2() {
		Response res = RestAssured.given()
								.when()
								.get("https://api.openweathermap.org/data/2.5/weather?q=Vadodara&appid=befade27e05964045351ebc18f9f840f");
		System.out.println(res.asPrettyString());
		System.out.println(res.getTime());
		System.out.println(res.getStatusCode());
		System.out.println(res.getContentType());
	}

	@Test(enabled = true, description = "Getting weather API information Generally")
	public void getWeatherInfo3() {
		RestAssured.given()
		.queryParam("q", "Vadodara")
		.queryParam("appid", "befade27e05964045351ebc18f9f840f")
		.when()
		.get("https://api.openweathermap.org/data/2.5/weather")
		.then()
		.log()
		.body()
		.statusCode(200);
	}

	@Test(enabled = true, description = "Getting weather API information Generally")
	public void getWeatherInfo4() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("q", "Vadodara");
		param.put("appid", "befade27e05964045351ebc18f9f840f");
		
		RestAssured.given()
		.params(param)
		.when()
		.get("https://api.openweathermap.org/data/2.5/weather")
		.then()
		.log()
		.body()
		.statusCode(200);
	}
}
