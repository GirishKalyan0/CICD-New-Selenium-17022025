package Automated.components;

import PageObjects.loginPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
	public WebDriver driver;
	public loginPage logpage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/Resources/GlobalData.properties");
		prop.load(file);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions cp = new ChromeOptions();
			cp.addArguments("--incognito");
			// Use new headless mode (if you need headless in CI). If you want to debug, remove headless.
			cp.addArguments("--headless=new");
			cp.addArguments("--no-sandbox");
			cp.addArguments("--disable-dev-shm-usage");
			// ensure a fixed large viewport
			cp.addArguments("--window-size=1920,1080");
			driver = new ChromeDriver(cp);
			// optional: set size again (not mandatory if window-size argument is present)
			driver.manage().window().setSize(new Dimension(1920, 1080));


		} else if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		// json to string

		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		// string to map
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;

	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("User.dir") + "//reports" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("User.dir") + "//reports" + testCaseName + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	public loginPage launchApp() throws IOException {
		driver = initializeDriver();
		logpage = new loginPage(driver);
		logpage.goTo();
		return logpage;

	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		if (driver != null) {
			driver.close();
		}
	}

}
