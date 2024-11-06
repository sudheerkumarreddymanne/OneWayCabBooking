package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HotelSelection extends BasePage {
    // Constructor for the HotelSelection class, calling the constructor of the BasePage class.
    public HotelSelection(WebDriver driver) {
        super(driver);
    }
    
    // Locates the guest selection element using its XPath.
    @FindBy(xpath = "//div[@class='hsw_inputBox roomGuests']")
    WebElement guestSelection;
    
    // Locates the adults option element using its XPath.
    @FindBy(xpath = "//div[@class='rmsGst__row'][2]/div[2]")
    WebElement adultsOption;
    
    // Locates the adult dropdown elements using their XPath.
    @FindBy(xpath = "//div[@class='rmsGst__row'][2]/div[2]/ul/li")
    List<WebElement> adultDropdown;
    
    // Locates the first adult guest option element using its XPath.
    @FindBy(xpath = "//ul[@class='gstSlct__list']/li[1]")
    WebElement adultsGuest;
    
    // Locates the apply button element using its XPath.
    @FindBy(xpath = "//div[@class='rmsGst__footer']/button")
    WebElement applyBtn;
    
    // Method to select the guest option.
    public void guestSelect() {
        // Waits until the guest selection element is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(guestSelection));
        guestSelection.click();
    }
    
    // Method to get the adult option count and select an adult guest.
    public int getAdultOption() {
        // Waits until the adults option element is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(adultsOption));
        adultsOption.click();
        
        // Waits until all adult dropdown elements are visible.
        wait.until(ExpectedConditions.visibilityOfAllElements(adultDropdown));
        //System.out.println("************************************************");
        // Gets the size of the adult dropdown list.
        int size = adultDropdown.size();
        System.out.println("Adults Count :-  " + size);
        System.out.println("***********************************************");
        // Scrolls the dropdown to the bottom.
       // js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", adultDropdown);
        
        // Clicks the first adult guest option.
        adultsGuest.click();
        return size;
    }
    
    // Method to apply the selected options.
    public void apply() {
        // Moves to the apply button and clicks it.
        actions.moveToElement(applyBtn).perform();
        wait.until(ExpectedConditions.visibilityOf(applyBtn));
        applyBtn.click();
    }
}
