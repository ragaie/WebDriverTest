
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewAccount {
    By screenTitle = By.id("noSlide");
    By gender = By.id("id_gender1");
    By firstName = By.id("customer_firstname");
    By lastName = By.id("customer_lastname");
    By passwrod = By.id("passwd");
    By selectDay = By.id("days");
    By selectMonth = By.id("months");
    By selectYear = By.id("years");
    By company = By.id("company");
    By address1 = By.id("address1");
    By address2 = By.id("address2");
    By city = By.id("city");
    By stateId = By.id("id_state");
    By postCode = By.id("postcode");
    By other = By.id("other");
    By phoneNumber = By.id("phone");
    By mobileNumber = By.id("phone_mobile");
    By alias = By.id("alias");
    By myAccountScreenTitle = By.id("center_column");
    By screenId = By.xpath("//*[@id=\"noSlide\"]/h1");
    WebDriver driver;
    WebDriverWait wait;
    DataDrivenManger dataManger;
    ExtentReports reports;
    ExtentTest logs;

    public CreateNewAccount(WebDriver driver, DataDrivenManger dataManger,    ExtentReports reports){
        this.driver = driver;
        this.dataManger = dataManger;
        this.reports = reports;
        wait = new WebDriverWait(driver, 40);
       logs =   reports.startTest("Create New User","screen creating new user");

    }


    public void createNewUser() {
        /*
        * wait driver until load page and element of screen found
        * */
        wait.until(ExpectedConditions.textToBePresentInElementLocated(screenId,"Create an account"));
        if(driver.findElement(screenId).getText().equalsIgnoreCase("Create an account")){
            logs.log(LogStatus.INFO,"Creating new user screen loaded successfuly");

        }
        else{
            logs.log(LogStatus.ERROR,"Creating new user screen failed to load");

        }

            driver.findElement(gender).click();
             logs.log(LogStatus.INFO,"selected user gender");

        driver.findElement(firstName).click();
            driver.findElement(firstName).clear();
            driver.findElement(firstName).sendKeys(dataManger.userObject.firstName);
        logs.log(LogStatus.INFO,"usser first name added");

            driver.findElement(lastName).click();
            driver.findElement(lastName).clear();
            driver.findElement(lastName).sendKeys(dataManger.userObject.lastName);
        logs.log(LogStatus.INFO,"user last name added");

            driver.findElement(passwrod).click();
            driver.findElement(passwrod).clear();
            driver.findElement(passwrod).sendKeys(dataManger.userObject.password);
        logs.log(LogStatus.INFO,"account password selected");

           new Select(driver.findElement(selectDay)).selectByIndex(dataManger.userObject.day);
            new Select(driver.findElement(selectMonth)).selectByIndex(dataManger.userObject.month);
            new Select(driver.findElement(selectYear)).selectByIndex(dataManger.userObject.year);
        logs.log(LogStatus.INFO,"user birthDay selected");

            driver.findElement(company).click();
            driver.findElement(company).clear();
            driver.findElement(company).sendKeys(dataManger.userObject.company);
        logs.log(LogStatus.INFO,"user working company added");

            driver.findElement(address1).click();
            driver.findElement(address1).clear();
            driver.findElement(address1).sendKeys(dataManger.userObject.address1);
        logs.log(LogStatus.INFO,"user base address added");

            driver.findElement(address2).click();
            driver.findElement(address2).clear();
            driver.findElement(address2).sendKeys(dataManger.userObject.address2);
        logs.log(LogStatus.INFO,"user secand address added");

            driver.findElement(city).click();
            driver.findElement(city).clear();
            driver.findElement(city).sendKeys(dataManger.userObject.city);
            new Select(driver.findElement(stateId)).selectByIndex(5);
        logs.log(LogStatus.INFO,"user city selected");

            driver.findElement(postCode).click();
            driver.findElement(postCode).clear();
            driver.findElement(postCode).sendKeys(dataManger.userObject.postCode);
        logs.log(LogStatus.INFO,"user post code added");

            driver.findElement(other).click();
            driver.findElement(other).clear();
            driver.findElement(other).sendKeys(dataManger.userObject.others);

            driver.findElement(phoneNumber).click();
            driver.findElement(phoneNumber).clear();
            driver.findElement(phoneNumber).sendKeys(dataManger.userObject.phoneNumber);
        logs.log(LogStatus.INFO,"user phone number added");

            driver.findElement(mobileNumber).click();
            driver.findElement(mobileNumber).clear();
            driver.findElement(mobileNumber).sendKeys(dataManger.userObject.mobileNumber);
        logs.log(LogStatus.INFO,"user mobile number added");

            driver.findElement(alias).click();
            driver.findElement(alias).clear();
            driver.findElement(alias).sendKeys(dataManger.userObject.alias);
            driver.findElement(By.xpath("//button[@id='submitAccount']/span")).click();
        logs.log(LogStatus.INFO,"sending request of creating new user ");

        newUserDone();
    }
/*
* logout user after it successful Signup
* */
    public void newUserDone(){
        //wait creating new account finish and load account screen
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.elementToBeClickable(myAccountScreenTitle));
            logs.log(LogStatus.PASS,"data of creating user passed validation and useer created successfuly");
        driver.findElement(By.id("header")).click();
        logs.log(LogStatus.PASS,"new User had been created");
    }
}


