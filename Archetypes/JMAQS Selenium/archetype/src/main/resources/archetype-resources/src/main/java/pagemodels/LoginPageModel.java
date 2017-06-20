#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.pagemodels;

import com.magenic.jmaqs.utilities.helper.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageModel
{
	
	/**
	 * The URL for the page
	 */
	private static final String PAGE_URL = Config.getValue("WebSiteBase") + "loginPage.html";
	
	/**
	 * By object for locating the User Name field
	 */
	private static final By USER_NAME_INPUT = By.cssSelector("${symbol_pound}UserName");
	
	/**
	 * By object for locating the Password field
	 */
	private static final By PASSWORD_INPUT = By.cssSelector("${symbol_pound}Password");
	
	/**
	 * The WebDriver object
	 */
	private WebDriver webDriver;
	
	
	/**
	 * Login Page Model Constructor
	 * 
	 * @param webDriver
	 * 			  The WebDriver object to use
	 */
	public LoginPageModel(WebDriver webDriver)
	{
		this.webDriver = webDriver;
	}
	
	/**
	 * Open the login page
	 */
	public void openLoginPage()
	{
		this.webDriver.navigate().to(PAGE_URL);
	}
}