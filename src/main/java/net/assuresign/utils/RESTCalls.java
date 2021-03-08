package net.assuresign.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.assuresign.base.Base;

public class RESTCalls extends Base{

	public static Response GETRequest(String uRI) {
		
		log.info("Inside GETRequest call");
		
		String token = prop.getProperty("token");
		RequestSpecification request= RestAssured.given().header("Authorization", "Bearer "+ token);;
		request.contentType(ContentType.JSON);
		Response response = request.get(uRI);
		log.debug(request.log().all());
		return response;
	}

	public static Response POSTRequest(String uRI, String strJSON) {
		log.info("Inside POSTRequest call");
		
		String token = prop.getProperty("token");
		RequestSpecification request= RestAssured.given().header("Authorization", "Bearer "+ token);;
		request.contentType(ContentType.JSON);
		request.body(strJSON);
		Response response = request.post(uRI);
		log.debug(request.log().all());
		return response;
	}
	

	public static Response PUTRequest(String uRI, String strJSON) {
		log.info("Inside PUTRequest call");
		String token = prop.getProperty("token");
		
		RequestSpecification requestSpecification = RestAssured.given().header("Authorization", "Bearer "+ token);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.body(strJSON);
		Response response = requestSpecification.put(uRI);
		log.debug(requestSpecification.log().all());
		return response;
	}

	public static Response DELETERequest(String uRI, String strJSON) {
		log.info("Inside DELETERequest call");
		RequestSpecification requestSpecification = RestAssured.given().body(strJSON);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.delete(uRI);
		log.debug(requestSpecification.log().all());
		return response;
	}

}
