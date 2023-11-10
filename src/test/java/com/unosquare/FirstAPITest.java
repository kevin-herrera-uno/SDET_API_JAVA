package com.unosquare;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;;

public class FirstAPITest {
	@BeforeMethod
	public void beforeMethod() {
	}
	@Test
	public void f_Gherkin() {
		Response response = given().when().get("https://reqres.in/api/users/2");
		response.then()
			.assertThat().statusCode(200).assertThat().contentType(ContentType.JSON)
			.assertThat().body("data.id", equalTo(2))// Assert that "data.id" = 2
			.assertThat().body("data.email", equalTo("janet.weaver@reqres.in"))// Assert that "data.email" = "janet.weaver@reqres.in"
			.assertThat().body("data.first_name", equalTo("Janet"))// Assert that "data.first_name" = "Janet"
			.assertThat().body("data.last_name", equalTo("Weaver"))// Assert that "data.last_name" = "Weaver"
			.assertThat().body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))// Assert that "data.avatar" = "https://reqres.in/img/faces/2-image.jpg"
			.assertThat().body("support.url", equalTo("https://reqres.in/#support-heading"))// Assert that "support.url" = "https://reqres.in/#support-heading"
			.assertThat().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));// Assert that "support.text" = "To keep ReqRes free, contributions towards server costs are appreciated!"
		Reporter.log("Sucess 200 validation");
		Reporter.log(response.asString());
	}
}
