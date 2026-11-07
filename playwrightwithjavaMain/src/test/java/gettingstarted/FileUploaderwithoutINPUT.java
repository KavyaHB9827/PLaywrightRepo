package gettingstarted;

import java.nio.file.Paths;
import java.nio.file.Path;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class FileUploaderwithoutINPUT {
//NO INPUT TAG IF THERE IS A BUTTON JUST CLICK
	public static void main(String[] args) {
		
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page = browser.newPage();
		page.navigate("https://the-internet.herokuapp.com/upload",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
		 
		FileChooser filechooser = page.waitForFileChooser(()->page.locator("#drag-drop-upload").click());
		filechooser.setFiles(Paths.get("C:/Users/Administrator/Downloads/Opencart_LoginTestData.xlsx"));
		
		Path[] filestoupload= {
				Paths.get("C:/Users/Administrator/Downloads/Opencart_LoginTestData.xlsx"),
				Paths.get("C:/Users/Administrator/Downloads1/Opencart_LoginTestData.xlsx"),
				Paths.get("C:/Users/Administrator/Downloads2/Opencart_LoginTestData.xlsx"),
				
		};
	filechooser.setFiles(filestoupload);
	}

}
