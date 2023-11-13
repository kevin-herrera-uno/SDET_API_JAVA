package com.unosquare;

import org.testng.annotations.Test;
import com.apicore.ApiCore;
import com.github.cliftonlabs.json_simple.JsonException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import static org.hamcrest.Matchers.isA;
import java.io.IOException;

public class SignInEndpointTest {
	ApiCore apiCore;
	@BeforeMethod
	public void beforeMethod(){
		apiCore = new ApiCore();
	}
	@Test
	public void	PostRegisterTest() throws IOException, JsonException {
		String registerEndpoint = "/register";
		Response response = apiCore.PostRegister("./json/register.json", registerEndpoint);
		response.then()
			.assertThat().statusCode(200).assertThat().contentType(ContentType.JSON)
			.assertThat().body("id", isA(Integer.class))
			.assertThat().body("token", isA(String.class))
		.and()
			.extract().response();
		Reporter.log("Response Body: "+response.asString());
		Reporter.log("Response Code: "+response.getStatusCode());
		Reporter.log("JSON body Sent: "+apiCore.getRegisterObj().toJson());
		Reporter.log("Request URL: "+registerEndpoint);
	}
}
