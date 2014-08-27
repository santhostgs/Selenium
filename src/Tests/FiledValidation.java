package Tests;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class FiledValidation {
	public WebDriver driver = new FirefoxDriver();
	WebElement user;
	WebElement email;
	WebElement fname;
	WebElement address;
	WebElement city;
	WebElement phone;
	Properties prop = new Properties();
	InputStream input = null;
	 
  @BeforeTest
  public void beforeTest() throws Exception{
	  driver.navigate().to("https://stage.govliquidation.com/register"); 
	  driver.manage().window().maximize();
	  Thread.sleep(10000);
	  input = new FileInputStream("D:/Automation/UBR/src/Tests/data.properties");
	  prop.load(input);
  }
  
  
  /*USERNAME FIELD VALIDATIONS*/    
  @Test(priority=1)
  public void username_verification_empty() throws Exception {
	  user = driver.findElement(By.id("username"));
	  user.clear();
	  user.sendKeys("");
	  user.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.name("isMandatoryConditionSatisfied"));
	  assertEquals(err.getText().trim(), "This field is mandatory.");
  }
  
  @Test(priority=2)
  public void username_verification_exist() throws Exception {
	  user.clear();
	  user.sendKeys(prop.getProperty("user_exist").toString());
	  user.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.name("isUsernameExists"));
	  assertEquals(err.getText().trim(), "This username is already in use. Please select a different username.");
  }
  
  @Test(priority=3)
  public void username_verification_min() throws Exception {
	  user.clear();
	  user.sendKeys(prop.getProperty("user_min").toString());
	  user.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.name("isUsernameMinLengthSatisfied"));
	  assertEquals(err.getText().trim(), "Username must be at least 4 characters");
  }
  
  @Test(priority=4)
  public void username_verification_max() throws Exception {
	  user.clear();
	  user.sendKeys(prop.getProperty("user_max").toString());
	  user.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.name("isUsernameMaxLengthSatisfied"));
	  assertEquals(err.getText().trim(), "Username may not be more than 50 characters");
  }
  
  @Test(priority=5)
  public void username_verification_valid() throws Exception {
	  String userid = UUID.randomUUID().toString();
	  userid = userid.replaceAll("-", "_");
	  System.out.println(userid);
	  user.clear();
	  user.sendKeys(userid);
	  user.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.name("isUsernameMaxLengthSatisfied"));
	  assertEquals(err.getText().trim().equals("Username may not be more than 50 characters"), false);
  }
  
  /*Email field validations*/
  
  @Test(priority=6)
  public void email_verification_empty() throws Exception {
	  email = driver.findElement(By.id("email"));
	  email.clear();
	  email.sendKeys("");
	  email.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='email']/following-sibling::*/*[@name='isMandatoryConditionSatisfied']"));
	  assertEquals(err.getText().trim(), "This field is mandatory.");
  }
  
  @Test(priority=7)
  public void email_verification_exist() throws Exception {
	  email.clear();
	  email.sendKeys(prop.getProperty("email_exist").toString());
	  email.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='email']/following-sibling::*/*[@name='isEmailExists']"));
	  assertEquals(err.getText().trim(), "This email is already in use. Please select a different email address.");
  }
  
  @Test(priority=9)
  public void email_verification_max() throws Exception {
	  email.clear();
	  email.sendKeys(prop.getProperty("email_max_256").toString());
	  email.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='email']/following-sibling::*/*[@name='isEmailMaxLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "Email should not exceed 255 characters");
	  email.clear();
	  email.sendKeys(prop.getProperty("email_max_255").toString());
	  email.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='email']/following-sibling::*/*[@name='isEmailMaxLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("Email should not exceed 255 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
	  
  }
  
  @Test(priority=8)
  public void email_verification_valid() throws Exception {
	  email.clear();
	  email.sendKeys(prop.getProperty("email_min").toString());
	  email.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  try{
	  WebElement err = driver.findElement(By.xpath(".//*[@id='email']/following-sibling::*/*[@name='isUsernameExists']"));
	  assertEquals(err.getText().trim().equals("This email is already in use. Please select a different email address."), false);
	  err = driver.findElement(By.xpath(".//*[@id='email']/following-sibling::*/*[@name='isMandatoryConditionSatisfied']"));
	  assertEquals(err.getText().trim().equals("This field is mandatory."), false);
	  }
	  catch(Exception E)
	  {
		  System.out.println("pass");
	  }
	 
  }
  
  /*Confirm Email field validations*/
  @Test(priority=10)
  public void cemail_verification_empty() throws Exception {
	  email = driver.findElement(By.id("confirmEmail"));
	  email.clear();
	  email.sendKeys("");
	  email.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='confirmEmail']/following-sibling::*/*[@name='isMandatoryConditionSatisfied']"));
	  assertEquals(err.getText().trim(), "This field is mandatory.");
  }
  
  
  @Test(priority=11)
  public void cemail_verification_max() throws Exception {
	  email.clear();
	  email.sendKeys(prop.getProperty("email_max_256").toString());
	  email.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='confirmEmail']/following-sibling::*/*[@name='isEqual']"));
	  assertEquals(err.getText().trim(), "Verify Email entered is not same as Email");
	  email.clear();
	  email.sendKeys(prop.getProperty("email_max_255").toString());
	  email.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='confirmEmail']/following-sibling::*/*[@name='isEqual']"));
		  assertEquals(err.getText().trim().equals("Email should not exceed 255 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
	  
  }
  
  /*First name field validations*/
  
  @Test(priority=12)
  public void firstname_verification_empty() throws Exception {
	  fname = driver.findElement(By.id("firstname")); 
	  fname.clear();
	  fname.sendKeys("");
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='firstname']/following-sibling::*/*[@name='isMandatoryConditionSatisfied']"));
	  assertEquals(err.getText().trim(), "This field is mandatory.");
  }
  
  @Test(priority=13)
  public void firstname_verification_min() throws Exception {
	  fname.clear();
	  fname.sendKeys(prop.getProperty("firstname_min_1").toString());
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='firstname']/following-sibling::*/*[@name='isFirstnameMinLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "First name must be at least 2 characters");
	  fname.clear();
	  fname.sendKeys(prop.getProperty("firstname_min_2").toString());
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='firstname']/following-sibling::*/*[@name='isFirstnameMinLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("First name must be at least 2 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  @Test(priority=15)
  public void firstname_verification_max() throws Exception {
	  fname.clear();
	  fname.sendKeys(prop.getProperty("firstname_max_51").toString());
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='firstname']/following-sibling::*/*[@name='isFirstnameMaxLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "First name may not exceed 50 characters");
	  fname.clear();
	  fname.sendKeys(prop.getProperty("firstname_max_50").toString());
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='firstname']/following-sibling::*/*[@name='isFirstnameMaxLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("First name may not exceed 50 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  @Test(priority=14)
  public void firstname_verification_invalid() throws Exception {
	  fname = driver.findElement(By.id("firstname")); 
	  fname.clear();
	  fname.sendKeys(prop.getProperty("firstname_invalid").toString());
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='firstname']/following-sibling::*/*[@name='isFirstnameValid']"));
	  assertEquals(err.getText().trim(), "Firstname accepts only alphanumeric, hyphen, apostrophe, period characters");
  }
    
    
  /*Last name field validation*/
  
  @Test(priority=16)
  public void lastname_verification_empty() throws Exception {
	  fname = driver.findElement(By.id("lastname")); 
	  fname.clear();
	  fname.sendKeys("");
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='lastname']/following-sibling::*/*[@name='isMandatoryConditionSatisfied']"));
	  assertEquals(err.getText().trim(), "This field is mandatory.");
  }
  
  @Test(priority=17)
  public void lastname_verification_min() throws Exception {
	  fname.clear();
	  fname.sendKeys(prop.getProperty("firstname_min_1").toString());
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='lastname']/following-sibling::*/*[@name='isLastnameMinLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "Last name must be at least 2 characters");
	  fname.clear();
	  fname.sendKeys(prop.getProperty("firstname_min_2").toString());
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='lastname']/following-sibling::*/*[@name='isLastnameMinLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("Last name must be at least 2 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  @Test(priority=19)
  public void lastname_verification_max() throws Exception {
	  fname.clear();
	  fname.sendKeys(prop.getProperty("firstname_max_51").toString());
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='lastname']/following-sibling::*/*[@name='isLastnameMaxLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "Last name may not exceed 50 characters");
	  fname.clear();
	  fname.sendKeys(prop.getProperty("firstname_max_50").toString());
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='lastname']/following-sibling::*/*[@name='isLastnameMaxLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("Last name may not exceed 50 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  @Test(priority=18)
  public void lastname_verification_invalid() throws Exception {
	  fname.clear();
	  fname.sendKeys(prop.getProperty("firstname_invalid").toString());
	  fname.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='lastname']/following-sibling::*/*[@name='isLastnameValid']"));
	  assertEquals(err.getText().trim(), "Lastname accepts only alphanumeric, hyphen, apostrophe, period characters");
  }
  
  /*Addressline1 field validations*/
  
  @Test(priority=20)
  public void address1_verification_empty() throws Exception {
	  address = driver.findElement(By.id("address")); 
	  address.clear();
	  address.sendKeys("");
	  address.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='address']/following-sibling::*/*[@name='isMandatoryConditionSatisfied']"));
	  assertEquals(err.getText().trim(), "This field is mandatory.");
  }
  
  @Test(priority=21)
  public void address1_verification_min() throws Exception {
	  address.clear();
	  address.sendKeys(prop.getProperty("address_min_1").toString());
	  address.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='address']/following-sibling::*/*[@name='isAddressLineMinLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "Address must be at least 9 characters");
	  address.clear();
	  address.sendKeys(prop.getProperty("address_min_2").toString());
	  address.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='address']/following-sibling::*/*[@name='isAddressLineMinLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("Address must be at least 9 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  @Test(priority=22)
  public void address1_verification_max() throws Exception {
	  address.clear();
	  address.sendKeys(prop.getProperty("address_max_256").toString());
	  address.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='address']/following-sibling::*/*[@name='isAddressLineMaxLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "Address should not exceed 255 characters");
	  address.clear();
	  address.sendKeys(prop.getProperty("address_max_255").toString());
	  address.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='address']/following-sibling::*/*[@name='isAddressLineMaxLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("Address should not exceed 255 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  /*Addressline2 field validations*/

  @Test(priority=23)
  public void address2_verification_max() throws Exception {
	  address = driver.findElement(By.id("address2"));
	  address.clear();
	  address.sendKeys(prop.getProperty("address_max_256").toString());
	  address.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='address2']/following-sibling::*/*[@name='isAddressLineMaxLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "Address should not exceed 255 characters");
	  address.clear();
	  address.sendKeys(prop.getProperty("address_max_255").toString());
	  address.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='address2']/following-sibling::*/*[@name='isAddressLineMaxLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("Address should not exceed 255 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  
  
 /*City field validations*/
  
  @Test(priority=24)
  public void city_verification_empty() throws Exception {
	  city = driver.findElement(By.id("city")); 
	  city.clear();
	  city.sendKeys("");
	  city.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='city']/following-sibling::*/*[@name='isMandatoryConditionSatisfied']"));
	  assertEquals(err.getText().trim(), "This field is mandatory.");
  }
  
  @Test(priority=25)
  public void city_verification_min() throws Exception {
	  city.clear();
	  city.sendKeys(prop.getProperty("city_min_1").toString());
	  city.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='city']/following-sibling::*/*[@name='isCityMinLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "City must be at least 2 characters");
	  city.clear();
	  city.sendKeys(prop.getProperty("city_min_2").toString());
	  city.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='city']/following-sibling::*/*[@name='isCityMinLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("City must be at least 2 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  @Test(priority=26)
  public void city_verification_max() throws Exception {
	  city.clear();
	  city.sendKeys(prop.getProperty("city_max_129").toString());
	  city.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='city']/following-sibling::*/*[@name='isCityMaxLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "City may not exceed 128 characters");
	  city.clear();
	  city.sendKeys(prop.getProperty("city_max_128").toString());
	  city.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='city']/following-sibling::*/*[@name='isCityMaxLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("City may not exceed 128 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  /*Postal code field validations*/
  
  @Test(priority=27)
  public void postal_verification_empty() throws Exception {
	  phone = driver.findElement(By.id("phone")); 
	  phone.clear();
	  phone.sendKeys("");
	  phone.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='city']/following-sibling::*/*[@name='isMandatoryConditionSatisfied']"));
	  assertEquals(err.getText().trim(), "This field is mandatory.");
  }
  
  @Test(priority=28)
  public void postal_verification_min() throws Exception {
	  phone.clear();
	  phone.sendKeys(prop.getProperty("city_min_1").toString());
	  phone.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='phone']/following-sibling::*/*[@name='isCityMinLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "City must be at least 2 characters");
	  phone.clear();
	  phone.sendKeys(prop.getProperty("city_min_2").toString());
	  phone.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='phone']/following-sibling::*/*[@name='isCityMinLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("City must be at least 2 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  @Test(priority=29)
  public void postal_verification_max() throws Exception {
	  phone.clear();
	  phone.sendKeys(prop.getProperty("city_max_129").toString());
	  phone.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='phone']/following-sibling::*/*[@name='isCityMaxLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "City may not exceed 128 characters");
	  phone.clear();
	  phone.sendKeys(prop.getProperty("city_max_128").toString());
	  phone.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='phone']/following-sibling::*/*[@name='isCityMaxLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("City may not exceed 128 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  /*Phone code field validations*/
  
  @Test(priority=30)
  public void phone_verification_empty() throws Exception {
	  phone = driver.findElement(By.id("phone")); 
	  phone.clear();
	  phone.sendKeys("");
	  phone.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='city']/following-sibling::*/*[@name='isMandatoryConditionSatisfied']"));
	  assertEquals(err.getText().trim(), "This field is mandatory.");
  }
  
  @Test(priority=31)
  public void phone_verification_min() throws Exception {
	  phone.clear();
	  phone.sendKeys(prop.getProperty("city_min_1").toString());
	  phone.sendKeys(Keys.TAB);
	  Thread.sleep(2000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='phone']/following-sibling::*/*[@name='isCityMinLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "City must be at least 2 characters");
	  phone.clear();
	  phone.sendKeys(prop.getProperty("city_min_2").toString());
	  phone.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='phone']/following-sibling::*/*[@name='isCityMinLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("City must be at least 2 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  @Test(priority=32)
  public void phone_verification_max() throws Exception {
	  phone.clear();
	  phone.sendKeys(prop.getProperty("city_max_129").toString());
	  phone.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  WebElement err = driver.findElement(By.xpath(".//*[@id='phone']/following-sibling::*/*[@name='isCityMaxLengthSatisfied']"));
	  assertEquals(err.getText().trim(), "City may not exceed 128 characters");
	  phone.clear();
	  phone.sendKeys(prop.getProperty("city_max_128").toString());
	  phone.sendKeys(Keys.TAB);
	  Thread.sleep(3000);	
	  try{
		  err = driver.findElement(By.xpath(".//*[@id='phone']/following-sibling::*/*[@name='isCityMaxLengthSatisfied']"));
		  assertEquals(err.getText().trim().equals("City may not exceed 128 characters"), false);  
	  }
	  catch(Exception e)
	  {
		  System.out.println("pass");
	  }
  }
  
  
  
  @AfterTest
  public void afterTest() throws Exception {
	  Thread.sleep(2000);
	  driver.quit();
  }

}
