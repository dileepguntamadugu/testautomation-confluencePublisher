package com.confluence.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class GetService {
	private static final Logger logger = LogManager.getLogger(GetService.class);
	public HashMap<String, String> getService() {
		BasicConfigurator.configure();
		HashMap<String, String> responseDataMap = new HashMap<>();
		RestAssured.defaultParser = Parser.JSON;
		Response response = given().queryParam("expand", "body.storage,version").header("Content-Type",ContentType.JSON).header("Authorization","Basic YWRtaW46MjYyNjMxMjA=").when().
		get("http://192.168.0.26:8090/rest/api/content/65623").then().contentType(ContentType.JSON).extract().response();
		logger.info(response.jsonPath().get("body.storage.value"));
		responseDataMap.put("body", (String) response.jsonPath().get("body.storage.value"));
		responseDataMap.put("version", String.valueOf(response.jsonPath().get("version.number")));
		return responseDataMap;
		
	}
}
