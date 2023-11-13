package com.apicore;

import java.io.FileReader;
import java.io.IOException;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import com.apiobjects.RegisterBody;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiCore {
    private RegisterBody registerObj;
    public RegisterBody getRegisterObj() {return registerObj;}
    
	public ApiCore() {
		RestAssured.baseURI = "https://reqres.in/api";
	}
	public Response PostRegister(String jsonFilePath, String endpointURI) throws IOException, JsonException{
		JsonFileParse(jsonFilePath);
		String registerEndpoint = endpointURI;
		return given()
			.headers("Content-Type", "application/json")
			.body(registerObj.toJson())
		.when()
			.post(registerEndpoint)
		.andReturn();
	}
	public Response GetUsers(String endpointURI) {
		return given().when().get(endpointURI);
	}
	private void JsonFileParse(String jsonFilePath) throws IOException, JsonException {
		try (FileReader fileReader = new FileReader(jsonFilePath)) {
            JsonObject deserialize = (JsonObject) Jsoner.deserialize(fileReader);
            Mapper mapper = new DozerBeanMapper();
            registerObj = mapper.map(deserialize, RegisterBody.class);
        }
	}
}
