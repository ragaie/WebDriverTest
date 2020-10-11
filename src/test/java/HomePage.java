import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.Dialect;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HomePage  {

    //*[@id="email_create"]
    By errorbodyMessage = By.xpath("/html/body/h1");
    By emailText = By.id("email_create");
    By createAccount = By.id("SubmitCreate");
    By errorMessage = By.xpath("//div[@id='create_account_error']/ol/li");
    By bestSeller =By.xpath("//*[@id=\"home-page-tabs\"]/li[2]/a");
    String messageemailTaken = "An account using this email address has already been registered.";
    String messageEmailNotVaild = "Invalid email address.";


    WebDriverWait wait ;
    ExtentReports reports;
    ExtentTest homePage;
    private WebDriver driver;
    private String baseUrl;
    private DataDrivenManger dataManger;

    // screens we will calling it inside.
    CreateNewAccount newUser ;
    LoginNewUser loginUser;
    ShoppingItems shopItem ;
    ReviewMyOrders checkMyOrders;

    @BeforeTest
    public void setUp() throws Exception {
        setup();
        loadingHomePage();

    }

    @AfterTest
    public void tearDown(){
        reports.flush();
    }

public void setup(){
    driver = new SafariDriver();
    baseUrl = "http://automationpractice.com/index.php";
    driver.manage().window().setSize(new Dimension(1024, 768));
    wait = new WebDriverWait(driver, 40);

    dataManger = new DataDrivenManger();
    reports = new ExtentReports("ExtentReport.html", true);
    newUser = new CreateNewAccount(driver,dataManger,reports);
    loginUser = new LoginNewUser(driver,dataManger,reports);
    shopItem = new ShoppingItems(driver,dataManger,reports);
    checkMyOrders = new ReviewMyOrders(driver,dataManger,reports);

    homePage = reports.startTest("Home page","checking action on it");
}

public void loadingHomePage(){
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    driver.get(baseUrl);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    if (driver.findElement(bestSeller).getText().equalsIgnoreCase("Best Sellers")) {

        homePage.log(LogStatus.INFO,"Home Page loaded");
        System.out.println("Email vaildation working fine");
    }
    else{
        homePage.log(LogStatus.FAIL,"Home Page failed to load");

    }
}
    /**
     * This test case will load http://automationpractice.com/index.php
     * <p>
     * Verify  page have signIn element
     * <p>
     * Select signIn
     * <p>
     * checking error message when entering wrong format email
     * then check wheen entring  correect email format
     */
    @Test
    public void signScreen() {

        driver.findElement(By.linkText("Sign in")).click();
        homePage.log(LogStatus.INFO, "Sign in Button pressed");
        wait.until(ExpectedConditions.elementToBeClickable(createAccount));
        homePage.log(LogStatus.INFO, "Sign screen loaded");

        verifyWrongEmailFormat();
        enterCorrectEmailFormat();
        /*
         * if error measage displayed or not
         * and check value of it to find out if email registred before of not
         * */
        if (elementStatus(errorMessage)){
            if (driver.findElement(errorMessage).getText().contains(messageemailTaken)) {
                   // System.out.println(driver.findElement(errorMessage).getText());
                homePage.log(LogStatus.PASS, "can't register with email registered before");
                this.loginInAndOrderItem();

            }
        }
        else{
            //Create new user
                newUser.createNewUser();
            homePage.log(LogStatus.INFO, "start create new user account");

            //logout This user
                loginUser.logOutUser();
            homePage.log(LogStatus.INFO, "LogOut form created new user account");

            //to start new session by login and order new item
                loginInAndOrderItem();
            homePage.log(LogStatus.INFO, "loged in new seession with account already created Before");

        }
    }


    public void loginInAndOrderItem(){
        loginUser.loginUser();
        shopItem.searchForItem();
        checkMyOrders.openMyOrderList();
    }

    public void verifyWrongEmailFormat(){
        /*
         * check entering wrong format email
         */
        driver.findElement(emailText).sendKeys("dssad.sdas");
        driver.findElement(createAccount).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (driver.findElement(errorMessage).getText().equalsIgnoreCase(messageEmailNotVaild)) {
            System.out.println("Email vaildation working fine");
            homePage.log(LogStatus.PASS, "can't register with wrong email format");
        }
        else{
            homePage.log(LogStatus.FAIL, "email validation failed");

        }
    }

    public void enterCorrectEmailFormat(){
        /*
         * clear it wrong email
         * enter correct format email
         * Cheeck if email taken login, go to login option
         * If not go to register new user
         * */

        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        driver.findElement(emailText).clear();
        driver.findElement(emailText).sendKeys(dataManger.userObject.email);
        driver.findElement(createAccount).click();
        homePage.log(LogStatus.PASS, "Start create new account with vaild and unused email");

    }

    public Boolean elementStatus(By by) {
        try {
            WebElement element = driver.findElement(by);
            return  true;

        } catch (NoSuchElementException ex){
            return  false;
        }

    }
}