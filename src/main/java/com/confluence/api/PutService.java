package com.confluence.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PutService {
	private static final Logger logger = LogManager.getLogger(PutService.class);
	public void putService(HashMap<String, String> responseBodyMap) {
		BasicConfigurator.configure();
		String[] numArray = {"1","2","3","4","5","6","7","8","9","0"};
		String randomString = "";
		for(int i=0;i<=8;i++) {
			randomString = randomString + numArray[(int)(9.0 * Math.random())];
		}
		String existingContentPrefix = responseBodyMap.get("body").split("</tbody>")[0].replace("colspan=\"1\"", "colspan=\\\"1\\\"").replace("class=\"wrapped\"", "class=\\\"wrapped\\\"");
		String existingContentSuffix = "</tbody>"+responseBodyMap.get("body").split("</tbody>")[1].replace("class=\"auto-cursor-target\"", "class=\\\"auto-cursor-target\\\"");
		int rowNumber = StringUtils.countMatches(responseBodyMap.get("body"),"<tr>");
		String anotherRow = "<tr><td>"+rowNumber+"</td><td>"+randomString+"</td><td>FSIT</td><td>Single Client Entity</td><td colspan=\\\"1\\\">DCO-"+randomString+"</td></tr>";
		String body =  "{\"id\":\"65623\",\"type\":\"page\",\"title\":\"Automation Results\",\"space\":{\"key\":\"AI\"},\"body\":{"
				+ "\"storage\":{\"value\":"+"\""+existingContentPrefix+anotherRow+existingContentSuffix+"\""+",\"representation\":"
						+ "\"storage\"}},\"version\":{\"number\":"+(Integer.valueOf(responseBodyMap.get("version"))+1)+"}}";
		logger.info("Request body is: \n"+body);
		given().header("Content-Type","application/json").header("Authorization","Basic YWRtaW46MjYyNjMxMjA=").
		body(body).when().put("http://192.168.0.26:8090/rest/api/content/65623").then().statusCode(200);
	}
}
