package gettingstarted;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.WaitUntilState;

public class VerifyTextMessagesViaPlaywrightAssertions1 {
	
	public static void main(String[] args)
	 {
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page = browser.newPage();
		page.navigate("https://freelance-learn-automation.vercel.app/login",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
		page.getByText("Sign in").nth(1).click();
		
		
		String actualmsg = "Email and Password is required";
	String expectedmsg = page.locator(".errorMessage").textContent();
	
	PlaywrightAssertions.assertThat(page.locator(".errorMessage")).containsText("Email and Password is required");
	//PlaywrightAssertions.assertThat(page.locator(".errorMessage")).containsText(Pattern.compile("required"));
	page.close();
	browser.close();
	
				

		
	}

}
