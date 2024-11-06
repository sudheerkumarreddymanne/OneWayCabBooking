package TestBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import userDefinedLibraries.PropertiesRead;


public class BaseClass {
    // Creates an instance of PropertiesRead to read properties files.
    PropertiesRead prop = new PropertiesRead();
    
    // Declares a static WebDriver instance to interact with the browser.
    public WebDriver driver;
    
    // Declares a Logger instance for logging purposes.
    public Logger logger;
    
    // Method annotated witzh @BeforeClass to run before any test methods in the class.
    @BeforeClass(groups = {"Regression", "Master", "Smoke"})
    @Parameters({"os", "browser"})
    public void startTest(String os, String browser) {
        // Initializes the logger for the current class.
        logger = LogManager.getLogger(this.getClass());
        
        // Switch statement to initialize the WebDriver based on the browser parameter.
        switch (browser.toLowerCase()) {
            case "chrome":
                // Initializes the ChromeDriver if the browser is Chrome.
                driver = new ChromeDriver();
                break;
            case "edge":
                // Initializes the EdgeDriver if the browser is Edge.
                driver = new EdgeDriver();
                break;
            default:
                // Prints a message if no valid browser is detected.
                System.out.println("No browser detected");
        }
        
        // Deletes all cookies in the browser.
        driver.manage().deleteAllCookies();
        // Maximizes the browser window.
        driver.manage().window().maximize();
        // Navigates to the URL specified in the properties file.
        driver.get(prop.getProperty("url"));
    }
    
    // Method annotated with @AfterClass to run after all test methods in the class.
    @AfterClass(groups = {"Regression", "Master", "Smoke"})
    public void teardown() {
        // Quits the WebDriver, closing all associated windows.
        driver.quit();
    }
    
    // Method to capture a screenshot.
    public String captureScreen(String tname) throws IOException {
        // Gets the current timestamp in the specified format.
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        
        // Casts the WebDriver to TakesScreenshot.
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        // Captures the screenshot and stores it in a file.
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        
        // Constructs the target file path using the test name and timestamp.
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        
        // Renames the source file to the target file.
        sourceFile.renameTo(targetFile);
        
        // Returns the path of the target file.
        return targetFilePath;
    }
}

