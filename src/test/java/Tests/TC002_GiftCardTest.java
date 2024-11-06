package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.GiftCard;
import Pages.HomePage;
import TestBase.BaseClass;
import userDefinedLibraries.ExcelDataProvider;
import userDefinedLibraries.ExcelUtil;

public class TC002_GiftCardTest extends BaseClass {
    // Specifies the path to the Excel file for output.
    String excelFilePath = "data/ExcelOutputData1.xlsx";

    // Test method annotated with @Test, using a data provider and belonging to the "Regression" and "Master" groups.
    @Test(priority = 2, dataProvider = "giftCardData", dataProviderClass = ExcelDataProvider.class, groups = {"Regression", "Master"})
    public void Gift_Card_Test(String recipientName, String phoneNumber, String emailAddress, String senderName, String senderMobile, String senderEmail) {
        // Logs the start of the test case.
        logger.info("******TC002_GIFT_CARD_SELECTION********");
        
        // Creates instances of the page objects.
        HomePage hp = new HomePage(driver);
        GiftCard gc = new GiftCard(driver);
        
        try {
            // Logs and handles the popup on the home page.
            logger.info("Handling popup");
            hp.handlePopup();
            
            // Logs and selects the Gift Card option on the home page.
            logger.info("Selecting Gift Card option");
            hp.selectingGiftCard();
            
            // Logs and selects the gift card.
            logger.info("Selecting gift card");
            gc.GiftSelection();
            
            // Logs and selects the email button for delivery.
            logger.info("Selecting email button for delivery");
            gc.selectingEmailBtn();
            
            // Logs and enters the delivery details.
            logger.info("Entering delivery details");
            gc.enterDeliveryDetails(recipientName, phoneNumber, emailAddress, senderName, senderMobile, senderEmail);
            
            // Logs and displays the error message.
            logger.info("Displaying message");
            String errorMessage = gc.DisplayMessage(); 

            // Prepares headers and data for Excel output.
            String[] errorHeaders = {"Error Message"};
            Object[][] errorData = new Object[1][1]; 

            errorData[0][0] = errorMessage; 

            // Writes the error message to the Excel file.
            ExcelUtil excelUtil = new ExcelUtil(excelFilePath);
            excelUtil.writeData("GiftCard", errorHeaders, errorData);
            excelUtil.save(excelFilePath);
            excelUtil.close();
            
            // Logs the successful completion of the gift card purchase test.
            logger.info("Gift card purchase test completed successfully");
        
        } catch (Exception e) {
            // Prints the exception message to the console.
            System.out.println("Exception occurred at " + e.getMessage());
            // Logs the failure of the test case.
            logger.error("Gift Card Selection Test Failed");
            logger.debug("Gift Card Selection Test Failed");
            // Fails the test case.
            Assert.fail();
        }
    }
}

	
