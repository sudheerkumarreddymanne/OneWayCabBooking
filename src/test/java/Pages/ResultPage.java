package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResultPage extends BasePage {
    // Locates the sort dropdown element using its XPath.
    @FindBy(xpath = "//span[@class='latoRegular']")
    WebElement Sort;
    
    // Locates the first sort option element using its XPath.
    @FindBy(xpath = "//div[@class='sortOptionUnit'][1]")
    WebElement SortOption;
    
    // Locates the car option element using its XPath.
    @FindBy(xpath = "//div[@class='filterPanel']//div[2]//div[3]/span")
    WebElement CarOption;
    
    // Locates the car type options elements using their XPath.
    @FindBy(xpath = "//div[@data-testid='filter_selection']")
    List<WebElement> carTypeOptions;
    
    // Locates the car name elements using their XPath.
    @FindBy(xpath = "//div[@class='makeFlex end']/span[1]")
    List<WebElement> CarName;
    
    // Locates the SUV checkbox element using its XPath.
    @FindBy(xpath = "//div[@class='appendBottom30']//label[text()='SUV']/preceding-sibling::input")
    WebElement checkbox;
    
    // Locates the car price elements using their XPath.
    @FindBy(xpath = "//p[@class='font28 latoBlack blackText ']")
    List<WebElement> CarPrice;
    
    // Constructor for the ResultPage class, calling the constructor of the BasePage class.
    public ResultPage(WebDriver driver) {
        super(driver);
    }
    
    // Method to interact with the sort dropdown.
    public void sortDropDown() {
        // Waits until the sort dropdown is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(Sort));
        Sort.click();
        // Clicks the first sort option.
        SortOption.click();
    }
    
    // Method to select a car type.
    public void selectCarType() {
        // Waits until the car option is visible and clicks it.
        wait.until(ExpectedConditions.visibilityOf(CarOption));
        CarOption.click();
    }

    // Method to get the details of the first car.
    public String[] getFirstCarDetails() {
        System.out.println("***********************************************");
        System.out.println("Car Details :- ");
        System.out.println("***********************************************");
        
        // Checks if car names or car prices are empty.
        if (CarName.isEmpty() || CarPrice.isEmpty()) {
            System.out.println("No car details available.");
            return null;
        }

        // Gets the first car's name and price.
        String firstCarName = CarName.get(0).getText();
        String firstCarPrice = CarPrice.get(0).getText();

        System.out.println("Cheapest Car: " + firstCarName + ", Car Price: " + firstCarPrice);

        // Returns the first car's details.
        return new String[]{firstCarName, firstCarPrice};
    }
}
