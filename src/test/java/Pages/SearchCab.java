package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchCab extends BasePage {
    // Locates the one-way button element using its XPath.
    @FindBy(xpath = "//li[@class='b2c_selected selectedText']")
    WebElement oneWayButton;
    
    // Locates the from city element using its ID.
    @FindBy(id = "fromCity")
    WebElement fromCity;
    
    // Locates the from city input field using its XPath.
    @FindBy(xpath = "//input[@placeholder='From']")
    WebElement fromCityInput;
    
    // Locates the to city input field using its XPath.
    @FindBy(xpath = "//input[@title='To']")
    WebElement toCityInput;
    
    // Locates the departure date label using its XPath.
    @FindBy(xpath = "//label[@for='departure']")
    WebElement Departure;
    
    // Locates the month element in the date picker using its XPath.
    @FindBy(xpath = "//div[@class='DayPicker-Months']/div[2]/div[@class='DayPicker-Caption']/div")
    WebElement Month;
    
    // Locates the next button in the date picker using its XPath.
    @FindBy(xpath = "//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")
    WebElement NextButton;
    
    // Locates the departure date element using its XPath.
    @FindBy(xpath = "//div[@class='DayPicker-Months']/div[2]/div[3]/div[4]/div[2]")
    WebElement DepartDate;
    
    // Locates the departure time label using its XPath.
    @FindBy(xpath = "//label[@for='pickupTime']")
    WebElement DepartTime;
    
    // Locates the pickup time hour element using its XPath.
    @FindBy(xpath = "//ul[@class='newTimeSlotHrUl']/li[7]")
    WebElement PickupTimeHour;
    
    // Locates the pickup time minutes element using its XPath.
    @FindBy(xpath = "//ul[@class='newTimeSlotMinUl']/li[7]")
    WebElement PickupTimeMinutes;
    
    // Locates the pickup time meridian element using its XPath.
    @FindBy(xpath = "//ul[@class='newTimeSlotMerUl']/li[1]")
    WebElement PickupTimeMeridian;
    
    // Locates the apply button element using its XPath.
    @FindBy(xpath = "//span[@class='applyBtnText']")
    WebElement ApplyButton;
    
    // Locates the search button element using its XPath.
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/p[1]/a[1]")
    WebElement searchButton;
    
    // Constructor for the SearchCab class, calling the constructor of the BasePage class.
    public SearchCab(WebDriver driver) {
        super(driver);
    }
    
    // Method to select the one-way option.
    public void oneWaySelection() {
        // Waits until the one-way button is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(oneWayButton));
        oneWayButton.click();
    }
    
    // Method to enter zthe from location.
    public void enterFromLocation() {
        // Clicks the from city element.
        fromCity.click();
        // Waits until the from city input field is visible.
        wait.until(ExpectedConditions.visibilityOf(fromCityInput));
        // Enters the from city value from properties.
        fromCityInput.sendKeys(propReader.getProperty("fromCity"));
        // Simulates arrow down and enter key presses to select the city.
        fromCityInput.sendKeys(Keys.ARROW_DOWN);
        fromCityInput.sendKeys(Keys.ARROW_DOWN);
        fromCityInput.sendKeys(Keys.ENTER);
    }
    
    // Method to enter the to location.
    public void enterToLocation() {
        // Waits until the to city input field is visible.
        wait.until(ExpectedConditions.visibilityOf(toCityInput));
        // Enters the to city value from properties.
        toCityInput.sendKeys(propReader.getProperty("toCity"));
        // Simulates a pause and arrow down and enter key presses to select the city.
        actions.pause(Duration.ofSeconds(2))
               .sendKeys(Keys.ARROW_DOWN)
               .sendKeys(Keys.ENTER).build().perform();
    }
    
    // Method to select the departure date.
    public void departureDate() {
        // Waits until the departure date label is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(Departure));
        Departure.click();
        // Loops until the desired month is found.
        while (!Month.getText().contains(propReader.getProperty("month"))) {
            NextButton.click();
        }
        // Waits until the departure date element is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(DepartDate));
        DepartDate.click();
    }
    
    // Method to select the pickup time.
    public void pickUpTime() {
        // Retrieves the hours, minutes, and meridian values from properties.
        String hours = propReader.getProperty("hours");
        String minutes = propReader.getProperty("minutes");
        String meridian = propReader.getProperty("meridian");
        
        // Clicks the departure time label.
        DepartTime.click();
        
        // Selects the hour.
        wait.until(ExpectedConditions.visibilityOf(PickupTimeHour));
        List<WebElement> hourOptions = driver.findElements(By.xpath("//ul[@class='newTimeSlotHrUl']/li"));
        for (WebElement hourElement : hourOptions) {
            if (hourElement.getText().equals(hours)) {
                hourElement.click();
                break;
            }
        }
        
        // Selects the minute.
        wait.until(ExpectedConditions.visibilityOf(PickupTimeMinutes));
        List<WebElement> minuteOptions = driver.findElements(By.xpath("//ul[@class='newTimeSlotMinUl']/li"));
        for (WebElement minuteElement : minuteOptions) {
            if (minuteElement.getText().equals(minutes)) {
                minuteElement.click();
                break;
            }
        }
        
        // Selects the meridian.
        wait.until(ExpectedConditions.visibilityOf(PickupTimeMeridian));
        List<WebElement> meridianOptions = driver.findElements(By.xpath("//ul[@class='newTimeSlotMerUl']/li"));
        for (WebElement meridianElement : meridianOptions) {
            if (meridianElement.getText().equals(meridian)) {
                meridianElement.click();
                break;
            }
        }
        
        // Clicks the apply button.
        ApplyButton.click();
    }
    
    // Method to search for cabs.
    public void searchCabs() {
        // Clicks the search button.
        searchButton.click();
    }
}
