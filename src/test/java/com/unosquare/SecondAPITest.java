package com.unosquare;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;;

public class SecondAPITest {
	@BeforeMethod
	public void beforeMethod() {
	}
	@Test
	public void f_Gherkin() {
		Response response = given().when().get("https://reqres.in/api/unknown/2");
		response.then()
			.assertThat().statusCode(200).assertThat().contentType(ContentType.JSON)
			.assertThat().body("data.id", equalTo(2))// Assert that "data.id" = 2
			.assertThat().body("data.name", equalTo("fuchsia rose"))// Assert that "data.name" = "fuchsia rose"
			.assertThat().body("data.year", equalTo(2001))// Assert that "data.year" = 2001
			.assertThat().body("data.color", equalTo("#C74375"))// Assert that "data.color" = "#C74375"
			.assertThat().body("data.pantone_value", equalTo("17-2031"))// Assert that "data.pantone_value" = "17-2031"
			.assertThat().body("support.url", equalTo("https://reqres.in/#support-heading"))// Assert that "support.url" = "https://reqres.in/#support-heading"
			.assertThat().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));// Assert that "support.text" = "To keep ReqRes free, contributions towards server costs are appreciated!"
		Reporter.log("Sucess 200 validation");
		Reporter.log(response.asString());
	}
}
