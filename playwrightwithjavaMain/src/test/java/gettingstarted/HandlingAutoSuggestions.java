package gettingstarted;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class HandlingAutoSuggestions {
	//in console -  	setTimeout(() => { debugger; }, 5000);//inspect console 
	//autosuggestion,autocontrol,dropdown without select tag,ajax control

	public static void main(String[] args) {
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page = browser.newPage();
		page.navigate("https://www.google.com/",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
		Locator locator = page.locator("xpath=//ul[@role='listbox']//li");//locate in such a way search text and received suggestion should get highlighted
		//here all suggestions  are developed using li stored under ul=unordered list 
		page.locator(".gLFyf").fill("Mukesh Otwani");	//locate search box
		System.out.println(locator.count());
		
		for(int i =0;i<locator.count();i++) {
			String text =locator. nth(i).textContent();
			System.out.println(text);
			
			//or only to get suggested text without other texts
			//locator.nth(i).innerText();
			if(text.toLowerCase().contains("playwright")) {
				locator.nth(i).click();
				break;
				
			}
			//page.close();
			//browser.close();
			
			
		}
		

	}

}
