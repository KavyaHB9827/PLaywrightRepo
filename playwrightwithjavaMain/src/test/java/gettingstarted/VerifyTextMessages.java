 package gettingstarted;

import org.jspecify.annotations.Nullable;
import org.testng.Assert;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class VerifyTextMessages {

	public static void main(String[] args) {
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page = browser.newPage();
		page.navigate("https://freelance-learn-automation.vercel.app/login",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
	page.getByText("Sign in").nth(1).click();
		String expectedmsg = "Email and Password is required";
		@Nullable
		//App1
		String actualmsg = page.locator(".errorMessage").textContent();
		Assert.assertEquals(expectedmsg, actualmsg);
		//or
		Assert.assertTrue(actualmsg.contains("Password is required"));
		/*
		System.out.println("Error message is"+ actualmsg);
		
		//App2
		String msgnew = page.locator(".errorMessage").innerText();
		System.out.println("New Error Message is"+ msgnew);
		
		//APP3
		@Nullable
		 String msgviaJS = (String)page.evaluate("document.getElementsByClassName('errorMessage')[0].textContent");
		System.out.println("ErrorMessage via Javascript"+msgviaJS);
		
		*/
		
		page.close();
		browser.close();

	}

}
