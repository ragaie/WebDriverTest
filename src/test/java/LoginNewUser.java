import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginNewUser {
     By email =  By.id("email");
     By password = By.id("passwd");
     By signIn = By.id("SubmitLogin");

     By logoutButton =  By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a");
        By accountTitle = By.xpath("//*[@id=\"center_column\"]/h1");
    WebDriver driver;
    WebDriverWait wait;
    DataDrivenManger dataManger;
    ExtentReports reports;
    ExtentTest logs;

    public LoginNewUser(WebDriver driver,DataDrivenManger dataManger,ExtentReports reports){
        this.driver = driver;
        this.dataManger = dataManger;
        this.reports = reports;
        wait = new WebDriverWait(driver, 40);
        logs = reports.startTest("Login screen","logging with email and password");

    }

    public void loginUser(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(accountTitle, "Authentication"));

        if(driver.findElement(accountTitle).getText().equalsIgnoreCase("Authentication")){
            logs.log(LogStatus.INFO,"Login form is loaded and available for login action");
        }
        else{
            logs.log(LogStatus.ERROR,"Login form not loaded");
        }

        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(dataManger.userObject.email);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(dataManger.userObject.password);
        driver.findElement(signIn).click();
        logs.log(LogStatus.PASS,"User loged successfully");

    }

     public void logOutUser(){
         wait.until(ExpectedConditions.textToBePresentInElementLocated(accountTitle, "My account"));
         if(driver.findElement(accountTitle).getText().equalsIgnoreCase("My account")){
             logs.log(LogStatus.INFO,"account screen loaded");

         }
         else {
             logs.log(LogStatus.ERROR, "account screen not loaded");
         }
             driver.findElement(logoutButton).click();
             logs.log(LogStatus.INFO, "User loggedOut Successfully");


     }
}
