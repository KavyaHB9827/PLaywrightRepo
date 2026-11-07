package gettingstarted;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class HandlingAutoSuggestionsUsingIndex {

	public static void main(String[] args) {
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page = browser.newPage();
		page.navigate("https://www.google.com/",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
		 page.locator("xpath=//ul[@role='listbox']//li");
		 page.locator(".gLFyf").fill("Mukesh Otwani");
		 page.locator("xpath=//ul[@role='listbox']//li").nth(5).click();//to click on 4th suggestion.
		
	}

}
