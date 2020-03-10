package com.confluence.api;

import static io.restassured.RestAssured.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PostService {
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(PostService.class);
	public void postService() {
		String body = "{\"type\":\"page\",\"title\":\"Automation Results\",\"ancestors\":[{\"id\":\"65609\"}],\"space\":{\"key\":\"AI\"},\"body\":{"
				+ "\"storage\":{\"value\":\"<p>This is a new page</p>\",\"representation\":\"storage\"}}}";
		given().header("Content-Type","application/json").header("Authorization","Basic YWRtaW46MjYyNjMxMjA=").
		body(body).when().post("http://192.168.0.26:8090/rest/api/content/").then().statusCode(200).log().all();
		
	}
}
