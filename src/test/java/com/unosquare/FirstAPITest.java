package com.unosquare;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import java.io.FileReader;
import java.io.IOException;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import com.apiobjects.RegisterObject;
import com.github.cliftonlabs.json_simple.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isA;

public class FirstAPITest {
    private RegisterObject registerObj;
	@BeforeMethod
	public void beforeMethod() throws IOException, JsonException {
		try (FileReader fileReader = new FileReader("./json/register.json")) {
            JsonObject deserialize = (JsonObject) Jsoner.deserialize(fileReader);
            Mapper mapper = new DozerBeanMapper();
            registerObj = mapper.map(deserialize, RegisterObject.class);
        }
	}
	@Test
	public void f_Gherkin() {
		RestAssured.baseURI = "https://reqres.in/api";
		String registerEndpoint = "/register";
		Response r =
		given()
			.headers("Content-Type", "application/json")
			.body(registerObj.toJson())
		.when()
			.post(registerEndpoint)
		.then()
			.assertThat().statusCode(200).assertThat().contentType(ContentType.JSON)
			.assertThat().body("id", isA(Integer.class))
			.assertThat().body("token", isA(String.class))
		.and()
			.extract().response();
		Reporter.log("Response Body: "+r.asString());
		Reporter.log("Response Code: "+r.getStatusCode());
		Reporter.log("JSON body Sent: "+registerObj.toJson());
		Reporter.log("Request URL: "+registerEndpoint);
	}
}
