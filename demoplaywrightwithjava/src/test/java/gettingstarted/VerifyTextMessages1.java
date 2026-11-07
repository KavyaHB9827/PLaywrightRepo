package gettingstarted;

import org.jspecify.annotations.Nullable;
import org.testng.Assert;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class VerifyTextMessages1 {

	public static void main(String[] args) {
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page=browser.newPage();
		page.navigate("https://freelance-learn-automation.vercel.app/login",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
		page.getByText("Sign in").nth(1).click();
		String actualmsg = "Email and Password is required";
		//Ass1
		 @Nullable
		String expectedmsg = page.locator(".errorMessage").textContent(); 
		 Assert.assertTrue(expectedmsg.contains("Password is required"));
		System.out.println("Errormessage is"+ actualmsg);
		//Ass2
		@Nullable
		  String jsmsg = (String)page.evaluate("document.getElementsByClassName('errorMessage')[0].textContent");
		System.out.println("Errormessage via Javascript" +jsmsg);
		page.close();
		browser.close();
	}
}
