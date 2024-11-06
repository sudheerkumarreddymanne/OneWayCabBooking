package Tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.HomePage;
import TestBase.BaseClass;


public class TC004_HomePageVerification extends BaseClass {
    
    // Test method annotated with @Test, belonging to the "Smoke" and "Master" groups.
    @Test(groups = {"Smoke", "Master"})
    public void Home_Page_Verification() {
        
        // Logs the start of the test case.
        logger.info("******TC004_HOME_PAGE_VERIFICATION********");
        
        // Creates an instance of the HomePage page object.
        HomePage hp = new HomePage(driver);
        
        try {
            // Logs and handles the popup on the home page.
            logger.info("Handling popup");
            hp.handlePopup();
            
            // Gets the current title of the home page.
            String currentTitle = hp.getcurrenttitle();
            
            // Expected title of the home page.
            String expectedTitle = "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";
            
            // Asserts that the current title matches the expected title.
            assertTrue(currentTitle.equals(expectedTitle));
            
            // Logs the successful verification of the home page.
            logger.info("Home Page is verified");
            
        } catch (Exception e) {
            // Prints the exception message to the console.
            System.out.println("Home page Verification Test completed: " + e.getMessage());
            
            // Logs the failure of the test case.
            logger.error("Test case 004 failed");
            logger.debug("Test case 004 failed");
            
            // Fails the test case.
            Assert.fail();
        } 
    }
}
