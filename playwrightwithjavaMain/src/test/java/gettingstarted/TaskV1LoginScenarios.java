package gettingstarted;



	import java.util.regex.Pattern;

	import org.testng.Assert;
	import org.testng.annotations.Test;

	import com.microsoft.playwright.Browser;
	import com.microsoft.playwright.BrowserType;
	import com.microsoft.playwright.Page;
	import com.microsoft.playwright.Playwright;
	import com.microsoft.playwright.assertions.PlaywrightAssertions;
	import com.microsoft.playwright.options.WaitUntilState;

	public class TaskV1LoginScenarios {

		// reusable steps so each test stays short
		private Page openLoginPage(Browser browser) {
			Page page = browser.newPage();
			page.navigate("https://freelance-learn-automation.vercel.app/login",
					new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
			return page;
		}

		private void login(Page page, String email, String password) {
			page.getByPlaceholder("Enter Email").fill(email);
			page.getByPlaceholder("Enter Password").fill(password);
			page.getByText("Sign in").nth(1).click();
		}

		// a. valid email + invalid password
		@Test
		public void validEmailInvalidPassword() {
			Browser browser = null;
			Page page = null;
			try {
				browser = Playwright.create().chromium()
						.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
				page = openLoginPage(browser);

				login(page, "admin@email.com", "wrongpass@123");

				// error message appears (auto-retries until visible)
				PlaywrightAssertions.assertThat(page.getByText(Pattern.compile("incorrect", Pattern.CASE_INSENSITIVE)))
						.isVisible();
				// user must stay on the login page
				PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login"));
				Assert.assertTrue(page.url().contains("login"));
			} finally {
				if (page != null) page.close();
				if (browser != null) browser.close();
			}
		}

		// b. valid email + valid password
		@Test
		public void validEmailValidPassword() {
			Browser browser = null;
			Page page = null;
			try {
				browser = Playwright.create().chromium()
						.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
				page = openLoginPage(browser);

				login(page, "admin@email.com", "admin@123");

				PlaywrightAssertions.assertThat(page.locator(".welcomeMessage")).containsText("Welcome");
				Assert.assertTrue(page.getByText("Welcome Admin Manager to Learn Automation Courses").isVisible());
				// user has left the login page
				PlaywrightAssertions.assertThat(page).not().hasURL(Pattern.compile("login"));

				// sign out so the session ends cleanly
				page.getByAltText("menu").click();
				page.getByText("Sign out").click();
				PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login"));
			} finally {
				if (page != null) page.close();
				if (browser != null) browser.close();
			}
		}

		// c. invalid email + valid password
		@Test
		public void invalidEmailValidPassword() {
			Browser browser = null;
			Page page = null;
			try {
				browser = Playwright.create().chromium()
						.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
				page = openLoginPage(browser);

				login(page, "wrong@email.com", "admin@123");

				PlaywrightAssertions.assertThat(page.getByText(Pattern.compile("incorrect", Pattern.CASE_INSENSITIVE)))
						.isVisible();
				PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login"));
				Assert.assertTrue(page.url().contains("login"));
			} finally {
				if (page != null) page.close();
				if (browser != null) browser.close();
			}
		}

		// d. invalid email + invalid password
		@Test
		public void invalidEmailInvalidPassword() {
			Browser browser = null;
			Page page = null;
			try {
				browser = Playwright.create().chromium()
						.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
				page = openLoginPage(browser);

				login(page, "wrong@email.com", "wrongpass@123");

				PlaywrightAssertions.assertThat(page.getByText(Pattern.compile("incorrect", Pattern.CASE_INSENSITIVE)))
						.isVisible();
				PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login"));
				Assert.assertTrue(page.url().contains("login"));
			} finally {
				if (page != null) page.close();
				if (browser != null) browser.close();
			}
		}
	}


