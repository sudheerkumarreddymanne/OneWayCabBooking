package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    // Locates the close popup button element using its XPath.
    @FindBy(xpath = "//span[@class='commonModal__close']")
    WebElement closePopup;
    
    // Locates the cab option element using its XPath.
    @FindBy(xpath = "//li[@class='menu_Cabs']")
    WebElement CabOption;
    
    // Locates the gift card option element using its XPath.
    @FindBy(xpath = "//div[@class='choosFrom__wrap']/ul/li[5]")
    WebElement GiftCard;
    
    // Locates the hotel option element using its XPath.
    @FindBy(xpath = "//li[@class='menu_Hotels']")
    WebElement HotelOption;
    
    // Constructor for the HomePage class, calling the constructor of the BasePage class.
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    // Method to handle the popup.
    public void handlePopup() {
        // Waits until the close popup button is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(closePopup));        
        closePopup.click();
    }
    
    // Method to get the current page title.
    public String getcurrenttitle() {
        // Waits until the gift card option is visible.
        wait.until(ExpectedConditions.visibilityOf(GiftCard));
        // Retrieves the current page title.
        String currenttitle = driver.getTitle();
        return currenttitle;
    }

    // Method to select the cab option.
    public void selectingCabs() {
        // Waits until the cab option is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(CabOption));
        CabOption.click();
    }
    
    // Method to select the gift card option.
    public void selectingGiftCard() {
        // Scrolls the window by 400 pixels vertically.
        js.executeScript("window.scrollBy(0,400)");
        // Waits until the gift card option is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(GiftCard));
        GiftCard.click();
    }
    
    // Method to select the hotel option.
    public void selectingHotel() {
        // Clicks the hotel option.
        HotelOption.click();
    }
}
