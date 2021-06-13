package NgbEmailVerificationNew;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NgbEmailVerificationTestcases {


	public static WebDriver driver = null;
	// Get email from mobile-test page
	public static String actualEmailAddress;
	// Get email from landing page 
	public static String expectedEmailAddress;
	
 @BeforeTest
	public static void testSetUp() throws InterruptedException {
		  driver=new ChromeDriver();
		  //Launch url
		  driver.get("https://snap.modernemortgage.com/home/mobile-test");
          driver.manage().window().maximize();
		   Reporter.log( "Url Launched", true );
	 }
	 
   @AfterTest
	public static void tearDown() throws InterruptedException {
	   driver.quit();
       Reporter.log( "Execution completed", true );
	   System.out.println("Execution complete");
	 }

   @Test(priority=1)

	public static void getLenderEmail() throws InterruptedException {
	  Thread.sleep(5000);	  
	  //Get Email address
   actualEmailAddress = driver.findElement(By.xpath("(//*[@class='contact-icon']/..//span/a)[2]")).getText();
   System.out.println("Actual Email Address : " + actualEmailAddress);
   Reporter.log("Actual Email Address >> " + actualEmailAddress + "\n", true );
   }
   
   @Test(priority=2)
	public static void clickOnApplyNowAndVerifyLenderEmail() throws InterruptedException {

   //Click on ApplyNow and Navigate to landing page
   driver.findElement(By.xpath("//*[contains(text(), 'Apply Now')]")).click();
	  Thread.sleep(5000);
   
   //Verify Email address in landing page
	   System.out.println("Print Actual Email Address in apply now method : " + actualEmailAddress);
	   Reporter.log("Actual Email Address in apply now method : >> " + actualEmailAddress + "\n", true );
	   expectedEmailAddress = driver.findElement(By.xpath("//*[contains(@href,'mailto')]")).getText();
    System.out.println("Expected Email Address : " + expectedEmailAddress);
    Reporter.log("Expected Email Address >> " + expectedEmailAddress + "\n", true );
   Assert.assertEquals(expectedEmailAddress, actualEmailAddress);
   Reporter.log( "Email verified succesfully", true );
 }
 }
