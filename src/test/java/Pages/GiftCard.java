package Pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GiftCard extends BasePage {
    // Constructor for the GiftCard class, calling the constructor of the BasePage class.
    public GiftCard(WebDriver driver) {
        super(driver);
    }
    
    // Locates the wedding card element using its XPath.
    @FindBy(xpath = "//div[@class='all__card__wrap'][1]/ul/li[2]")
    WebElement weddingCard;
    
    // Locates the emazil button element using its XPath.
    @FindBy(xpath = "//div[@class='deliver__wrap']/ul/li[2]")
    WebElement emailBtn;
    
    // Locates the recipient name input field using its XPath.
    @FindBy(xpath ="//input[@name='name']")
    WebElement recipientName;
    
    // Locates the phone number input field using its XPath.
    @FindBy(xpath ="//input[@name='mobileNo']")
    WebElement phoneNum;
    
    // Locates the email input field using its XPath.
    @FindBy(xpath ="//input[@name='emailId']")
    WebElement email;
    
    // Locates the sender name input field using its XPath.
    @FindBy(xpath ="//input[@name='senderName']")
    WebElement senderName;
    
    // Locates the sender mobile number input field using its XPath.
    @FindBy(xpath ="//input[@name='senderMobileNo']")
    WebElement senderMobileNo;
    
    // Locates the sender email input field using its XPath.
    @FindBy(xpath ="//input[@name='senderEmailId']")
    WebElement senderEmailId;
    
    // Locates the buy button element using its XPath.
    @FindBy(xpath ="//div[@class='booking__details__wrap']/div[3]/button")
    WebElement buyBtn;
    
    // Locates the error text element using its XPath.
    @FindBy(xpath ="//p[@class='red-text font11 append-top5']")
    WebElement errorText;
    
    // Method to select a gift card.
    public void GiftSelection() {
        // Stores the current window handle.
        String firstTab = driver.getWindowHandle();
        // Gets all window handles.
        Set<String> allTabs = driver.getWindowHandles();
        
        // Switches to the new tab.
        for(String tab : allTabs) {
            if(!tab.equals(firstTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        
        // Scrolls the window by 400 pixels vertically.
        js.executeScript("window.scrollBy(0,400)");
        
        // Waits until the wedding card element is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(weddingCard));
        weddingCard.click();
    }

    // Method to select the email button.
    public void selectingEmailBtn() {
        // Waits until the email button is visible.
        wait.until(ExpectedConditions.visibilityOf(emailBtn));
        // Moves to the email button and clicks it.
        actions.moveToElement(emailBtn).perform();
        emailBtn.click();
    }
    
    // Method to enter delivery details.
    public void enterDeliveryDetails(String recipient, String phoneNumber, String emailAddress, String sender, String senderMobile, String senderEmail) {
        // Scrolls the window by 700 pixels vertically.
        js.executeScript("window.scrollBy(0,700)"); 
        
        // Waits until the recipient name input field is visible and enters the recipient's name.
        wait.until(ExpectedConditions.visibilityOf(recipientName)).sendKeys(recipient);
        // Enters the phone number.
        phoneNum.sendKeys(phoneNumber);
        // Enters the email address.
        email.sendKeys(emailAddress);
        // Enters the sender's name.
        senderName.sendKeys(sender);
        // Enters the sender's mobile number.
        senderMobileNo.sendKeys(senderMobile);
        // Enters the sender's email address.
        senderEmailId.sendKeys(senderEmail);
    }
    
    // Method to display an error message.
    public String DisplayMessage() {
        // Waits until the buy button is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(buyBtn));
        buyBtn.click();

        // Waits until the error text is visible and retrieves its text.
        wait.until(ExpectedConditions.visibilityOf(errorText));
        String t = errorText.getText();
        // Prints the error message to the console.
        System.out.println("***********************************************");
        System.out.println("Error Message :- " + t);
        System.out.println("***********************************************");
        // Returns the error message text.
        return t;
    }
}
