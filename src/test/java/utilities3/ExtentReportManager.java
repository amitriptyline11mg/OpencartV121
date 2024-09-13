package utilities3;

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

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports report;
    public ExtentTest test;

    String reportName;

    public void onStart(ITestContext testContext) {
        try {
            // Create the reports directory if it does not exist
            File reportDir = new File("reports");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            // Time Stamp
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            reportName = "Test-Report-" + timeStamp + ".html";

            sparkReporter = new ExtentSparkReporter("reports/" + reportName);

            sparkReporter.config().setDocumentTitle("Opencart Automation report");
            sparkReporter.config().setReportName("Opencart Functional Testing");
            sparkReporter.config().setTheme(Theme.STANDARD);

            report = new ExtentReports();
            report.attachReporter(sparkReporter);

            report.setSystemInfo("Application", "OpenCart");
            report.setSystemInfo("Module", "Admin");
            report.setSystemInfo("Sub-Module", "Customers");
            report.setSystemInfo("User name", System.getProperty("user.name"));
            report.setSystemInfo("Environment", "QA");

            String os = testContext.getCurrentXmlTest().getParameter("os");
            report.setSystemInfo("Operating System", os);

            String browser = testContext.getCurrentXmlTest().getParameter("browser");
            report.setSystemInfo("Browser", browser);

            List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
            if (!includedGroups.isEmpty()) {
                report.setSystemInfo("Groups", includedGroups.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred during Extent Report setup.");
        }
    }

    public void onTestSuccess(ITestResult result) {
        try {
            test = report.createTest(result.getTestClass().getName());
            test.assignCategory(result.getMethod().getGroups());
            test.log(Status.PASS, result.getName() + " got successfully executed");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while logging test success.");
        }
    }

    public void onTestFailure(ITestResult result) {
        try {
            test = report.createTest(result.getTestClass().getName());
            test.assignCategory(result.getMethod().getGroups());
            test.log(Status.FAIL, result.getMethod() + " got Failed");
            test.log(Status.INFO, result.getThrowable().getMessage());

            try {
                String imgPath = new BaseClass().captureScreen(result.getName());
                test.addScreenCaptureFromPath(imgPath);
            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println("Error occurred while capturing or adding screenshot.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while logging test failure.");
        }
    }

    public void onTestSkipped(ITestResult result) {
        try {
            test = report.createTest(result.getTestClass().getName());
            test.assignCategory(result.getMethod().getGroups());
            test.log(Status.SKIP, result.getName() + " got skipped");
            test.log(Status.INFO, result.getThrowable().getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while logging skipped test.");
        }
    }

    public void onFinish(ITestContext context) {
        try {
            report.flush();
            System.out.println("Report generated successfully at: " + new File("reports/" + reportName).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while generating the report.");
        }

        
        // Optional code to open the report in a browser
        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + reportName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        /*
        // Optional code to send the report via email
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("your-email@gmail.com", "your-email-password"));
            email.setSSLOnConnect(true);

            email.setFrom("your-email@gmail.com");
            email.setSubject("Extent Report Test Results");
            email.setMsg("Please find the attached Extent Report.");

            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(pathOfExtentReport);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setName("ExtentReport.html");
            email.attach(attachment);

            email.addTo("recipient-email@example.com");

            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        */
    }
}
