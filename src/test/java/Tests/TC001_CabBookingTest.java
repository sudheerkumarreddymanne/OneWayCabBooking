package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.ResultPage;
import Pages.SearchCab;
import TestBase.BaseClass;
import userDefinedLibraries.ExcelUtil;

public class TC001_CabBookingTest extends BaseClass {
    // Test method annotated with @Test, belonging to the "Regression" and "Master" groups.
    @Test(groups = {"Regression", "Master"})
    public void Booking_Cab() {
        // Logs the start of the test case.
        logger.info("******TC001_CAB_BOOKING********");
        
        // Creates instances of the page objects.
        HomePage hp = new HomePage(driver);
        SearchCab cb = new SearchCab(driver);
        ResultPage rp = new ResultPage(driver);
        
        // Specifies the path to the Excel file for output.
        String excelFilePath = "data/ExcelOutputData1.xlsx";
        
        try {
            // Logs and handles the popup on the home page.
            logger.info("Handling popup");
            hp.handlePopup();
            
            // Logs and selects the Cabs option on the home page.
            logger.info("Selecting Cabs option");
            hp.selectingCabs();
            
            // Logs and selects the one-way trip option.
            logger.info("Selecting one-way trip");
            cb.oneWaySelection();
            
            // Logs and enters the 'From' location.
            logger.info("Entering 'From' location");
            cb.enterFromLocation();
            
            // Logs and enters the 'To' location.
            logger.info("Entering 'To' location");
            cb.enterToLocation();
            
            // Logs and selects the departure date.
            logger.info("Selecting departure date");
            cb.departureDate();
            
            // Logs and selects the pick-up time.
            logger.info("Selecting pick-up time");
            cb.pickUpTime();
            
            // Logs and searches for cabs.
            logger.info("Searching for cabs");
            cb.searchCabs();
            
            // Logs and sorts the results.
            logger.info("Sorting results");
            rp.sortDropDown();
            
            // Logs and selects the car type.
            logger.info("Selecting car type");
            rp.selectCarType();
            
            // Logs and views the car details.
            logger.info("Viewing car details");
            // rp.carDetails(); // This line is commented out.
            
            // Gets the details of the first car.
            String[] carDetails = rp.getFirstCarDetails();
            
            if (carDetails != null && carDetails.length == 2) {
                // Prepares headers and data for Excel output.
                String[] headers = {"Car Name", "Car Price"};
                Object[][] data = new Object[1][2]; // Only one car
                
                data[0][0] = carDetails[0]; // Car Name
                data[0][1] = carDetails[1]; // Car Price
                
                // Writes data to the Excel file.
                ExcelUtil excelUtil = new ExcelUtil(excelFilePath);
                excelUtil.writeData("CabBooking", headers, data);
                excelUtil.save(excelFilePath);
                excelUtil.close();
            } else {
                // Logs a warning if no car details are available.
                logger.warn("No car details available to write to Excel.");
            }
            // Logs the successful completion of the cab booking test.
            logger.info("Cab booking test completed successfully");

            // Logs the end of the test case.
            logger.info("******TC001_COMPLETED********");
            
        } catch (Exception e) {
            // Prints the exception message to the console.
            System.out.println("Cab Booking Test completed: " + e.getMessage());
            // Logs the failure of the test case.
            logger.error("Test case 001 failed");
            logger.debug("Test case 001 failed");
            // Fails the test case.
            Assert.fail();
        }
    }
}

	
