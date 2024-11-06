package userDefinedLibraries;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
public class ExcelDataProvider {
    
    // DataProvider annotation to provide data for test methods.
    @DataProvider(name = "giftCardData")
    public Object[][] getGiftCardData() throws Exception {
        // Path to the Excel file.
        String excelFilePath = "data/GiftCardInput.xlsx"; 
        // Name of the sheet in the Excel file.
        String sheetName = "Sheet1"; 
        // Creates an instance of ExcelUtil to interact with the Excel file.
        ExcelUtil excelUtil = new ExcelUtil(excelFilePath);
        
        // Gets the number of rows in the specified sheet.
        int rowCount = excelUtil.getRowCount(sheetName);
        
        // Use a dynamic list to hold non-empty rows.
        List<Object[]> dataList = new ArrayList<>();

        // Loop through each row, starting from the second row (index 1).
        for (int i = 1; i < rowCount; i++) {
            // Check if the first cell in the row is not empty (or you can choose any cell to validate).
            String firstCellData = excelUtil.getCellData(sheetName, i, 0);
            if (!firstCellData.isEmpty()) { // Only process non-empty rows.
                // Create an array to hold the data for each row, assuming 6 columns.
                Object[] rowData = new Object[6];
                // Loop through each column in the row.
                for (int j = 0; j < 6; j++) {
                    // Get the data from each cell and store it in the rowData array.
                    rowData[j] = excelUtil.getCellData(sheetName, i, j);
                }
                // Add the rowData array to the dataList.
                dataList.add(rowData);
            }
        }

        // Convert the list to a 2D array for the DataProvider.
        Object[][] data = new Object[dataList.size()][6];
        for (int i = 0; i < dataList.size(); i++) {
            // Copy each row from the dataList to the data array.
            data[i] = dataList.get(i);
        }

        // Close the ExcelUtil instance to release resources.
        excelUtil.close();
        // Return the 2D array of data.
        return data;
    }
}
