package userDefinedLibraries;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtentReportManager implements ITestListener {
    // Declares an ExtentSparkReporter instance to configure the report.
    public ExtentSparkReporter sparkReporter;
    
    // Declares an ExtentReports instance to manage the report.
    public ExtentReports extent;
    
    // Declares an ExtentTest instance to log test results.
    public ExtentTest test;

    // Declares a string to hold the report name.
    String repName;

    // Method to initialize the report before any test methods are run.
    public void onStart(ITestContext testContext) {
        // Gets the current timestamp in the specified format.
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        // Constructs the report name using the timestamp.
        repName = "Test-Report-" + timeStamp + ".html";
        // Initializes the ExtentSparkReporter with the report file path.
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        // Configures the report title.
        sparkReporter.config().setDocumentTitle("MakeMyTrip Automation Report");
        // Configures the report name.
        sparkReporter.config().setReportName("MakeMyTrip Functional Testing");
        // Sets the theme of the report to dark.
        sparkReporter.config().setTheme(Theme.DARK);
        
        // Initializes the ExtentReports instance and attaches the reporter.
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        // Sets system information for the report.
        extent.setSystemInfo("Application", "MakeMyTrip");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        
        // Gets the operating system parameter from the test context and sets it in the report.
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);
        
        // Gets the browser parameter from the test context and sets it in the report.
        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);
        
        // Gets the included groups from the test context and sets them in the report if not empty.
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    // Method to log test success.
    public void onTestSuccess(ITestResult result) {
        // Creates a test entry in the report for the test class.
        test = extent.createTest(result.getTestClass().getName());
        // Assigns the test method's groups to the test entry.
        test.assignCategory(result.getMethod().getGroups());
        // Logs the test success status.
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    // Method to log test failure.
    public void onTestFailure(ITestResult result) {
        // Creates a test entry in the report for the test class.
        test = extent.createTest(result.getTestClass().getName());
        // Assigns the test method's groups to the test entry.
        test.assignCategory(result.getMethod().getGroups());
        
        // Logs the test failure status.
        test.log(Status.FAIL, result.getName() + " got failed");
        // Logs the failure message.
        test.log(Status.INFO, result.getThrowable().getMessage());
        
        try {
            // Captures a screenshot of the failure and adds it to the report.
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    // Method to log test skip.
    public void onTestSkipped(ITestResult result) {
        // Creates a test entry in the report for the test class.
        test = extent.createTest(result.getTestClass().getName());
        // Assigns the test method's groups to the test entry.
        test.assignCategory(result.getMethod().getGroups());
        // Logs the test skip status.
        test.log(Status.SKIP, result.getName() + " got skipped");
        // Logs the skip message.
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    // Method to finalize the report after all test methods are run.
    public void onFinish(ITestContext testContext) {
        // Flushes the report to write all the information to the file.
        extent.flush();
        
        // Constructs the path of the extent report.
        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);
        
        try {
            // Opens the extent report in the default browser.
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
