package gettingstarted;

import java.util.regex.Pattern;

import org.jspecify.annotations.Nullable;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;

public class VerifyTextMessagesViaPlaywrightAssertions {
	
	
	public static void main(String[] args) 
	{
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page = browser.newPage();
		page.navigate("https://freelance-learn-automation.vercel.app/login",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
		page.getByText("Sign in").nth(1).click();
		String actual = "Email and Password is required";
		@Nullable
		String expectedmsg = page.locator(".errorMessage").textContent();
		PlaywrightAssertions.assertThat(page.locator(".errorMessage")).containsText("Email and Password is required");//.assertions waited for 5 sec or 5000ms 
		PlaywrightAssertions.assertThat(page.locator(".errorMessage")).containsText(Pattern.compile("required"));
		page.close();
		browser.close();
	}

}

