package Pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import userDefinedLibraries.PropertiesRead;

public class BasePage {
    // Declares a WebDriver instance variable to interact with the browser.
    WebDriver driver;
    
    // Declares a WebDriverWait instance variable for explicit waits.
    WebDriverWait wait;
    
    // Declares an Actions instance variable to handle complex user gestures.
    Actions actions;
    
    // Declares a JavascriptExecutor instance variable to execute JavaScript.
    JavascriptExecutor js;
    
    // Declares a protected PropertiesRead instance variable for reading properties files.
    protected PropertiesRead propReader;
    
    // Constructor for the BasePage class, initializing the WebDriver instance.
    public BasePage(WebDriver driver) {
        // Assigns the passed WebDriver instance to the class's driver variable.
        this.driver = driver;
        
        // Initializes the Actions instance with the WebDriver instance.
        this.actions = new Actions(driver);
        
        // Initializes the WebDriverWait instance with the WebDriver instance and a timeout of 20 seconds.
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        // Initializes the PropertiesRead instance.
        this.propReader = new PropertiesRead();
        
        // Casts the WebDriver instance to JavascriptExecutor and assigns it to the js variable.
        this.js = (JavascriptExecutor) driver;
        
        // Initializes the web elements in the page using PageFactory.
        PageFactory.initElements(driver, this);
    }
}

