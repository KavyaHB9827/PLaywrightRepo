package gettingstarted;

import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;

import org.jspecify.annotations.Nullable;
import org.testng.Assert;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class VerifyTextMessagedDemo {

	public static void main(String[] args) {
	Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
	Page page = browser.newPage();
	page.navigate("https://freelance-learn-automation.vercel.app/login",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
	page.getByText("Sign in").nth(1).click();
	String actualmsg = "Email and Password is required";
	
	//ASS1
	@Nullable
	String expectedmsg1 = page.locator(".errorMessage").textContent();
	Assert.assertEquals(expectedmsg1, actualmsg);
	//Assert.assertTrue(expectedmsg1.contains("Required"));
	System.out.println("Expected message via 2"+expectedmsg1);
	//ASS
	String expectedmsg2 = page.locator(".errorMessage").innerText();
	Assert.assertEquals( expectedmsg2,actualmsg);
	//Assert.assertTrue(expectedmsg2.contains("Required"));
	System.out.println("Expected message via 2"+expectedmsg2);
	
	//ASS3
	@Nullable
	String expectedmsg3 =(String)page.evaluate("document.getElementsByClassName('errorMessage')[0].innerText");
	System.out.println("Expected message via 2"+expectedmsg3);
	page.close();
	browser.close();
	

	}

}
