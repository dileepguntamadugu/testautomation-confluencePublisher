package com.confluence.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PutService {
	private static final Logger logger = LogManager.getLogger(PutService.class);
	public void putService(HashMap<String, String> responseBodyMap, Properties prop, HashMap<String, String> dataMap) {
		String automationResultsPageID = prop.getProperty("automation.results.pageID");
		String endPointURL = prop.getProperty("server.endpoint.url")+prop.getProperty("confluence.content.path")
		+automationResultsPageID;
		BasicConfigurator.configure();
		String[] numArray = {"1","2","3","4","5","6","7","8","9","0"};
		String randomString = "";
		for(int i=0;i<=8;i++) {
			randomString = randomString + numArray[(int)(9.0 * Math.random())];
		}
		String existingContentPrefix = responseBodyMap.get("body").split("</tbody>")[0].replace("colspan=\"1\"", "colspan=\\\"1\\\"").replace("class=\"wrapped\"", "class=\\\"wrapped\\\"");
		String existingContentSuffix = "</tbody>"+responseBodyMap.get("body").split("</tbody>")[1].replace("class=\"auto-cursor-target\"", "class=\\\"auto-cursor-target\\\"");
		int rowNumber = StringUtils.countMatches(responseBodyMap.get("body"),"<tr>");
		String anotherRow = "<tr><td>"+rowNumber+"</td><td>"+dataMap.get("Story")+"</td><td>"+dataMap.get("Title")+"</td><td>"+dataMap.get("Result")+
				"</td><td colspan=\\\"1\\\">"+dataMap.get("Date")+"</td><td>"+dataMap.get("Stability")+"</td><td>"+dataMap.get("Duration (s)")+"</td></tr>";
		String body =  "{\"id\":\""+automationResultsPageID+"\",\"type\":\"page\",\"title\":\""+prop.getProperty("page.title")+"\",\"space\":{\"key\":\""+prop.getProperty("space.key")+"\"},\"body\":{"
				+ "\"storage\":{\"value\":"+"\""+existingContentPrefix+anotherRow+existingContentSuffix+"\""+",\"representation\":"
						+ "\"storage\"}},\"version\":{\"number\":"+(Integer.valueOf(responseBodyMap.get("version"))+1)+"}}";
		logger.info("Request body is: \n"+body);
		given().header("Content-Type","application/json").header("Authorization",prop.getProperty("authorization")).
		body(body).when().put(endPointURL).then().statusCode(200);
	}
}
