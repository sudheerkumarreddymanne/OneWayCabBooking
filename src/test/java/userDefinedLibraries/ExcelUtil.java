package userDefinedLibraries;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ExcelUtil {
    // Declares a Workbook instance to interact with the Excel file.
    private Workbook workbook;
 
    // Constructor for the ExcelUtil class, initializing the Workbook instance.
    public ExcelUtil(String excelFilePath) throws IOException {
        // Creates a FileInputStream to read the Excel file.
        FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
        // Initializes the Workbook instance with the FileInputStream.
        this.workbook = new XSSFWorkbook(fileInputStream);
    }
 
    // Method to get data from a specific cell.
    public String getCellData(String sheetName, int rowNum, int colNum) {
        // Gets the sheet by name.
        Sheet sheet = workbook.getSheet(sheetName);
        // Returns an empty string if the sheet is null.
        if (sheet == null) return "";
 
        // Gets the row by number.
        Row row = sheet.getRow(rowNum);
        // Returns an empty string if the row is null.
        if (row == null) return "";
 
        // Gets the cell by column number.
        Cell cell = row.getCell(colNum);
        // Returns an empty string if the cell is null.
        if (cell == null) return "";
 
        // Switch statement to handle different cell types.
        switch (cell.getCellType()) {
            case STRING: 
                // Returns the string value of the cell.
                return cell.getStringCellValue();
            case NUMERIC: 
                // Returns the numeric value of the cell as a string.
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN: 
                // Returns the boolean value of the cell as a string.
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK: 
                // Returns an empty string if the cell is blank.
                return "";
            default: 
                // Returns an empty string for any other cell type.
                return "";
        }
    }
 
    // Method to get the number of rows in a sheet.
    public int getRowCount(String sheetName) {
        // Gets the sheet by name.
        Sheet sheet = workbook.getSheet(sheetName);
        // Returns the number of physical rows in the sheet, or 0 if the sheet is null.
        return (sheet != null) ? sheet.getPhysicalNumberOfRows() : 0;
    }
 
    // Method to write data to a sheet.
    public void writeData(String sheetName, String[] headers, Object[][] data) throws IOException {
        // Gets the sheet by name, or creates it if it doesn't exist.
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        // Gets the header row, or creates it if it doesn't exist.
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            headerRow = sheet.createRow(0);
        }
        // Writes the headers to the first row.
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.getCell(i);
            if (cell == null) {
                cell = headerRow.createCell(i);
            }
            cell.setCellValue(headers[i]);
        }
 
        // Writes the data to the sheet.
        for (int i = 0; i < data.length; i++) {
            // Gets the row, or creates it if it doesn't exist.
            Row row = sheet.getRow(i + 1); 
            if (row == null) {
                row = sheet.createRow(i + 1);
            }
            // Writes each cell in the row.
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                // Sets the cell value based on the data type.
                if (data[i][j] instanceof String) {
                    cell.setCellValue((String) data[i][j]);
                } else if (data[i][j] instanceof Number) {
                    cell.setCellValue(((Number) data[i][j]).doubleValue());
                } else {
                    cell.setCellValue(data[i][j].toString());
                }
            }
        }
    }
 
    // Method to save the workbook to a file.
    public void save(String excelFilePath) throws IOException {
        // Creates a FileOutputStream to write the workbook to a file.
        try (FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath))) {
            workbook.write(outputStream);
        }
    }
 
    // Method to close the workbook and release resources.
    public void close() throws IOException {
        workbook.close();
    }
}
 