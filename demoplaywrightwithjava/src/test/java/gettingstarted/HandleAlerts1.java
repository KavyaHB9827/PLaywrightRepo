package gettingstarted;

import org.testng.Assert;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class HandleAlerts1 {

	public static void main(String[] args) {
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page = browser.newPage();
		page.navigate("https://the-internet.herokuapp.com/javascript_alerts",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
		
		page.onDialog(dialog ->{
			String msg = dialog.message();
			System.out.println("Dialog text is "+msg);
			Assert.assertTrue(msg.contains("I am a JS Alert"));
			dialog.accept();
				 
		});
		
		page.getByText("Click for JS Alert").click();
		browser.close();
		
	}
 
}
