package stepDefinition;


import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoint;
import api.payload.User;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
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
	
	@Given("Test Post API methon")
	 public void test_post_api_methon()
	{
		Response response = UserEndPoint.CreateUser(Userpayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		
	
	}

	 @When("Test Get API calls")
	 public void test_get_api_calls() 
	{
		Response response = UserEndPoint.ReadUser(this.Userpayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		
		}
	 
	 @When("Test Delete User API call")
	 public void test_delete_user_api_call()
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
	 @Then("Test update user call")
	 public void test_update_user_call()
	{
		Response response = UserEndPoint.DeleteUser(this.Userpayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);
	}
}
