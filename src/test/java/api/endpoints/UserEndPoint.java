package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;

import io.restassured.response.Response;

import java.util.HashMap;

public class UserEndPoint {
	
	public static Response CreateUser(User Payload)
	{
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
		
		.when()
			.post(Routes.post_url_user);
		
			
		return response;
		
		
	}

	public static Response ReadUser(String Username)
	{
		
		Response response = given()
							.pathParam("username", Username)
			
		
		.when()
			.get(Routes.get_url_user);
		
			
		return response;
		
		
	}
	
	public static Response UpdateUser(String UserName, User Payload)
	{
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", UserName)
			.body(Payload)
		
		.when()
			.put(Routes.put_url_user);
		
			
		return response;
		
		
	}
	
	public static Response DeleteUser(String Username)
	{
		
		Response response = given()
							.pathParam("username", Username)
			
		
		.when()
			.delete(Routes.delete_url_user);
		
			
		return response;
		
		
	}

}
