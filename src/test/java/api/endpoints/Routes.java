package api.endpoints;

public class Routes {

	public static String base_URL = "https://petstore.swagger.io/v2";
	
	//user module : Operations about user
	
	public static String post_url_user = base_URL+"/user";
	public static String get_url_user = base_URL+"/user/{username}";
	public static String put_url_user = base_URL+"/user/{username}";
	public static String delete_url_user = base_URL+"/user/{username}";
	
}
