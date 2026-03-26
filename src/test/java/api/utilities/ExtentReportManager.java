package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener

{

	public ExtentSparkReporter sparkReporter;	//UI of report
	public ExtentReports extent;				//populate with common info for the report
	public ExtentTest test;						//populate with common info for the report
	
	String repName;
	
	public void onStart(ITestContext textContext)
	{
		
		/*SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt= new Date();
		String currentdatetimestamp= df.format(dt);
		*/
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());	//time stamp
		
		repName = "Test-Report" + timeStamp + ".html";
		sparkReporter= new ExtentSparkReporter(".\\reports\\" +repName);	//special folder created to store the report
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");	//Title of report
		sparkReporter.config().setReportName("Pet Store Users API");		//Name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Vikram");
		
		/*String os= textContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating Syste", os);
		
		String browser= textContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups= textContext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty())
		{
			
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
		
		*/
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test= extent.createTest(result.getTestClass().getName());	// to display class name in report
		test.assignCategory(result.getMethod().getGroups());	//to display groups in report
		test.log(Status.PASS, result.getName()+ "got successfully executed");
		
	}
	
	public void onTestFailure(ITestResult result)
	{
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+ "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	
	public void onTestSkipped(ITestResult result)
	{
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ "got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage()+ "got Skipped");
		
	}
	
	public void onFinish(ITestContext context)
	{
		extent.flush();
		
	}
	
		
		/*
		//Optional part
		// To send the mail automatically after the report has been generated 
		try
		{
			URL url= new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			
			//Create the email
			
			ImageHtmlEmail email= new ImageHtmlEmail();
			email.setDataSourceResolver(new  DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("abc@gmail.com", "password"));
			email.setSSLOnConnect(true);
			email.setFrom("abc@gmail.com"); 	//sender
			email.setSubject("Test Results");
			email.setMsg("Please find attached report...");
			email.addTo("xyz@gmail.com");		//receiver
			email.attach(url, "extent report", "please check report...");
			email.send();		//send the email
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
			
			
			
		}
	*/
}
	
	

