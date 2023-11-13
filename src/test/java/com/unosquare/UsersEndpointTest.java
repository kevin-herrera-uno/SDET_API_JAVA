package com.unosquare;

import org.testng.annotations.Test;
import com.apicore.ApiCore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import static org.hamcrest.Matchers.*;

public class UsersEndpointTest {
	private ApiCore apiCore;
	@BeforeMethod
	public void beforeMethod() {
		apiCore = new ApiCore();
	}
	@Test
	public void GetUserByIDTest() {
		String unknownEndpoint = "/users/2";
		Response response = apiCore.GetUsers(unknownEndpoint);
		response.then()
			.assertThat().statusCode(200).assertThat().contentType(ContentType.JSON)
			.assertThat().body("data.id", equalTo(2))// Assert that "data.id" = 2
			.assertThat().body("data.email", equalTo("janet.weaver@reqres.in"))// Assert that "data.email" = "janet.weaver@reqres.in"
			.assertThat().body("data.first_name", equalTo("Janet"))// Assert that "data.first_name" = "Janet"
			.assertThat().body("data.last_name", equalTo("Weaver"))// Assert that "data.last_name" = "Weaver"
			.assertThat().body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))// Assert that "data.pantone_value" = "17-2031"
			.assertThat().body("support.url", equalTo("https://reqres.in/#support-heading"))// Assert that "support.url" = "https://reqres.in/#support-heading"
			.assertThat().body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));// Assert that "support.text" = "To keep ReqRes free, contributions towards server costs are appreciated!"
		Reporter.log("Sucess 200 validation");
		Reporter.log(response.asString());
	}/*
	{
    "data": {
        "id": 2,
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "avatar": "https://reqres.in/img/faces/2-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
	*/
}
