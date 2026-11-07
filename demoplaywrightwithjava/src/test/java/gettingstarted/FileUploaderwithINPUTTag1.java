package gettingstarted;

import java.nio.file.Path;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

public class FileUploaderwithINPUTTag1 {

	public static void main(String[] args) {
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page = browser.newPage();
		page.navigate("https://the-internet.herokuapp.com/upload",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
		page.locator("#file-upload").setInputFiles(Path.of("C:/Users/Administrator/Downloads/BloodTest.pdf"));
		
		//multiple files 1
		Path[]files= {
				
				Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest.pdf"),
				Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest1.pdf"),
				Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest2.pdf")	
		};
		
		page.locator("#file-upload").setInputFiles(files);
		
		//multuple files 2
		page.locator("#file-upload").setInputFiles(new Path[]
				
				{
						
				
		Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest.pdf"),
		Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest1.pdf"),
		Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest2.pdf")	
				
				});
		
		//To remove files
		
		page.locator("#file-upload").setInputFiles(new Path[0]);
				
		
	}

}
