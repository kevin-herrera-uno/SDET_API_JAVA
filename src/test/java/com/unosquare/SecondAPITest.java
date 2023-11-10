package com.unosquare;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import static org.hamcrest.Matchers.*;

public class SecondAPITest {
	@Test
	public void f() {
		RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/unknown/2");
		
		int statusCode = response.getStatusCode();
		
		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode,200);
		Reporter.log("Success 200 validation");
		// Assert that "data.id" = 2
		response.then().body("data.id", equalTo(2));
		// Assert that "data.name" = "fuchsia rose"
		response.then().body("data.name", equalTo("fuchsia rose"));
		// Assert that "data.year" = 2001
		response.then().body("data.year", equalTo(2001));
		// Assert that "data.color" = "#C74375"
		response.then().body("data.color", equalTo("#C74375"));
		// Assert that "data.pantone_value" = "17-2031"
		response.then().body("data.pantone_value", equalTo("17-2031"));
		// Assert that "support.url" = "https://reqres.in/#support-heading"
		response.then().body("support.url", equalTo("https://reqres.in/#support-heading"));
		// Assert that "support.text" = "To keep ReqRes free, contributions towards server costs are appreciated!"
		response.then().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
		Reporter.log(response.body().asString());
	}

	@BeforeMethod
	public void beforeMethod() {
	}
	
}
