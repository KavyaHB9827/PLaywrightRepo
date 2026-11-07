package gettingstarted;

import java.nio.file.Path;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

// must have input tag and type = file
public class FileUploaderINPUTtag {

	public static void main(String[] args) throws InterruptedException {
		Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		Page page = browser.newPage();
		page.navigate("https://the-internet.herokuapp.com/upload",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
		//Keeping file in PC
		//page.locator("#file-upload").setInputFiles(Path.of("C:/Users/Administrator/Downloads/BloodTest.pdf"));
		
		//keeping file in CWD
		//page.locator("#file-upload").setInputFiles( Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest.pdf"));
			   
		
		/*Multiple Files--user.dir=project root directory
		 * 
		 *APPROACH1
		Path[]files= {
				Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest.pdf"),
				Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest1.pdf"),
				Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest2.pdf")	
				
		};
		
		page.locator("#file-upload").setInputFiles(files);

			*/	
		//Approach 2
		page.locator("#file-upload").setInputFiles(new Path[]
				{
						Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest.pdf"),
						Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest1.pdf"),
						Path.of(System.getProperty("user.dir") + "/src/test/resources/files/BloodTest2.pdf")	
						
						
				});
		Thread.sleep(3000);
		//To remove uploaded files
		page.locator("#file-upload").setInputFiles(new Path[0]);
		
		//To upload from buffer memeory
		


	}
}
