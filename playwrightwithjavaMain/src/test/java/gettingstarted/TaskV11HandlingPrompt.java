package gettingstarted;

		import java.nio.file.Paths;

import org.testng.Assert;

		import com.microsoft.playwright.Browser;
		import com.microsoft.playwright.BrowserType;
		import com.microsoft.playwright.Page;
		import com.microsoft.playwright.Playwright;
		import com.microsoft.playwright.options.WaitUntilState;

public class TaskV11HandlingPrompt {

	public static void main(String[] args) {
	
			Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
			Page page = browser.newPage();
			page.navigate("https://freelance-learn-automation.vercel.app/course/manage",new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
			
			// 1. Register the dialog handler BEFORE the click that triggers the prompt
			
			page.onDialog(dialog ->{
				
				
				System.out.println("DilogType"+dialog.type());
				System.out.println("DilogMessage"+dialog.message());
				dialog.accept("Teamcity");   // category name typed into the prompt
				
				
			});
			// Click whatever opens the category prompt (e.g. "Add new category" near Select Category)
	        page.getByText("Add Category").click();
	     // Mandatory fields
	        page.setInputFiles("input[type='file']", Paths.get("C:/testdata/thumbnail.png")); // THUMBNAIL
	        page.getByPlaceholder("Course Name").fill("TeamCity Basics");                      // COURSENAME
	        page.getByPlaceholder("Description").fill("Learn CI/CD with TeamCity");            // DESCRIPTION
	        page.getByPlaceholder("Instructor").fill("Mukesh");                                // INSTRUCTOR
	        page.getByPlaceholder("Price").fill("999");                                        // PRICE
	        page.locator("input[name='startDate']").fill("2026-12-01");                        // STARTS FROM
	        page.locator("input[name='endDate']").fill("2027-03-01");                          // ENDS ON
	        page.getByLabel("Permanent").check();                                              // PERMANENT
	        page.getByLabel("Select Category").selectOption("Teamcity");                       // SELECT CATEGORY

	        page.getByText("Create Course").click();

	        Assert.assertTrue(page.getByText("TeamCity Basics").isVisible());
	        browser.close();
	    }
	}


		