package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

// These two we need to import for the logger
import org.apache.logging.log4j.LogManager; // Log4j
import org.apache.logging.log4j.Logger; // Log 4j 
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities3.RandomStringGenerator;

public class BaseClass {
	// This class contains thing that will be used by many other test classes

	public static WebDriver driver;

	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Master", "Regression", "DataDriven" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		logger = LogManager.getLogger(this.getClass()); // Dynamically takes the class

		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file); // Captured the data of properties file

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();

			// OS
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);

			} else if (os.equalsIgnoreCase("mac")) 
			{
				capabilities.setPlatform(Platform.MAC);
			} else if (os.equalsIgnoreCase("Linux")) 
			{
				capabilities.setPlatform(Platform.LINUX);
			} else 
			{
				System.out.println("No matching OS");
				return;
			}

			// Browser

			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser");
				return;
			}

			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		}

		// If the execution env is local (Decide form config.propety file
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;

			default:
				System.out.println("Invalid Browser name");
				return;
			}

		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("AppUrl")); // Reading url from the properties file.
		driver.manage().window().maximize();

	}

	@AfterClass(groups = { "Sanity", "Master", "Regression", "DataDriven" })
	public void tearDown() {
		driver.quit();
	}

	// We will now use RandomStringUtils class to create random data

	public String randomString(int num) {
		String generatedString = RandomStringGenerator.randomAlphabeticGen(num);
		return generatedString;
	}

	public String randomNumber(int num) {
		String generatedNumber = RandomStringGenerator.randomNumberGen(num);
		return generatedNumber;
	}

	public String randomAlphanumeric() {
		String generatedString = RandomStringGenerator.randomAlphabeticGen(3);
		String generatedNumber = RandomStringGenerator.randomNumberGen(4);

		return (generatedString + "@#" + generatedNumber);
	}

	public String captureScreen(String testname) { // A method that's failing will pass it's name to this method

		// We will save the ss with timestamp so that it doesn't get replace
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		// We use the interface to take the screenshot
		TakesScreenshot takeSS = (TakesScreenshot) driver;
		File sourceFile = takeSS.getScreenshotAs(OutputType.FILE); // Then we get the source file

		// We will copy the source file to target folder with timestamp & format of img
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testname + "_" + timeStamp
				+ ".png";
		File targetFile = new File(targetFilePath);

		// And using below command the source file will get copied into target file
		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}
