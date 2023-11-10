package com.unosquare;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import static org.hamcrest.Matchers.*;

public class FirstAPITest {
	@Test
	public void f() {
		RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/users/2");
		
		int statusCode = response.getStatusCode();
		
		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode,200);
		Reporter.log("Success 200 validation");
		// Assert that "data.id" = 2
		response.then().body("data.id", equalTo(2));
		// Assert that "data.email" = "janet.weaver@reqres.in"
		response.then().body("data.email", equalTo("janet.weaver@reqres.in"));
		// Assert that "data.first_name" = "Janet"
		response.then().body("data.first_name", equalTo("Janet"));
		// Assert that "data.last_name" = "Weaver"
		response.then().body("data.last_name", equalTo("Weaver"));
		// Assert that "data.avatar" = "https://reqres.in/img/faces/2-image.jpg"
		response.then().body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
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
