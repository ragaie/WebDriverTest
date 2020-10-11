import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ReviewMyOrders {
    By sectionTitle = By.xpath("//*[@id=\"center_column\"]/h1");
    By myAccount = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span");
    By accountList = By.className("myaccount-link-list");
    By orderHistory = By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[1]/a/span");
    By itemList = By.id("order-list");
    By firstItem = By.xpath("//*[@id=\"order-list\"]/tbody/tr/td[2]");
    By showItemDetail = By.xpath("//*[@id=\"order-list\"]/tbody/tr[2]/td/div/div[3]/div[2]/a[1]/span");
    By itemDetaillabel = By.xpath("//*[@id=\"order-detail-content\"]/table/tbody/tr/td[2]/label");
    WebDriver driver;
    WebDriverWait wait;
    DataDrivenManger dataManger;
    ExtentReports reports;
    ExtentTest logs;
    public ReviewMyOrders(WebDriver driver,DataDrivenManger dataManger, ExtentReports reports){
        this.driver = driver;
        this.dataManger = dataManger;
        this.reports = reports;

        wait = new WebDriverWait(driver, 40);
        logs = reports.startTest("Review my orders details","review that is i ordered correect itm or not");

    }

    public void openMyOrderList() {
        /*
         * open account
         * to check placed orders
         * */

        wait.until(ExpectedConditions.invisibilityOfElementWithText(sectionTitle,"Order confirmation"));
            logs.log(LogStatus.INFO,"order placed ");

        driver.findElement(myAccount).click();

      //  open my orders history
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountList));
        logs.log(LogStatus.INFO,"My account screen opend");
        driver.findElement(orderHistory).click();


        /*
         * select first item which it represent item you ordered
         * */
       // WebDriverWait wait11 = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemList));
        logs.log(LogStatus.INFO,"my order List opened");
        driver.findElement(firstItem).click();
        logs.log(LogStatus.INFO,"first item selected");

        /*
         * click item detail to make sure you ordered correct item.
         * */
        wait.until(ExpectedConditions.elementToBeClickable(showItemDetail));
        logs.log(LogStatus.INFO,"first item selected");
        driver.findElement(showItemDetail).click();
        logs.log(LogStatus.INFO,"show detail pressed");

        /*
        * get listed item detail and compare it with item already added before in last session of shoping.
        * */
        String  str = driver.findElement(itemDetaillabel).getText();
        if (str.contains(dataManger.selectedItem)){
             System.out.println("Item you shopped placed correctly");
            logs.log(LogStatus.PASS,"You had pleaced order correct, and order in your package and in way to shipping");

        }




    }
}
