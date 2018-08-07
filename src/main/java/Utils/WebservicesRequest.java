package Utils;

import java.util.Map;

import com.jayway.restassured.RestAssured;
	
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
	import com.jayway.restassured.response.Response;
	import com.jayway.restassured.specification.RequestSpecification;

	public class WebservicesRequest {
			
		public static Response post(String url,Map<String, String> inputValue){			
			RequestSpecBuilder builder = new RequestSpecBuilder();			
			builder.addFormParameters(inputValue);
			RequestSpecification requestSpec = builder.build();
			RestAssured.useRelaxedHTTPSValidation();
			return	RestAssured
					.given()
					.spec(requestSpec)
					.when().post(url);
			
		}
		
		public static Response get(String url){
			RestAssured.useRelaxedHTTPSValidation();
			RequestSpecification requestSpecification = RestAssured.given();
			requestSpecification.contentType(ContentType.JSON);
			return requestSpecification.get(url);
			 
		}
		
		public static Response put(String url,String stringJSON){
			RequestSpecification requestSpecification = RestAssured.given().body(stringJSON);
			requestSpecification.contentType(ContentType.JSON);
			return requestSpecification.put(url);
			
		}
		
		public static Response delete(String url){
			RequestSpecification requestSpecification = RestAssured.given();
			requestSpecification.contentType(ContentType.JSON);
			return requestSpecification.delete(url);		
			
		}
	
	}