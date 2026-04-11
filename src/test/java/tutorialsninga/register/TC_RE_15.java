//package tutorialsninga.register;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.time.Duration;
//import java.util.Date;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class TC_RE_15 {
//	  String url = "jdbc:mysql://localhost:3306/opencart_db";
//      String username = "root";
//      String password = null;
//      String firstNameStored= null;
//      String lastNameStored=null;
//      String emailStored=null;
//      
//    @Test
//	public void verifyDataTestingOfRegisteringAccount() {
//    	 WebDriver driver = new ChromeDriver();
//		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//	      driver.manage().window().maximize();
//	      driver.get("http://localhost/opencart");
//	      driver.findElement(By.xpath("//span[.='My Account']")).click();
//		  driver.findElement(By.linkText("Register")).click();
//		  String firstNameData="Mohd";
//		  driver.findElement(By.id("input-firstname")).sendKeys(firstNameData);
//		  String lastNameData="Mujeeb";
//		  driver.findElement(By.id("input-lastname")).sendKeys(lastNameData);
//	      String emaildata=generateEmale();
//		  driver.findElement(By.id("input-email")).sendKeys(emaildata);
//		  String passwordData="123456";
//		  driver.findElement(By.id("input-password")).sendKeys(passwordData);
//		  WebElement newsletter = driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']"));
//		  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newsletter);
//          WebElement policy =  driver.findElement(By.name("agree"));
//          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", policy);
//          WebElement button= driver.findElement(By.xpath("//button[.='Continue']"));
//          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
//    
//          Connection connection = null;
//          Statement statement =null;
//          ResultSet resultSet = null;
//          
//          try {
//              // Step 1: Establish the connection
//              connection = DriverManager.getConnection(url, username, password);
//              System.out.println("Connected to the database!");
//
//              // Step 2: Create a statement
//              statement = connection.createStatement();
//
//              // Step 3: Execute a query
//              String sql = "SELECT * FROM oc_customer";
//              resultSet = statement.executeQuery(sql);
//
//              // Step 4: Process the result set
//              while (resultSet.next()) {
//                  String firstNameStored = resultSet.getString("firstname");
//                  String lastNameStored = resultSet.getString("lastname");
//                  String emailStored = resultSet.getString("email");
//              }
//
//          } catch (Exception e) {
//              e.printStackTrace();
//          } finally {
//              try {
//                  if (resultSet != null) resultSet.close();
//                  if (statement != null) statement.close();
//                  if (connection != null) connection.close();
//              } catch (Exception e) {
//                  e.printStackTrace();
//              }
//          }
//          Assert.assertEquals(firstNameStored, firstNameData);
//          Assert.assertEquals(lastNameStored, lastNameData);
//          Assert.assertEquals(emailStored, emaildata);
//    
//    }
//    
//    public   String generateEmale() {
//  		return new Date().toString().replaceAll(" ", "").replaceAll("\\:","")+"@gmail.com";
//}
//    
//}

package tutorialsninga.register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RE_15 {

    String url = "jdbc:mysql://localhost:3306/opencart_db";
    String username = "root";
    String password = null;

    String firstNameStored = null;
    String lastNameStored = null;
    String emailStored = null;

    @Test
    public void verifyDataTestingOfRegisteringAccount() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("http://localhost/opencart");

        driver.findElement(By.xpath("//span[.='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        String firstNameData = "Mohd";
        driver.findElement(By.id("input-firstname")).sendKeys(firstNameData);

        String lastNameData = "Mujeeb";
        driver.findElement(By.id("input-lastname")).sendKeys(lastNameData);

        String emaildata = generateEmale();
        driver.findElement(By.id("input-email")).sendKeys(emaildata);

        String passwordData = "123456";
        driver.findElement(By.id("input-password")).sendKeys(passwordData);

        WebElement newsletter = driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newsletter);

        WebElement policy = driver.findElement(By.name("agree"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", policy);

        WebElement button = driver.findElement(By.xpath("//button[.='Continue']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

        // ✅ Wait for DB insertion
        Thread.sleep(2000);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Step 1: Establish connection
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            // Step 2: Create statement
            statement = connection.createStatement();

            // ✅ Step 3: Query only inserted user
            String sql = "SELECT * FROM oc_customer WHERE email='" + emaildata + "'";
            resultSet = statement.executeQuery(sql);

            // ✅ Step 4: Store values correctly (NO shadowing)
            if (resultSet.next()) {
                firstNameStored = resultSet.getString("firstname");
                lastNameStored = resultSet.getString("lastname");
                emailStored = resultSet.getString("email");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // ✅ Assertions
        Assert.assertEquals(firstNameStored, firstNameData);
        Assert.assertEquals(lastNameStored, lastNameData);
        Assert.assertEquals(emailStored, emaildata.toLowerCase());

        driver.quit();
    }

    public String generateEmale() {
        return new Date().toString().replaceAll(" ", "").replaceAll("\\:", "") + "@gmail.com";
    }
}
