package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.HotelSelection;
import TestBase.BaseClass;
import userDefinedLibraries.ExcelUtil;

public class TC003_HotelSelectionTest extends BaseClass {
    // Specifies the path to the Excel file for output.
    String excelFilePath = "data/ExcelOutputData1.xlsx";

    // Test method annotated with @Test, belonging to the "Regression" and "Master" groups.
    @Test(groups = {"Regression", "Master"})
    public void Hotel_Selection_Test() {
        // Logs the start of the test case.
        logger.info("******TC003_HOTEL_SELECTION********");
        
        // Creates instances of the page objects.
        HomePage hp = new HomePage(driver);
        HotelSelection hs = new HotelSelection(driver);
        
        try {
            // Logs and handles the popup on the home page.
            logger.info("Handling popup");
            hp.handlePopup();
            
            // Logs and selects the Hotel option on the home page.
            logger.info("Selecting Hotel option");
            hp.selectingHotel();
            
            // Logs and selects the number of guests.
            logger.info("Selecting number of guests");
            hs.guestSelect();
            
            // Logs and selects the adult option, then gets the adult count.
            logger.info("Selecting adult option");
            int adultCount = hs.getAdultOption(); // Get the adult count

            // Prepares headers and data for Excel output.
            String[] countHeaders = {"Adults Count"};
            Object[][] countData = new Object[1][1]; // Only one adult count

            countData[0][0] = adultCount; // Adult Count

            // Writes the adult count data to the Excel file in the "AdultOption" sheet.
            ExcelUtil excelUtil = new ExcelUtil(excelFilePath);
            excelUtil.writeData("AdultOption", countHeaders, countData);
            excelUtil.save(excelFilePath);
            excelUtil.close();
            
            // Logs and applies the guest selection.
            logger.info("Applying guest selection");
            hs.apply();
            
            // Logs the successful completion of the hotel booking test.
            logger.info("Hotel booking test completed successfully");
        
        } catch (Exception e) {
            // Prints the exception message to the console.
            System.out.println("Exception occurred at " + e.getMessage());
            // Logs the failure of the test case.
            logger.error("Test case 003 failed");
            logger.debug("Test case 003 failed");
            // Fails the test case.
            Assert.fail();
        }
    }
}

