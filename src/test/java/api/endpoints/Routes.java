package api.endpoints;
/*
swagger url:				https://petstore.swgger.io
 
create user(post): 		https://petstore.swgger.io/v2/user

get user(Get):			https://petstore.swgger.io/v2/user/{username}

update user(Update):		https://petstore.swgger.io/v2/user/{username}

delete user(Delete):		https://petstore.swgger.io/v2/user/{username}

 */

public class Routes 

{

	public static String base_url= "https://petstore.swgger.io/v2";
	
	//User Model
	
	public static String post_url= base_url +"/user";
	public static String get_url= "/user/{username}";
	public static String update_url= "/user/{username}";
	public static String delete_url= "/user/{username}";
	
	//Store Module
		//Here we create store module urls
	
	
	
	//Pet Module
	//Here we create pet module urls
}
