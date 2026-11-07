package gettingstarted;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FirstPlayWrightTest1 {
//playwright runs scripts in headless mode so you cant see any chrome tab opened after scripts runs and it still under execution until you terminate sessoon
	public static void main(String[] args) {
		Playwright pw=Playwright.create();
		BrowserType browserType=pw.firefox();
		Browser browser=browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"))  ;//setChannel("chrome"))=launch your script in pre installed chrome browser
		Page page= browser.newPage();
		page.navigate("https://playwright.dev/java/docs/intro");
		String title=page.title();
		System.out.println("Title is "+title);
		
		//page.close();
		//browser.close();
		//pw.close();
		

	}

}
