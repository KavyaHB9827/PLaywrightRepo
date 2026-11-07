package gettingstarted;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class HandlingAutoSuggestions1 {

	public static void main(String[] args) {
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page = browser.newPage();
		page.navigate("https://www.google.com/",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
		Locator locator = page.locator("xpath=xpath=//ul[@role='listbox']//li");
		page.locator(".gLFyf").fill("Mukesh Otwani");
		System.out.println(locator.count());
		
		for(int i=0;i<locator.count();i++) {
			String text = locator.nth(i).innerText();
			System.out.println(text);
			 
			if(text.toLowerCase().contains("playwright")) {
				locator.nth(i).click();
				break;
			}
			
			page.close();
			browser.close();
		}
	}

}
