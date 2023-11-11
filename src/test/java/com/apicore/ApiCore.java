package com.apicore;

import static io.restassured.RestAssured.given;

import java.io.FileReader;
import java.io.IOException;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.apiobjects.RegisterObject;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiCore {
    private RegisterObject registerObj;
    public RegisterObject getRegisterObj() {return registerObj;}
    
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
	//Response response = given().when().get("https://reqres.in/api/unknown/2");}
	public Response GetUnknow(String endpointURI) {
		return given().when().get(endpointURI);
	}
	private void JsonFileParse(String jsonFilePath) throws IOException, JsonException {
		try (FileReader fileReader = new FileReader(jsonFilePath)) {
            JsonObject deserialize = (JsonObject) Jsoner.deserialize(fileReader);
            Mapper mapper = new DozerBeanMapper();
            registerObj = mapper.map(deserialize, RegisterObject.class);
        }
	}
}
