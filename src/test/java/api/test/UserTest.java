package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import api.endpoints.UserEndPoint;
import api.payload.User;
import api.utilities.RetryAnalyzer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User Userpayload;
	
	@Before
	public void SetupData() {
		
		faker = new Faker();
		Userpayload = new User();
		
		Userpayload.setId(faker.idNumber().hashCode());
		Userpayload.setUsername(faker.name().username());
		Userpayload.setFirstName(faker.name().firstName());
		Userpayload.setLastName(faker.name().lastName());
		Userpayload.setEmail(faker.internet().safeEmailAddress());
		Userpayload.setPassword(faker.internet().password());
		Userpayload.setPhone(faker.phoneNumber().phoneNumber());
		
			}
	 @Step("Executing API Test")
	@Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
	public void TestPostUser()
	{
		Response response = UserEndPoint.CreateUser(Userpayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		
	
	}
	 @Step("Executing API Test")
	@Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
	public void TestGetUser()
	{
		Response response = UserEndPoint.ReadUser(this.Userpayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		
		}
	 @Step("Executing API Test")
	@Test(priority = 3, retryAnalyzer = RetryAnalyzer.class)
	public void TestUpdateUserbyName()
	{
		 
		Userpayload.setFirstName(faker.name().firstName());
		Userpayload.setLastName(faker.name().lastName());
		Response response = UserEndPoint.UpdateUser(this.Userpayload.getUsername(),Userpayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		
		Response responseAfterUpdate = UserEndPoint.ReadUser(this.Userpayload.getUsername());
		responseAfterUpdate.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		
		}
	
	 @Step("Executing API Test")
	@Test(priority = 4, retryAnalyzer = RetryAnalyzer.class)
	public void DeleteUser()
	{
		Response response = UserEndPoint.DeleteUser(this.Userpayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);
	}
}
