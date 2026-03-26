
//program is generating null obj error

package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 

{

	 Faker faker;
	 User userPayload;
	 
	 public  Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		
		Faker faker= new  Faker();
		User userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().fullName());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		//logs
		logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging");
		
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		
		logger.info("***********  Create user  ***************");
		
		Response response=UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	
		logger.info("***********  User created  ***************");
	}
	
	
	@Test(priority = 2)
	public void testgetUserByName()
	{
		
		logger.info("***********  Reading userInfo  ***************");
		
		Response response=UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	
		
		logger.info("***********  User info is displayed  ***************");
	}
	
	
	
	@Test(priority = 3)
	public void testUpdateUserByName()
	{
		
		logger.info("***********  Updating user  ***************");
		
		//update data using payload
		
		userPayload.setUsername(faker.name().fullName());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		
		Response response=UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	
	
		logger.info("***********  User updated  ***************");
		
		//checking data after update
		
	
		Response responseafterupdate=UserEndPoints2.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseafterupdate.getStatusCode(), 200);
	
	}
	
	
	@Test(priority = 4)
	public void testDeleteUserByName()
	{
		
		logger.info("***********  Deleting user  ***************");
		
		Response response= UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		logger.info("***********  User deleted  ***************");
		
	}
	
	
}
